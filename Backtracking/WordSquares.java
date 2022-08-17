package Backtracking;

/* Given an array of unique strings words, return all the word squares you can build from words. The same word from words can be used multiple times. You can return the answer in any order.

        A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).

        For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.


        Example 1:

        Input: words = ["area","lead","wall","lady","ball"]
        Output: [["ball","area","lead","lady"],["wall","area","lead","lady"]]
        Explanation:
        The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
*/
import java.util.*;
public class WordSquares {
    //the matrix should be symmetric across the diagonal for every row in nw col it should match the prefix of ( words char at row index) for all words in 0 to row-1 , we backtrack based on this logic by computing prefixcountmap

        List<List<String>> result = new ArrayList<>();
        int N;
        public List<List<String>> wordSquares(String[] words) {
            HashMap<String, List<String>> prefixesWordsMap = new HashMap<>();

            constructPrefixes(words, prefixesWordsMap);
            if(words.length < 1)
                return result;

            N= words[0].length();
            LinkedList<String> wordSquares = new LinkedList<>();
            for(String word: words) {
                wordSquares.addLast(word);
                backtrack(wordSquares, 1, prefixesWordsMap);
                wordSquares.clear();
            }
            return result;
        }

        public HashMap<String, List<String>> constructPrefixes(String[] words,HashMap<String, List<String>> prefixesWordsMap
        ) {
            int N = words[0].length();
            for(String word: words) {
                for(int i=1;i<N;i++) {
                    String prefix = word.substring(0,i);
                    prefixesWordsMap.computeIfAbsent(prefix, v -> new ArrayList<>()).add(word);

                }
            }
            return prefixesWordsMap;
        }


        public void backtrack(LinkedList<String> wordSquares, int steps,HashMap<String, List<String>> prefixesWordsMap) {
            if(steps >= N) {
                result.add(new ArrayList<>(wordSquares));
                return;
            }
            StringBuilder prefix = new StringBuilder();
            for(String word: wordSquares) {
                prefix.append(word.charAt(steps));
            }
            List<String> wordsList = prefixesWordsMap.getOrDefault(prefix.toString(), new ArrayList<>());
            for(String worde: wordsList) {
                wordSquares.addLast(worde);
                backtrack(wordSquares, steps+1, prefixesWordsMap);
                wordSquares.removeLast();
            }

            return;
        }


}
