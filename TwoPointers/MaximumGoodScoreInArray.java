package TwoPointers;

/* You are given an array of integers nums (0-indexed) and an integer k.

        The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.

        Return the maximum possible score of a good subarray.



        Example 1:

        Input: nums = [1,4,3,7,4,5], k = 3
        Output: 15
        Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15.
        Example 2:

        Input: nums = [5,5,4,5,4,1,1,1], k = 0
        Output: 20
        Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.

 */
public class MaximumGoodScoreInArray {
    public int maximumScore(int[] nums, int k) {
        int currmin = nums[k];

        int left=k;
        int right=k;
//          do {
//             left--;
//          }while(left > 0 && nums[left] > currmin);

//         do {
//             right++;}while(right < nums.length-1 && nums[right] > currmin);

        int currscore = (right-left+1)*currmin;

        while(left > 0 || right < nums.length-1) {
            if(left == 0)
                ++right;
            else if(right == nums.length-1)
                --left;
            else if(nums[left-1] < nums[right+1] ) {
                ++right;
            }
            else --left;

            currmin = Math.min(currmin, Math.min(nums[right],nums[left]));
            currscore = Math.max(currscore, currmin*(right-left+1));
        }
        return currscore;
    }
}
