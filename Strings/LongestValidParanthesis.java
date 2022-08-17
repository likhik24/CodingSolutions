package Strings;

public class LongestValidParanthesis {
    //paranthesis can be ( or )
    // if at any point we have more closed pars it is invalid
    //     a valid string will always have ) char so we can have a dp to store length of highest valid string until htis index
    //     dp['('] = 0 as they wont have valid string
    //     if char is ) and i-1  = '(' we will do dp[i-2]+2;
    // if char is ) and i-1 is ) then we need to get start bracket
    //     of this bracket which will be dp[i-dp[i-1]-1] as dp[i-1] will have length of valid string until i-1 which can be k we need to go to i-k-1 index, so
    // if dp[i-dp[i-1]-1] = '(' then dp[i] = 2+dp[i-1]+ dp[i-dp[i-1]-2] to get the length before this index to find number of strngs valid until that point


        public int longestValidParentheses(String s) {
            int maxans = 0;
            int dp[] = new int[s.length()];
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    maxans = Math.max(maxans, dp[i]);
                }
            }
            return maxans;
        }

}
