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
    public  ArrayList<ArrayList<String>> concatenatedWords(String[] words) {
        TreeSet<String> wordsSet = new TreeSet<>();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
      
        for(String word:words) {
            wordsSet.add(word);
        }
        for(String word: words) {
            int length = word.length();
            boolean[] dp = new boolean[length+1];
            dp[0] = true; 
            HashSet<String> subwords = new HashSet<>();
            for(int i=1;i<=length;i++) {
               
                for(int j=0; j<i ;j++ ) {
                    if(dp[j] && wordsSet.contains(word.substring(j,i))) {
                    
                        subwords.add(word.substring(j,i));
                        dp[i] = true;
                    }
                }  
            }
            if(dp[word.length()]) {
                subwords.add(word);
                if(subwords.size() > 1)
                result.add(new ArrayList<>(subwords));
            }
        }
        
        return result;
    

    }


    public static void main(String[] args) {
        FindConcatenatedWords words  = new FindConcatenatedWords();
        ArrayList<ArrayList<String>> compositeWordsList = words.concatenatedWords(new String[]{"car","super","supercar","hero","superhero","superherocar","spring","flower","springflower","winter","cool"});
       
        int index=0;
        for(ArrayList<String> subre: compositeWordsList) {
             System.out.println("wordList at " + index++);
             for(String s: subre)
              System.out.println(s);
        }
    }
}
