/*
Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.



Example 1:

Input: n = 19
Output: true
Complexity Analysis

Determining the time complexity for this problem is challenging for an "easy" level question. If you're new to these problems, have a go at calculating the time complexity for just the getNext(n) function (don't worry about how many numbers will be in the chain).

Time complexity : O(243 \cdot 3 + \log n + \log\log n + \log\log\log n)...O(243⋅3+logn+loglogn+logloglogn)... = O(\log n)O(logn).

Finding the next value for a given number has a cost of O(\log n)O(logn) because we are processing each digit in the number, and the number of digits in a number is given by \log nlogn.

To work out the total time complexity, we'll need to think carefully about how many numbers are in the chain, and how big they are.

We determined above that once a number is below 243243, it is impossible for it to go back up above 243243. Therefore, based on our very shallow analysis we know for sure that once a number is below 243243, it is imp
 */
import java.util.*;

public class HappyNumber {
    public boolean isHappy(int n) {
        //take examples to find when n can become a cycle in circle, that is when it is summing up to same
        //number as before we repeat same result , so we store the square of digits in hashset for faster rerieval
        TreeSet<Integer> nSet = new TreeSet<>();
        while(n != 1) {

            n = getNextNumber(n);
            if(nSet.contains(n))
                return false;
            nSet.add(n);
        }
        return n==1;
    }

    public int getNextNumber(int n) {
        int d=0;
        int temp=0;
        while(n>0) {
            d = n%10;
            n=n/10;
            temp += d*d;
        }
        return temp;
    }
}
