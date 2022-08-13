package Stack;//You are given an integer array nums of size n. You are asked to solve n queries for each integer i in the range 0 <= i < n.
//
//        To solve the ith query:
//
//        Find the minimum value in each possible subarray of size i + 1 of the array nums.
//        Find the maximum of those minimum values. This maximum is the answer to the query.
//        Return a 0-indexed integer array ans of size n such that ans[i] is the answer to the ith query.
//
//        A subarray is a contiguous sequence of elements in an array.
//
//
//
//        Example 1:
//
//        Input: nums = [0,1,2,4]
//        Output: [4,2,1,0]
//        Explanation:
//        i=0:
//        - The subarrays of size 1 are [0], [1], [2], [4]. The minimum values are 0, 1, 2, 4.
//        - The maximum of the minimum values is 4.
//        i=1:
//        - The subarrays of size 2 are [0,1], [1,2], [2,4]. The minimum values are 0, 1, 2.
//        - The maximum of the minimum values is 2.
//        i=2:
//        - The subarrays of size 3 are [0,1,2], [1,2,4]. The minimum values are 0, 1.
//        - The maximum of the minimum values is 1.
//        i=3:
//        - There is one subarray of size 4, which is [0,1,2,4]. The minimum value is 0.
//        - There is only one value, so the maximum is 0.
import java.util.*;
public class MaximumOfMinimumSubArrays {
    public int[] findMaximums(int[] nums) {
        // nums -> 10 20 50 10
        //     10 20 10 50
        //     10 20 50 -> 50
        //     10 20 -> 10
        //     10
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[nums.length]; //-1 0 1 -1
        int[] right = new int[nums.length]; //4 4 3 4
        //find left minimum element and right minimum element position for each element in nums
        //we will use stack and keep pushing index to stack and pop indexes if nums at index >= nums[currentindex]
        //if still there is element in stack that means there is element in stack which has nums [stack.peek()] < nums[i] ( therefore we found our minimum element we will update it as left[i]/right[i] )

        for(int i=0;i<nums.length;i++) {
            left[i] = -1;
            right[i] = nums.length;
        }
        for(int i=0;i<nums.length;i++) {
            while(!stack.isEmpty() && nums[stack.peek()] >= nums[i])
                stack.pop();
            if(!stack.isEmpty())
                left[i] = stack.peek();
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            stack.pop();
        }
        for(int i=nums.length-1;i>=0;i--) {
            while(!stack.isEmpty() && nums[stack.peek()] >= nums[i])
                stack.pop();
            if(!stack.isEmpty())
                right[i] = stack.peek();
            stack.push(i);
        }
        //now we can compute the minimum of each size by taking right[i]-left[i]-2 as size and setting res[size] as nums[i];
        // if any result for size is not set, we can set its size by getting result of size+1 as the minimum of entire subarray is the least minimum value of any size subarray

        int[] res = new int[nums.length];
        for(int i=0;i<nums.length;i++) {
            int size = right[i]-left[i]-2;
            res[size] = Math.max(res[size], nums[i]);
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            res[i] = Math.max(res[i], res[i + 1]);
        }
        return res;

    }
}
