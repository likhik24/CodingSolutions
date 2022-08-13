package Strings;


//Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.

//
//
//        Example 1:
//
//        Input: s = "abab"
//        Output: true
//        Explanation: It is the substring "ab" twice.
//        Example 2:
//
//        Input: s = "aba"
//        Output: false
//        Example 3:
//
//        Input: s = "abcabcabcabc"
//        Output: true
//        Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
public class RepeatedSubStringPattern {
//    This is very good problem and I can see it potentially used in many other places.
//
//    So let's say we have a string s that is composed of three repeating substrings. s = PatternPattern
//    s+s = PatternPatternPatternPattern
//    In the above string, the first occurence of s will occur at position 0.
//    If start searching from position 1 of the concatenated string, then the first occurence of s will occur no later than index s.length().
//    However if s is composed of a repeating substring then the first occurence of s in s+s is guaranteed to occur prior to s.length() even if we start from position 1. Why?
//    Proof-
//
//    Imagine s having n blocks of size k where k = length of repeating substring. In other words s - n * k
//    s+s = n * k + n * k
//    By removing the first character in s+s we reduce it to s' + (n-1) * k + n * k. This can be written as s' + n * k + (n-1) * k = s' + s + (n-1) * k
//    Since len(s') < len(s) thus index of s is less than len(s)

        public boolean repeatedSubstringPattern(String s) {
            return (s+s).indexOf(s, 1) < s.length();

    }

}
