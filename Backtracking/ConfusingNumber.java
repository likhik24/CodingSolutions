package Backtracking;

import java.util.*;
//A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.

//        We can rotate digits of a number by 180 degrees to form new digits.
//
//        When 0, 1, 6, 8, and 9 are rotated 180 degrees, they become 0, 1, 9, 8, and 6 respectively.
//        When 2, 3, 4, 5, and 7 are rotated 180 degrees, they become invalid.
//        Note that after rotating a number, we can ignore leading zeros.
//
//        For example, after rotating 8000, we have 0008 which is considered as just 8.
//        Given an integer n, return the number of confusing numbers in the inclusive range [1, n].
//
//
//
//        Example 1:
//
//        Input: n = 20
//        Output: 6
//        Explanation: The confusing numbers are [6,9,10,16,18,19].
//        6 converts to 9.
//        9 converts to 6.
//        10 converts to 01 which is just 1.
//        16 converts to 91.
//        18 converts to 81.
//        19 converts to 61.
//        Example 2:
//
//        Input: n = 100
//        Output: 19
//        Explanation: The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
//
//
//        Constraints:
public class ConfusingNumber {
        ArrayList<Integer> numbersPossible = new ArrayList<>(Arrays.asList(new Integer[]{0,1,6,8,9}));

        int count = 0;
    Map<Integer, Integer> map = new HashMap<>();

    public int confusingNumberII(int n) {

        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        confusingNums(n,0);
        return count;
    }


    //we will keep multiplying current number by 10 and adding any valid number from keyset until num is less than N and keep checking confuse number

    public void confusingNums(int n, long curr) {

        if(isConfuseNumber(curr))
            count++;
        for(Integer i:map.keySet()) {
            if(curr*10+i <= n && curr*10+i !=0) {

                confusingNums(n, curr*10+i);
            }
        }
    }


    //confuse number means it doesnot form same digit after 180 degree rotation so we try reverse the number and also get mapping of it and compare if reverse of mapping is equals to the source
    public boolean isConfuseNumber(long n) {
        long res = 0;
        long src = n;
        while(n != 0) {

            res = res*10 + map.get((int)n%10); //2 20 203
            n=n/10; //30 3
        }
        return res != src;
    }

    /*public int confusingNumberII(int n) {


            confusingNums(n,new StringBuilder());
            return count;
        }

        public boolean isValidNum(StringBuilder num) {
            String curr = String.valueOf(num);
            // System.out.println(curr);
            int i=0;
            int j=curr.length()-1;
            while(j>=i) {
                if((curr.charAt(i) == '9' && curr.charAt(j) == '6') || (curr.charAt(i) == '6' && curr.charAt(j) == '9') || (curr.charAt(i) == '8' && curr.charAt(j) == '8') || (curr.charAt(i) == '1' && curr.charAt(j) == '1')  || (curr.charAt(i) == '0' && curr.charAt(j) == '0' && i != j)) { System.out.println(curr);  return false; }
                else {
                    i++;
                    j--;
                }
            }
            return true;
        }

        public void confusingNums(int n, StringBuilder curr) {
            if(curr.length() > 0 && (Integer.parseInt(curr.toString()) > n || curr.charAt(0) == '0' || curr.length() > String.valueOf(n).length()))
                return;

            //System.out.println(String.valueOf(curr));
            if(curr.length() > 0 && isValidNum(curr) ) {
                //  System.out.println(String.valueOf(curr));
                count++;
            }

            for(int i=0;i<numbersPossible.size();i++) {

                if(curr.length() == 0 && numbersPossible.get(i) == 0)
                    continue;
                if(curr.length() >= String.valueOf(n).length())
                    return;
                curr.append(numbersPossible.get(i));

                confusingNums(n, curr);
                curr.deleteCharAt(curr.length()-1);
            }
            return;
        }
*/
}
