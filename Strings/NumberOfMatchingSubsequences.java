package Strings;
import java.util.*;
/*
Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

        A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

        For example, "ace" is a subsequence of "abcde".


        Example 1:

        Input: s = "abcde", words = ["a","bb","acd","ace"]
        Output: 3
        Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
        Example 2:

        Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
        Output: 2

Approach #2: Next Letter Pointers [Accepted]
Intuition

Since the length of S is large, let's think about ways to iterate through S only once, instead of many times as in the brute force solution.

We can put words into buckets by starting character. If for example we have words = ['dog', 'cat', 'cop'], then we can group them 'c' : ('cat', 'cop'), 'd' : ('dog',). This groups words by what letter they are currently waiting for. Then, while iterating through letters of S, we will move our words through different buckets.

For example, if we have a string like S = 'dcaog':

heads = 'c' : ('cat', 'cop'), 'd' : ('dog',) at beginning;
heads = 'c' : ('cat', 'cop'), 'o' : ('og',) after S[0] = 'd';
heads = 'a' : ('at',), 'o' : ('og', 'op') after S[0] = 'c';
heads = 'o' : ('og', 'op'), 't': ('t',) after S[0] = 'a';
heads = 'g' : ('g',), 'p': ('p',), 't': ('t',) after S[0] = 'o';
heads = 'p': ('p',), 't': ('t',) after S[0] = 'g';
Algorithm

Instead of a dictionary, we'll use an array heads of length 26, one entry for each letter of the alphabet. For each letter in S, we'll take all the words waiting for that letter, and have them wait for the next letter in that word. If we use the last letter of some word, it adds 1 to the answer.

For another description of this algorithm and a more concise implementation, please see @StefanPochmann's excellent forum post here.
timecomplexity:O(word.length+ sum of all words.length)
*/

public class NumberOfMatchingSubsequences {

        class Node {
            int index;
            String value;
            Node(int index, String value){
                this.value = value;
                this.index = index;
            }
        }

        public int numMatchingSubseq(String s, String[] words) {
            HashMap<Character, ArrayList<Node>> heads = new HashMap<>();
            for(char c='a';c<='z';c++)
                heads.put(c, new ArrayList<>());
            for(int i=0;i<words.length;i++) {
                String word = words[i];
                ArrayList<Node> currChars = heads.get(word.charAt(0));
                currChars.add(new Node(i,word.substring(1)));
                heads.put(word.charAt(0), currChars);
            }
            char[] textToMatch = s.toCharArray();
            int count=0;
            //if the current char in text matches to any nodes first char then we will update nodes of that index
            for(int i=0;i<textToMatch.length;i++) {
                char ch = textToMatch[i];
                ArrayList<Node> nodes = heads.get(ch);
                heads.put(ch, new ArrayList<>());
                for(Node node:nodes) {
                    if(node.value.length() == 0) {
                        count++;
                        continue;
                    }

                    char newKey = node.value.charAt(0);
                    if(node.value.length() >= textToMatch.length-i)
                        continue;
                    node.value = node.value.length() > 1 ? node.value.substring(1) : "";
                    ArrayList<Node> currChars = heads.get(newKey);
                    currChars.add(node);
                    heads.put(newKey, currChars);

                }
            }
            return count;
        }

}
