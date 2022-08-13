package Backtracking;

import java.util.*;
/*Approach 1: Backtracking
        Backtracking is an algorithm for finding all solutions by exploring all potential candidates. If the solution candidate turns to be not a solution (or at least not the last one), backtracking algorithm discards it by making some changes on the previous step, i.e. backtracks and then try again.

        Here is a backtrack function which takes the index of the first integer to consider as an argument backtrack(first).

        If the first integer to consider has index n that means that the current permutation is done.
        Iterate over the integers from index first to index n - 1.
        Place i-th integer first in the permutation, i.e. swap(nums[first], nums[i]).
        Proceed to create all permutations which starts from i-th integer : backtrack(first + 1).
        Now backtrack, i.e. swap(nums[first], nums[i]) back. */
public class Permutations {

        public void backtrack(List<Integer> nums, int first, List<List<Integer>> result) {
            if(first >= nums.size()) {
                result.add(nums);
                return;
            }

            for(int i=first;i<nums.size();i++) {
                Collections.swap(nums, first, i);
                backtrack(nums, first+1, result);
                Collections.swap(nums,first, i);
            }
        }
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Integer[] nums1 = new Integer[nums.length];
            List<Integer> subres = Arrays.asList(nums1);
            backtrack(subres, 0,result);
            return result;
        }

}
