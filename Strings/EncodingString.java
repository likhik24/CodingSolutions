package Strings;
//for encoding we can use the repeatedsubstringpattern problem to find if repeated substring of string exists and do encoding of that string

//if((s+s).indexOf(s,1) < s.length()) there is repeated substring s in s
//      if there is no repeatedsubstringpattern then we split string into two substrings for each i : 0 to n and encode each substring and combine them
//        base case: if s.length <= 4 we dont need to perform encoding as encoding takes min of 4 characters and doesnot reduce length

//First try solving Repeated substring pattern. I have a one line solution with comments here It's important you understand and solve this problem first. Although it has been marked as easy, I think the one line O(n) solution is medium.
//
//        This is a hard problem but if you have understood the above problem well, this will be much simpler.
//
//        If a String length is less than or equal to 4 it can't be shortened via encoding (see in-line comment)
//        Else if a string is composed of repeating substrings then we find the repeating substring, encode the string using the substring and return because this is the shortest encoding of the string.
//        Else, we'll need to split the string, encode the left and right and concatenate. The pivot for the split lies in the range [1, s.length]. We return the encoding having minimum length
//        By using memoization we can speed up the above exponential algorithm to o(n^3). We can memoize because left and right strings above will repeat across recrusion calls.

import java.util.*;

public class EncodingString {
    HashMap<String, String> memo = new HashMap<>();
    public String encodedString(String s) {
        return encodedDp(s);
        // return encodedRec(s);
    }
    public String encodedRec(String s) {
        if(s.length() <= 4)
            return s;
        if(!memo.containsKey(s)) {
            int index = (s + s).indexOf(s, 1);
            String e = index < s.length() ? (s.length() / index + "[" + encodedRec(s.substring(0, index)) + "]") : s;
                if (e.length() < s.length())
                    System.out.println("ok");
             else{
                for (int i = 1; i < s.length(); i++) {
                    String s1 = encodedRec(s.substring(0, i)) + encodedRec(s.substring(i));
                   if(s1.length() < s.length())
                      e = s1;
                }
            }
             memo.put(s,e);
        }
        return memo.get(s);

    }
    public String repeatedSubstringPattern(String s, int left, String[][] dp) {
        int index = (s+s).indexOf(s,1);
        if(index < s.length()) return s.length()/index + "[" + dp[left][left+index] + "]";
        return s;
    }

    public String encodedDp(String s) {
        String dp[][] = new String[s.length() + 1][s.length() + 1];
        int n = s.length();
        for (int len = 0; len <= n; len++) {
            for (int left = 0; left < n - len + 1; left++) {
                int right = left + len;
                dp[left][right] = s.substring(left, right);

                if (dp[left][right].length() <= 4)
                    continue;

                String encoding = repeatedSubstringPattern(dp[left][right], left, dp);
                if (encoding.length() < dp[left][right].length()) {
                    dp[left][right] = encoding;
                    continue;
                }

                for (int k = left; k < right; k++) {
                    String l = dp[left][k];
                    String r = dp[k][right];
                    if ((l + r).length() < dp[left][right].length())
                        dp[left][right] = l + r;
                }


            }
        }
        return dp[0][s.length()];
    }

    public static void main(String[] args) {
        EncodingString encoder = new EncodingString();
        System.out.println(encoder.encodedString("abcaaaaabcabca"));
    }
}
