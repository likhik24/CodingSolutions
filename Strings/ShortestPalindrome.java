package Strings;

public class ShortestPalindrome {
        public String shortestPalindrome(String s) {
            StringBuilder sb = new StringBuilder();
            int n=s.length();
            int left=0;
            // we will take two pointers and iterate from left,r ight to find the largest possible substring on left which is a palindrome, then store the reverse of string form i,n which should be prefix and check shortest possible palindrome of (0,i)
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(left) == s.charAt(j))
                    left++;
            }
            if (left == n) {
                return s;
            }
            sb.append(s.substring(left));

            return  sb.reverse()+shortestPalindrome(s.substring(0,left)) + s.substring(left);

        }
//     Each iteration of \text{shortestPalindrome}shortestPalindrome is linear in size of substring and the maximum number of recursive calls can be n/2n/2 times as shown in the Intuition section.
// Let the time complexity of the algorithm be T(n). Since, at the each step for the worst case, the string can be divide into 2 parts and we require only one part for further computation. Hence, the time complexity for the worst case can be represented as : T(n)=T(n-2)+O(n) So, T(n) = O(n) + O(n-2) + O(n-4) + ... + O(1)T(n)=O(n)+O(n−2)+O(n−4)+...+O(1) which is O(n^2)
// O(n2) as each time string length is reduced by half n/2 and shortest palindrome takes constant time



}
