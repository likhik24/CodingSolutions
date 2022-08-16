/*
You are playing the Bulls and Cows game with your friend.

You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:

The number of "bulls", which are digits in the guess that are in the correct position.
The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.

The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.



Example 1:

Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1807"
  |
"7810"
Example 2:

Input: secret = "1123", guess = "0111"
Output: "1A1B"

Here the trick is there can be duplicate characters which are counted as cows earlier but are matched later with same position , then we decrement cows count by looking at hashmap to see if its count is less
//else decrement matched char count in map
 */
import java.util.*;

public class BullsAndCows {
      public String getHint(String secret, String guess) {
            char ch[] = secret.toCharArray();
            HashMap< Character, Integer> map = new HashMap<>();
            char[] guessarr = guess.toCharArray();
            StringBuilder res = new StringBuilder();
            int bulls = 0;
            int cows = 0;
            for(int i=0;i<ch.length;i++)
                map.put(ch[i], map.getOrDefault(ch[i],0)+1);

            for(int i=0;i<guessarr.length;i++) {
                if(guessarr[i] == ch[i]) {
                    if(map.get(guessarr[i]) <=0) {
                        cows--;
                    }
                    else {
                        map.put(guessarr[i],  map.get(guessarr[i])-1);
                    }
                    bulls++;
                }
                else if(map.containsKey(guessarr[i]) && map.get(guessarr[i])>0) {
                    cows++;
                    map.put(guessarr[i],  map.get(guessarr[i])-1);
                }
            }
            res.append(bulls);
            res.append("A");
            res.append(cows);
            res.append("B");
            return String.valueOf(res);
        }


}
