package SlidingWindow;
import java.util.*;

public class CountVowelsOfSubstring {
    HashSet<Character> vowels = new HashSet<>(List.of('a', 'e', 'i','o','u'));

    public int countVowelsOfSubstring(String word) {
        HashSet<Character> currentVowels = new HashSet<>();
        int totalCount = 0;

        for(int i=0;i<word.length()-vowels.size();i++) {
            currentVowels.clear();

            for(int j=i;j<word.length();j++) {
                if(!vowels.contains(word.charAt(j)))
                {
                    break;
                }
                else {
                    currentVowels.add(word.charAt(j));
                    if(currentVowels.size() == vowels.size())
                        totalCount++;
                }
            }

        }
        return totalCount;
    }

    //Optimized sliding window approach below

     /*   realize that brute-force would do for 100 characters, otherwise, this problem would be medium.

j mark the start of an "all-vowel" substring, and i is the current position. The window between k - 1 and i is the smallest window with all 5 vowels.

So, for each position i, we have k - j valid substrings. The picture below demonstrate it for "xxaiioueiiaxx" test case: */
    public int countVowelsSubStrings(String word) {
        int left=0;
        int right=0;
        int k=0;
        int count=0;
        int vowelsCount = 0;
        HashMap < Character, Integer > map = new HashMap < > ();
        map.put('a', 0);
        map.put('e', 0);
        map.put('i', 0);
        map.put('o', 0);
        map.put('u', 0);
        //we update count of chars in the map until we reach total vowel count of 5 and once vowel count is 5 ,
        // we try to decrement counts of current chars to see how many are repeating until vowelcount is maintained and add that count
        for(right=0;right<word.length();right++) {
            if(!map.containsKey(word.charAt(right))) {
                vowelsCount=0;
                map.forEach((key,v) -> map.put(key,0));
                k=left=right+1;
            }
            else {
                map.put(word.charAt(right), map.get(word.charAt(right))+1);
                if(map.get(word.charAt(right)) == 1)
                    ++vowelsCount;
                 for(; vowelsCount == 5;k++) {
                        map.put(word.charAt(k), map.get(word.charAt(k))-1);
                        if(map.get(word.charAt(k)) == 0)
                            --vowelsCount;
                 }
                   count += k-left;
            }
        }

        return count;

    }


    public static void main(String[] args) {
        CountVowelsOfSubstring countVowels = new CountVowelsOfSubstring();
        System.out.println(countVowels.countVowelsSubStrings("caieuouac"));
    }
}
