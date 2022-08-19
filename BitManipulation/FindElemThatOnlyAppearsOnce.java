package BitManipulation;

public class FindElemThatOnlyAppearsOnce {
    /*arr is not sorted , we need to maintain xor and and of elems to check if elem is occured for first time
    Run a loop for all elements in the array. At the end of every iteration, maintain the following two values.
    ones: The bits that have appeared 1st time or 4th time or 7th time .. etc.
            twos: The bits that have appeared 2nd time or 5th time or 8th time .. etc.
            Finally, we return the value of ‘ones’
            */

    public int elementFind(int[] arr) {
        int ones=0;
        int twos=0;
        for(int i=0;i<arr.length;i++) {

           /*"one & arr[i]" gives the bits that are there in
            both 'ones' and new element from arr[]. We
            add these bits to 'twos' using bitwise OR*/
            twos = twos | (ones & arr[i]);

            /*"one & arr[i]" gives the bits that are
            there in both 'ones' and new element from arr[].
            We add these bits to 'twos' using bitwise OR*/
            ones = ones ^ arr[i];

            /* The common bits are those bits which appear third time
            So these bits should not be there in both 'ones' and 'twos'.
            common_bit_mask contains all these bits as 0, so that the bits can
            be removed from 'ones' and 'twos'*/
            int common_bit_mask = ~(ones & twos);
           ones &= common_bit_mask;
            /*Remove common bits (the bits that appear third time) from 'twos'*/
            twos &= common_bit_mask;
        }
        return ones;
    }

}
