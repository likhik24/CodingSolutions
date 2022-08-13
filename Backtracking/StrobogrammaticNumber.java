package Backtracking;

import java.util.*;
//Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.
//
//        A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
//
//
//        Example 1:
//
//        Input: n = 2
//        Output: ["11","69","88","96"]
//        Example 2:
//
//        Input: n = 1
//        Output: ["0","1","8"]
class StrobogrammaticNumber {

    HashMap<Integer, Integer> numbersMap = new HashMap<>();
    Set<Integer> numbersSet = numbersMap.keySet(); //1 6 9 8 0
    public List<String> findStrobogrammatic(int n) {
        numbersMap.put(1,1);
        numbersMap.put(6,9);
        numbersMap.put(9,6);
        numbersMap.put(8,8);
        numbersMap.put(0,0);
        ArrayList<String> result = new ArrayList<>();
        if( n ==1)
            return new ArrayList<>(Arrays.asList(new String[]{"0", "1", "8"}));
        strobNumbers(result, new ArrayList<Integer>(n+1),0, n);
        return result;
    }

    public void strobNumbers(ArrayList<String> result, ArrayList<Integer> curr, int index, int n) {
        if(index == n-1 || (n%2 == 0 && index >= n/2) || (n%2 != 0 && index > n/2))
        {
            StringBuilder results = new StringBuilder();
            for(int d : curr) {
                results.append(d);
            }
            result.add(results.toString());
            return;
        }

        while(curr.size() < n-index)
            curr.add(0);

        Iterator<Integer> iter = numbersSet.iterator();
        while(iter.hasNext()) {
            int num = iter.next();
            if(index == 0 && num == 0 || (index == n-index-1 && num != numbersMap.get(num))) {
                continue;
            }

            int temp=index;
            curr.set(index, num);
            if(index < n/2) {
                curr.set(n-index-1, numbersMap.get(num));
            }
            strobNumbers(result, curr, index+1,n);
            index=temp;

        }
    }
}
