package Strings;
import java.util.regex.Pattern;
/*
Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).



Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true

 */
public class RegularExpressionMatching {

    enum Result {
        TRUE,
        FALSE;
    }

        Result memo[][];
        public boolean isMatch(String s, String p) {
            memo = new Result[s.length()+1][p.length()+1];
            return dp(0,0, s,p);
        }
          //two choices for when * is there otherwise we can just match schar, pchar and move forward ,if * is there we can treat it  as a prev character and proceed to compare schar, pchar provided chars until schar,pchar matched, or
          // (we treat * as char before it so we move pchar to pchar-1 and match(schar, pchar)
        public boolean dp(int sindex, int pindex, String text, String pattern) {
            boolean ans;

            if(memo[sindex][pindex] != null)
                return memo[sindex][pindex] == Result.TRUE;
            if (pindex == pattern.length()){
                ans = sindex == text.length();
            }
            else {
                boolean firstMatch = sindex < text.length() && (pattern.charAt(pindex) == '.' || text.charAt(sindex) == pattern.charAt(pindex));
                if(pindex + 1 < pattern.length() && pattern.charAt(pindex+1) == '*') {
                    ans = ( firstMatch && dp(sindex+1, pindex, text, pattern)) || dp(sindex, pindex+2, text, pattern);
                }
                else {
                    ans = firstMatch && dp(sindex+1, pindex+1, text, pattern);
                }
            }
            memo[sindex][pindex] = ans ? Result.TRUE : Result.FALSE;
            return ans;
        }

}
