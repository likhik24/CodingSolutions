package DynamicProgramming;
import java.util.*;

public class StringCompression {
    /*
  Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".

    Notice that in this problem, we are not adding '1' after single characters.

    Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.

    Find the minimum length of the run-length encoded version of s after deleting at most k characters.



            Example 1:

    Input: s = "aaabcccd", k = 2
    Output: 4

      // we can take any char or discard the char and have two choices for every character, if leftk > 0 only we can discard the char otherwise that path needs to go through add char , when keeping char we need to increase count when the
    // numbers are 1,9,99 as we place the char in encoding when number is greater than 1 thereby increasing length, we increment keepchar when last char doesnot match our current char to count the all charcters we need to insert apart from encoding

    time complexity: //each recursive call takes O(1) time , time complexity is proporttional to dp state
    Each DP state is defined as (idx, lastchar,lascharcnt, k), so we can calculate the number of DP states as the product of the number of possible values for each parameter.
     There will be n possible values for idx, A possible values for lastchar, where A = 26A=26 is the size of the alphabet, n possible values for lastcharcnt, and k possible values for k. Thus, there are O(An^2k) possible DP states.
*/
       HashSet<Integer> numbersset = new HashSet<>(List.of(1,9,99));
        public int getLengthOfOptimalCompression(String s, int k) {
            //we can take string s or not take s
            int[][] dp = new int[s.length()][2];
            for(int[] dps: dp)
                Arrays.fill(dps, Integer.MAX_VALUE);
            dfs(dp, 0, k, s, (char)('a'+26),0);
            return Math.min(dp[0][0], dp[0][1]);
        }

        public int dfs(int[][] dp, int index, int leftK, String s, char lastChar, int lastCharCount ) {

            if(index == s.length()) {
                return 0;
            }
            if(leftK < 0)
                return Integer.MAX_VALUE;
            if(dp[index][0] != Integer.MAX_VALUE && dp[index][1] != Integer.MAX_VALUE) {
                return Math.min(dp[index][0], dp[index][1]);
            }


            int notKeepChar = dfs(dp, index+1, leftK-1, s, lastChar, lastCharCount);
            dp[index][0] = notKeepChar;

            //to keepchar
            int keepChar = 0;
            if(lastChar == s.charAt(index)) {
                int number = lastCharCount;
                if(numbersset.contains(number))
                    keepChar += 1;
                lastCharCount += 1;
            }
            else {
                keepChar += 1;
                lastChar = s.charAt(index);
                lastCharCount = 1;
            }



            dp[index][1] = keepChar+dfs(dp, index+1, leftK, s, lastChar, lastCharCount);
            return Math.min(dp[index][1], dp[index][0]);
        }



}
