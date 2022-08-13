package Arrays;

public class MaxSubArraySumInCyclicArray {

        public int maxSubarraySumCircular(int[] nums) {
            int maxGlobalSum = nums[0];
            int maxEndingHere = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (maxEndingHere < 0)
                    maxEndingHere = 0;
                maxEndingHere += nums[i];

                if (maxGlobalSum < maxEndingHere)
                    maxGlobalSum = maxEndingHere;

            }
            //this is for 1 interval , if we have circular array we can take two intervals (0,i) + (j, nums.length-1) which will be max sum we can calculate max of 0,i using the above logic and leftsum
            // For each i, we want to know
            // the maximum of sum(A[j:]) with j >= i+2 ( we take i+2 because we already calculate the entire array sum in above logic of 1 interval, so we skip i+1 element as it cant be included in two interval sum)
            //for j to nums.length-1 we need to calculate  rightsums
            int N = nums.length;
            int[] rightsums = new int[N];
            rightsums[N - 1] = nums[N - 1];
            for (int i = N - 2; i >= 0; --i) {
                rightsums[i] = rightsums[i + 1] + nums[i];
            }
            int[] maxRightSums = new int[N];
            maxRightSums[N - 1] = rightsums[N - 1];
            for (int i = N - 2; i >= 0; i--) {
                maxRightSums[i] = Math.max(maxRightSums[i + 1], rightsums[i]);
            }

            int[] leftSums = new int[N];
            int leftsum = 0;
            for (int i = 0; i < N - 2; i++) {
                leftsum += nums[i];
                maxGlobalSum = Math.max(maxGlobalSum, leftsum + maxRightSums[i + 2]);
            }

            return maxGlobalSum;
        }
}
