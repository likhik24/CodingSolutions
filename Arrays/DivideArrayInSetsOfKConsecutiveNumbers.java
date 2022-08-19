package Arrays;

import java.util.*;

/*
Given an array of integers nums and a positive integer k, check whether it is possible to divide this array into sets of k consecutive numbers.

Return true if it is possible. Otherwise, return false.



Example 1:

Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {
    //we can find always count of(nums[i]) <= nums[i-1]+nums[i+1] count only then it can be paired
    public boolean possibleToDivide(int[] arr , int k) {
        int groups = k;
        if(arr.length%k != 0)
            return false;

        TreeMap<Integer, Integer> numCountsMap = new TreeMap<>();
        for(int num: arr) {
            numCountsMap.putIfAbsent(num, 0);
            numCountsMap.put(num, numCountsMap.get(num) + 1);

        }
        ArrayList<Integer> numsSorted = new ArrayList<>(numCountsMap.keySet());
        for(int num : numsSorted) {
            if(numCountsMap.get(num) > 0) {
                for(int i=1; i<k;i++) {
                    numCountsMap.put(num + i, numCountsMap.get(num + i) - 1);
                    if(numCountsMap.get(num+i) < 0)
                        return false;
                }
                numCountsMap.put(num,numCountsMap.get(num)-1);
            }
        }
        return true;

    }
}
