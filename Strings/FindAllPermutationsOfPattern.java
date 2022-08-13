package Strings;


import java.sql.Time;


//        The idea is to modify Rabin Karp Algorithm. For example, we can keep the hash value as sum of ASCII values of all characters under modulo of a big prime number. For every character of text, we can add the current character to hash value and subtract the first character of previous window. This solution looks good, but like standard Rabin Karp, the worst case time complexity of this solution is O(mn). The worst case occurs when all hash values match and we one by one match all characters.
//
//        We can achieve O(n) time complexity under the assumption that alphabet size is fixed which is typically true as we have maximum 256 possible characters in ASCII. The idea is to use two count arrays:
//
//        The first count array store frequencies of characters in pattern.
//        The second count array stores frequencies of characters in current window of text.
//        The important thing to note is, time complexity to compare two count arrays is O(1) as the number of elements in them are fixed (independent of pattern and text sizes). Following are steps of this algorithm.
//
//        Store counts of frequencies of pattern in first count array countP[]. Also store counts of frequencies of characters in first window of text in array countTW[].
//        Now run a loop from i = M to N-1. Do following in loop.
//        If the two count arrays are identical, we found an occurrence.
//        Increment count of current character of text in countTW[]
//        Decrement count of first character in previous window in countWT[]
//        The last window is not checked by above loop, so explicitly check it.
public class FindAllPermutationsOfPattern {
        static final int MAX = 256;

        // This function returns true if contents
        // of arr1[] and arr2[] are same, otherwise
        // false.
        static boolean compare(char arr1[], char arr2[])
        {
            for (int i = 0; i < MAX; i++)
                if (arr1[i] != arr2[i])
                    return false;
            return true;
        }

        // This function search for all permutations
        // of pat[] in txt[]
        static void search(String pat, String txt)
        {
            int M = pat.length();
            int N = txt.length();

            // countP[]:  Store count of all
            // characters of pattern
            // countTW[]: Store count of current
            // window of text
            char[] countP = new char[MAX];
            char[] countTW = new char[MAX];
            for (int i = 0; i < M; i++)
            {
                (countP[pat.charAt(i)])++;
                (countTW[txt.charAt(i)])++;
            }

            // Traverse through remaining characters
            // of pattern
            for (int i = M; i < N; i++)
            {
                // Compare counts of current window
                // of text with counts of pattern[]
                if (compare(countP, countTW))
                    System.out.println("Found at Index " +
                            (i - M));

                // Add current character to current
                // window
                (countTW[txt.charAt(i)])++;

                // Remove the first character of previous
                // window
                countTW[txt.charAt(i-M)]--;
            }

            // Check for the last window in text
            if (compare(countP, countTW))
                System.out.println("Found at Index " +
                        (N - M));
        }

        /* Driver program to test above function */
        public static void main(String args[])
        {
            String txt = "BACDGABCDA";
            String pat = "ABCD";
            search(pat, txt);
        }
    }

//    Output
//    Found at Index 0
//    Found at Index 5
//    Found at Index 6
//    Time Complexity: O(m), where m is 256
//
//    Auxiliary space: O(m), where m is 256

