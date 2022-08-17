package BitManipulation;

import Arrays.*;

public class FindNthMagicNumber {
    //find nth magic number of 5
    //we can take magic number as 001, 010 which will be 5,25 and so on to find nth magic number that is power of 5 , we can keep right shifting to find the ones until it becomes 0
    //5th magic number of 6 is 150 (5,25,30,125,130,150) 001,010,011,100,101,110
    public int findNthMagicNumber(int n) {
        int answer = 0;
        int power  = 1;
        while(n != 0) {
            power = power*5;
            if((n & 1) == 1)
                answer += power;
            n >>= 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        FindNthMagicNumber N = new FindNthMagicNumber();
        System.out.println(N.findNthMagicNumber(6));
    }
}
