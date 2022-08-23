package BitManipulation;
/*
You are given an integer array nums of length n and an integer numSlots such that 2 * numSlots >= n. There are numSlots slots numbered from 1 to numSlots.

You have to place all n integers into the slots such that each slot contains at most two numbers. The AND sum of a given placement is the sum of the bitwise AND of every number with its respective slot number.

For example, the AND sum of placing the numbers [1, 3] into slot 1 and [4, 6] into slot 2 is equal to (1 AND 1) + (3 AND 1) + (4 AND 2) + (6 AND 2) = 1 + 1 + 0 + 2 = 4.
Return the maximum possible AND sum of nums given numSlots slots.



Example 1:

Input: nums = [1,2,3,4,5,6], numSlots = 3
Output: 9
Explanation: One possible placement is [1, 4] into slot 1, [2, 6] into slot 2, and [3, 5] into slot 3.
This gives the maximum AND sum of (1 AND 1) + (4 AND 1) + (2 AND 2) + (6 AND 2) + (3 AND 3) + (5 AND 3) = 1 + 0 + 2 + 2 + 3 + 1 = 9.
Example 2:

Input: nums = [1,3,10,4,7,1], numSlots = 9
Output: 24
Explanation: One possible placement is [1, 1] into slot 1, [3] into slot 3, [4] into slot 4, [7] into slot 7, and [10] into slot 9.
This gives the maximum AND sum of (1 AND 1) + (1 AND 1) + (3 AND 3) + (4 AND 4) + (7 AND 7) + (10 AND 9) = 1 + 1 + 3 + 4 + 7 + 8 = 24.
Note that slots 2, 5, 6, and 8 are empty which is permitted.


Constraints:

n == nums.length
1 <= numSlots <= 9
1 <= n <= 2 * numSlots
1 <= nums[i] <= 15
 */
public class MaximumAndSumArray {
    // we want to do bit masking
    //consider a slot as room if they are 5 slots we represent it as 5 digit number  22222
    // each digit index showing number of numbers selected for that slot index,
    // eg:slot 1 has 2 numbers , slot 2 has 2 numbers tc., if we allocated slot 3 to a number then we decrement the number to 22122 ,
    // this can be formed by subtracting 10^(slot-1) from 22222 10^(3-1) = 100 ,
    // if we put another number then we decrement another time 100 22022 ,to check if a lsot is available we can do slot//base % 10 == 0  to check if its available 22122/100 = 221 , now 221 % 10 = 1 that means slot is available

    public int maximumANDSum(int[] nums, int numSlots) {

        StringBuilder sb = new StringBuilder();
        for(int i=1 ; i<=numSlots;i++)
            sb.append(2);
        return dp(nums, Integer.parseInt(String.valueOf(sb)), numSlots, 0);
    }

    public int dp(int[] nums, int rooms, int numSlots, int index) {
        if(index == nums.length)
            return 0;
        int res=0;
        for(int slot=1;slot<=numSlots;slot++) {
            int base = (int)Math.pow(10, (slot-1));

            if((rooms/base) % 10 > 0)
                res = Math.max(res, (nums[index]&slot)+dp(nums, rooms-base, numSlots, index+1));
        }
        return res;
    }

   /* /numofslots is 7 we creatr string with 7 2s at each index we can select at most 2 slots
    for(int i=0;i<numSlots;i++) {
        //we will try placing number in this slot
        //so the base is math.pow(10,i)
        if(rooms%base != 0) {
        res -= base;
        result = Math.max(result, nums[index]&i + dp(nums, index+1, numSlots, res(rooms))
        every num can take any slot and for next numbers we need to decrement slot number we can try all combinations of placing num
        }
    }
    return result; */
}
