package Arrays;
/*
If there is a word array/list, please find the word lists that contains all the words that can composite another word in the origin word array. E.g.

If the input word array/list is:
['car','super','supercar','hero','superhero','superherocar','spring','flower','springflower','winter','cool']

Please return me the array as below:
[['car','super', 'supercar'],
['super', 'hero', 'superhero'],
['car','super','hero','superherocar'],
['spring', 'flower','springflow']]

Variation of https://leetcode.com/problems/concatenated-words

 */
import java.util.*;

public class FindConcatenatedWords {
    public HashMap<String, ArrayList<ArrayList<String>>> concatenatedWords(String[] words) {
        TreeSet<String> wordsSet = new TreeSet<>();
        HashMap<String, ArrayList<ArrayList<String>>> compositeWordsMap = new HashMap<>();

        for(String word:words) {
            wordsSet.add(word);
        }
        ArrayList<ArrayList<String>> k = compositeWordsMap.get("dfg");
        for(String word:words) {
            wordsSet.remove(word);
            findCompositeWords(word, new ArrayList<>(), word, wordsSet, compositeWordsMap);
            wordsSet.add(word);
        }

       return compositeWordsMap;
    }


    public boolean findCompositeWords(String word, ArrayList<String> result, String originalWord,TreeSet<String> wordsSet,HashMap<String, ArrayList<ArrayList<String>>> compositeWordsMap ) {
        if(word.length() == 0 && result.size()>1) {
            ArrayList<ArrayList<String>> subres = compositeWordsMap.getOrDefault(originalWord, new ArrayList<>());
            subres.add(new ArrayList<>(result));
            result.clear();
            compositeWordsMap.put(originalWord, subres);
            return true;
        }
        for(int i=1;i<=word.length();i++) {
            if(wordsSet.contains(word.substring(0,i))) {
                result.add(word.substring(0,i)); //w
                findCompositeWords(word.substring(i), result, originalWord, wordsSet, compositeWordsMap);
               
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FindConcatenatedWords words  = new FindConcatenatedWords();
        HashMap<String, ArrayList<ArrayList<String>>> compositeWordsMap = words.concatenatedWords(new String[]{"car","super","supercar","hero","superhero","superherocar","spring","flower","springflower","winter","cool"});
        for(Map.Entry<String,ArrayList<ArrayList<String>>> entry: compositeWordsMap.entrySet()) {
            ArrayList<ArrayList<String>> subre= entry.getValue();
            System.out.print(" words for key " + entry.getKey());
            System.out.println();
            for(ArrayList<String> re : subre) {
                for(String s: re)
                    System.out.print(s + " , ");
                System.out.println();
            }
        }
    }
}
