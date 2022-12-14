package DynamicProgramming;
/*
You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
 One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.

        Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).

        Note: You cannot rotate an envelope.



        Example 1:

        Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
        Output: 3
        Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
        Example 2:

        Input: envelopes = [[1,1],[1,1],[1,1]]
        Output: 1
 */
import java.util.*;
public class MaxNumberOfEnvelopes {
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int lengthOfIncreasingSequence = 0;
            for (int num : nums) {
                int i = Arrays.binarySearch(dp, 0, lengthOfIncreasingSequence, num);
                // i will. output (-k-1) where k is insertion point of num in array if this -(output+1)
                // which will give us exact index is in sync with current len like index=length of sequence then we increment length, otherwise this elem doesnot fit in the sequence so we wont increment length;
                System.out.println(i + " " + num);
                if (i < 0) {
                    i = -(i + 1);
                }
                dp[i] = num;
                if (i == lengthOfIncreasingSequence) {
                    lengthOfIncreasingSequence++;
                }
            }
            return lengthOfIncreasingSequence;
        }

        public int maxEnvelopes(int[][] envelopes) {
            // sort on increasing in first dimension and decreasing in second
            //to handle edge case of [[1, 3], [1, 4], [1, 5], [2, 3]] where envelope of same widht cannot fit in other we use this sorting to store descending order of height when widths are same , so they wont form increasing subsequence, in this example maxenvelopes is 1
            Arrays.sort(envelopes, new Comparator<int[]>() {
                public int compare(int[] arr1, int[] arr2) {
                    if (arr1[0] == arr2[0]) {
                        return arr2[1] - arr1[1];
                    } else {
                        return arr1[0] - arr2[0];
                    }
                }
            });
            // extract the second dimension and run LIS
            int[] secondDim = new int[envelopes.length];
            for (int i = 0; i < envelopes.length; ++i) secondDim[i] = envelopes[i][1];
            return lengthOfLIS(secondDim);
        }

}
