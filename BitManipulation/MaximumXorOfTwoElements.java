package BitManipulation;
import java.util.*;
//we need to find maximum xor of two elements in an array
public class MaximumXorOfTwoElements {

//     Approach 1: Bitwise Prefixes in HashSet
// Let's start from rewriting all numbers [3, 10, 5, 25, 2, 8] in binary from

// 3 = (00011)

// 10 = (01010)

// 5 = (00101)

// 25 = (11001)

// 2 = (00010)

// 8 = (01000)


// To simplify the work with prefixes, better to use the same number of bits LL for all the numbers. It's enough to take LL equal to the length of the max number in the binary representation.

// Now let's construct the max XOR starting from the leftmost bit. The absolute maximum one could have with L = 5L=5 bits here is (11111)_2(11111)
// 2
// ​
//  . So let's check bit by bit:

// Could we have the leftmost bit for XOR to be equal to 1-bit, i.e. max XOR to be equal to (1****)_2(1∗∗∗∗)
// 2
// ​
//  ?
// Yes, for that it's enough to pair 25 = (11001)_225=(11001)
// 2
// ​
//   with another number starting with the zero leftmost bit. So the max XOR is (1****)_2(1∗∗∗∗)
// 2
// ​
//  .

// Next step. Could we have max XOR to be equal to (11***)_2(11∗∗∗)
// 2
// ​
//  ?
// For that, let's consider all prefixes of length 2 and check if there is a pair of them, p_1p
// 1
// ​
//   and p_2p
// 2
// ​
//  , such that its XOR is equal to 11: p_1 \oplus p_2 == 11p
// 1
// ​
//  ⊕p
// 2
// ​
//  ==11

// 3 = (00***)_23=(00∗∗∗)
// 2
// ​


// 10 = (01***)_210=(01∗∗∗)
// 2
// ​


// 5 = (00***)_25=(00∗∗∗)
// 2
// ​


// 25 = (11***)_225=(11∗∗∗)
// 2
// ​


// 2 = (00***)_22=(00∗∗∗)
// 2
// ​


// 8 = (01***)_28=(01∗∗∗)
// 2
// ​


// Yes, it's the case, for example, pair 5 = (00***)_25=(00∗∗∗)
// 2
// ​
//   and 25 = (11***)_225=(11∗∗∗)
// 2
// ​
//  , or 2 = (00***)_22=(00∗∗∗)
// 2
// ​
//   and 25 = (11***)_225=(11∗∗∗)
// 2
// ​
//  , or 3 = (00***)_23=(00∗∗∗)
// 2
// ​
//   and 25 = (11***)_225=(11∗∗∗)
// 2
// ​
//  .

// And so on, and so forth. The complexity remains linear. One has to perform NN operations to compute prefixes, though the number of prefixes containing L - iL−i bits could not be greater than 2^{L - i}2
// L−i
//  . Hence the check if XOR could have the ith bit to be equal to 1-bit takes 2^{L - i} \times 2^{L - i}2
// L−i
//  ×2
// L−i
//   operations.

// Algorithm

// Compute the number of bits LL to be used. It's a length of max number in binary representation.

// Initiate max_xor = 0.

// Loop from i = L - 1i=L−1 down to i = 0i=0 (from the leftmost bit L - 1L−1 to the rightmost bit 0):

// Left shift the max_xor to free the next bit.

// Initiate variable curr_xor = max_xor | 1 by setting 1 in the rightmost bit of max_xor. Now let's check if curr_xor could be done using available prefixes.

// Compute all possible prefixes of length L - iL−i by iterating over nums.

// Put in the hashset prefixes the prefix of the current number of the length L - iL−i: num >> i.
// Iterate over all prefixes and check if curr_xor could be done using two of them: p1^p2 == curr_xor. Using self-inverse property of XOR p1^p2^p2 = p1, one could rewrite it as p1 == curr_xor^p2 and simply check for each p if curr_xor^p is in prefixes. If so, set max_xor to be equal to curr_xor, i.e. set 1-bit in the rightmost bit. Otherwise, let max_xor keep 0-bit in the rightmost bit.

        // Return max_xor.
        public int findMaximumXOR(int[] nums) {

            int maxNum = nums[0];
            for(int num : nums) maxNum = Math.max(maxNum, num);
            // length of max number in a binary representation
            int L = (Integer.toBinaryString(maxNum)).length();


            int maxXor = 0;
            int currXor;
            Set<Integer> prefixes = new HashSet<>();
            //we start to find from left most bit that is most signifcant bit which can be xored to 1 and then continue to find other bits which can e xored to 1, we start shifting characters by L-i length to get the leftmost significant bit and compare them to get most number of 1's in the XOR, maxXor contains most number of 1s we found , currXor contains curr max number of 1s we have

            for(int i=L-1; i>=0;i--) {
                maxXor <<= 1; // this is initially 0 then it becomes 2 if most signifcant digit matches then 110 then 1110
                prefixes.clear();
                currXor = maxXor | 1; // this will convert maxxor to 01, then 11, then 111 then 1111 and so on as we move from most significant to least significant bits
                System.out.println(maxXor + " " + currXor);
                for(int num: nums) { prefixes.add(num >>i );}
                for(int p:prefixes) {
                    if(prefixes.contains(currXor^p)) {
                        maxXor = currXor;
                        break;
                    }
                }
            }
            return maxXor;
        }

}
