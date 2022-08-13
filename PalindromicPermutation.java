import java.util.*;

public class PalindromicPermutation {
//    Given a string s, return all the palindromic permutations (without duplicates) of it.

//    You may return the answer in any order. If s has no palindromic permutation, return an empty list.
//
//
//            Example 1:
//
//    Input: s = "aabb"
//    Output: ["abba","baab"]
//    Example 2:
//
//    Input: s = "abc"
//    Output: []

//    It might be possible that no palindromic permutation could be possible for the given string ss.
//    Thus, it is useless to generate the permutations in such a case. Taking this idea,
//    firstly we check if a palindromic permutation is possible for ss. If yes, then only we proceed further with generating the permutations.
//    To check this, we make use of a hashmap mapmap which stores the number of occurences of each character(out of 128 ASCII characters possible).
//    If the number of characters with odd number of occurences exceeds 1, it indicates that no palindromic permutation is possible for ss.
//    To look at this checking process in detail, look at Approach 4 of the article for Palindrome Permutation.
//
//    Once we are sure that a palindromic permutation is possible for ss, we go for the generation of the required permutations.
//    But, instead of wildly generating all the permutations, we can include some smartness in the generation of permutations i.e. we can generate only those permutations which are already palindromes.
//
//    One idea to to do so is to generate only the first half of the palindromic string and to append its reverse string to itself to generate the full length palindromic string.
//
//    Based on this idea, by making use of the number of occurences of the characters in ss stored in mapmap,
//    we create a string stst which contains all the characters of ss but with the number of occurences of these characters in stst reduced to half their original number of occurences in ss.
//
//    Thus, now we can generate all the permutations of this string stst and append the reverse of this permuted string to itself to create the palindromic permutations of ss.
//
//            In case of a string ss with odd length, whose palindromic permutations are possible, one of the characters in ss must be occuring an odd number of times.
//            We keep a track of this character, chch, and it is kept separte from the string stst. We again generate the permutations for stst similarly and a
//            ppend the reverse of the generated permutation to itself, but we also place the character chch at the middle of the generated string.
//
//            In this way, only the required palindromic permutations will be generated. Even if we go with the above idea, a lot of duplicate strings will be generated.
//
//    In order to avoid generating duplicate palindromic permutations in the first place itself, as much as possible, we can make use of this idea.
//    As discussed in the last approach, we swap the current element with all the elements lying towards its right to generate the permutations. Before swapping, we can check if the elements being swapped are equal. If so, the permutations generated even after swapping the two will be duplicates(redundant). Thus, we need not proceed further in such a case.
//
//    Time complexity : O(n/2+1)!
//
//
    Set < String > set = new HashSet < > ();
    public List < String > generatePalindromes(String s) {
        int[] map = new int[128];
        char[] st = new char[s.length() / 2];
        if (!canPermutePalindrome(s, map))
            return new ArrayList < > ();
        char ch = 0;
        int k = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] % 2 == 1)
                ch = (char) i;
            for (int j = 0; j < map[i] / 2; j++) {
                st[k++] = (char) i;
            }
        }
        permute(st, 0, ch);
        return new ArrayList < String > (set);
    }
    public boolean canPermutePalindrome(String s, int[] map) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
    }
    public void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
    void permute(char[] s, int l, char ch) {
        if (l == s.length) {
            set.add(new String(s) + (ch == 0 ? "" : ch) + new StringBuffer(new String(s)).reverse());
        } else {
            for (int i = l; i < s.length; i++) {
                if (s[l] != s[i] || l == i) {
                    swap(s, l, i);
                    permute(s, l + 1, ch);
                    swap(s, l, i);
                }
            }
        }
    }

}
