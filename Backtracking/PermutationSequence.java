package Backtracking;
/*produce kth permutation of num in lexocographical order
Algorithm

        Generate input array nums of numbers from 11 to NN.

        Compute all factorial bases from 00 to (N - 1)!(N−1)!.

        Decrease kk by 1 to make it fit into (0, N! - 1)(0,N!−1) interval.

        Compute factorial representation of kk. Use factorial coefficients to construct the permutation.

        Return the permutation string.
*/

import java.util.*;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 0;
        StringBuilder sb = new StringBuilder();
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        for(int i=1;i<n;i++)
        {
            factorial[i] = factorial[i-1]*i;
            // fit k in the interval 0 ... (n! - 1)
            nums.add(i+1);
        }
        k--; //we already included actual number as 1st number which is 123
        for(int i=n-1;i>-1;i--) {
            int idx = k/factorial[i];
            k -= idx*factorial[i];
            sb.append(nums.get(k));
            nums.remove(idx);
        }
        return String.valueOf(sb);
    }
}
