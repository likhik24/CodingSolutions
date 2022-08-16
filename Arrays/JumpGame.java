package Arrays;
/*
Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

You can assume that you can always reach the last index.



Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2
 */
public class JumpGame {
    //we have to take farthest jump possible at any point  as that will give us a larger subarray of values we can use to jum from
    public int jump(int[] nums) {
        // int[] dp = new int[nums.length];
        // dp[nums.length-1] = 0;
        // for(int j= nums.length-2;j>0;j--) {
        //     dp[j] = nums[j]+j >= nums.length-1 ? 1 : dp[nums[j]+j];
        // }
        int farthestJump=0; int currentJumpEnd=0;int jumps=0;
        for(int i=0;i<nums.length-1;i++) {
            farthestJump = Math.max(farthestJump, nums[i]+i);
            if(i == nums.length-1)
                return jumps;
            if(i==currentJumpEnd) {
                currentJumpEnd = farthestJump;
                jumps++;

            }
        }
        //if we
        return jumps;
    }
}
