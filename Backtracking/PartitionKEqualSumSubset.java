package Backtracking;
import java.util.*;

public class PartitionKEqualSumSubset {
    /*first we check if total sum is divisible by k
            then targetsum for each subset is totalsum/k
    then we look for k subsets whose target sum will be k
            we keep a memo array to store if we included this index in our subset already to make a decision of using an index for our getSubset
            we keep incrementing currsum += nums[j] where j=start to nums.length if currsum + nums[j] < target & set memo[j] as true and backtrack from j+1 as start to find other part of subset,

    whenever currsum= target we will search from 0, nums.length to find k-1 subsets as we found 1 already
    as we need to backtrack to try if our current combination didnot work and set memo back as false and remove it from currsum as currsum -= nums[j]; */

    public boolean kPartitionSum(int[] arr, int k) {
       int sum = Arrays.stream(arr).sum();
       if(sum%k  != 0)
           return false;
       int targetSum  = sum/k;
       boolean[] memo = new boolean[arr.length];
       Arrays.fill(memo, false);
       Arrays.sort(arr);
       reverse(arr);
       return backtrack(arr, 0, 0,k, targetSum, memo);
    }

    public void reverse(int[] arr){
        int left=0;
        int right=arr.length-1;
        while(right >= left) {
            int temp = arr[right];
            arr[right--] = arr[left];
            arr[left++] = temp;
        }
    }

    public boolean backtrack(int[] arr, int start, int currsum, int subsetsNeeded,int targetSum , boolean[] memo) {
        if(subsetsNeeded == 0)
            return true;
        if(currsum > targetSum)
            return false;
        if(currsum == targetSum )
            return backtrack(arr, 0,0,subsetsNeeded-1, targetSum, memo);
        if(start >= arr.length)
            return false;
        for(int j=start; j<arr.length;j++) {
            if(memo[j] == true || currsum+arr[j] > targetSum)
                continue;
            currsum += arr[j];
            memo[j] = true; // we are adidng j to our current subset and trying to find remsumf or our current subset
            if(backtrack(arr, j+1,currsum, subsetsNeeded, targetSum, memo))
                return true;
            memo[j] = false;
            currsum -= arr[j];
        }
            //we formed target sum so reduce subsets needed to form
          return false;
    }


}
