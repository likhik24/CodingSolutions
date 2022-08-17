package BitManipulation;

public class NumberOfBitDifferences {
    public int bitDifferences(int[] nums) {
     //we need to get sum of bit differences in all num
     int sum=0;
     for(int i=0;i<nums.length;i++)   {
         for(int j=i+1;j<nums.length && nums[j] != nums[i];j++) {
             int xor = nums[i]^nums[j];
             sum += count1bits(xor)*2;
         }
     }
     return sum;
    }
    public int count1bits(int xor) {
        int count=0;
        for(int i=xor; i>0; i = i >>> 1)
            count += i&1;
        return count;
    }

}
