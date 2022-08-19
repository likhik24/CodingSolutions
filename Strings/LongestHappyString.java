package Strings;
/*
A string s is called happy if it satisfies the following conditions:

s only contains the letters 'a', 'b', and 'c'.
s does not contain any of "aaa", "bbb", or "ccc" as a substring.
s contains at most a occurrences of the letter 'a'.
s contains at most b occurrences of the letter 'b'.
s contains at most c occurrences of the letter 'c'.
Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".

A substring is a contiguous sequence of characters within a string.



Example 1:

Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.
Example 2:

Input: a = 7, b = 1, c = 0
Output: "aabaa"
 */
public class LongestHappyString {
     public String generate(int a, int b, int c, String a1, String b1, String c1) {
            if(a <b) //we will keep alway aa as highest count to replace max as before taking another character to use to not form AAA substring
                return generate(b,a,c,b1,a1,c1);
            if(b<c) // we take b has second highest counts to use as buffer to put max chars from a
                return generate(a,c,b,a1,c1,b1);
            if (b == 0)
                return a1.repeat(Math.min(2, a));
            int use_a = Math.min(a,2); int use_b = a-use_a >= b?1: 0;
            return a1.repeat(use_a)+b1.repeat(use_b)+generate(a-use_a, b-use_b,c,a1,b1,c1);

        }

        public String longestDiverseString(int a, int b, int c) {
            return generate(a,b,c,"a","b","c");
        }

}
