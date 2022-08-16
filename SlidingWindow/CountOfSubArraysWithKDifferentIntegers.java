package SlidingWindow;

public class CountOfSubArraysWithKDifferentIntegers {
    public int subarraysWithKDistinct(int[] nums, int k) {

        return atMost(k,nums) - atMost(k - 1,nums);
    }

    private int atMost(int k, int[] nums){
        int left = 0, right = 0;
        int[] map = new int[nums.length + 1];
        int cnt = 0;
        int res = 0;

        while(right < nums.length){

            if(map[nums[right]] == 0) cnt++;
            map[nums[right]]++;

            //invalid
            while(cnt > k){
                map[nums[left]]--;
                if(map[nums[left]] == 0) cnt--;
                left++;
            }

            //valid => save
            res = res + right - left;

            right++;

        }

        return res;
    }
}
