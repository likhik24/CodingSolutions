package HashMap;
import java.util.*;
public class GuessTheWord {
    interface Master {
        int guess(String word) ;
    }
//    This is an interactive problem.
//
//    You are given an array of unique strings wordlist where wordlist[i] is 6 letters long, and one word in this list is chosen as secret.
//
//    You may call Master.guess(word) to guess a word. The guessed word should have type string and must be from the original list with 6 lowercase letters.
//
//    This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word. Also, if your guess is not in the given wordlist, it will return -1 instead.
//
//    For each test case, you have exactly 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or fewer calls to Master.guess and at least one of these guesses was secret, then you pass the test case.
//
//    Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
public void findSecretWord(String[] wordlist, Master master) {
    HashSet<String> wordsSet = new HashSet<>(Arrays.asList(wordlist));
    int numOfGuesses = 10;
    while (numOfGuesses > 0) {
        String word = wordsSet.iterator().next();
        int matchedLetters = master.guess(word);
        numOfGuesses -= 1;
        Set<String> wordsToRemove = new HashSet<>();
        if (matchedLetters == 6)
            return;
        else if (matchedLetters == 0) {
            for (String word1 : wordsSet) {

                for (int i = 0; i < 6; i++) {
                    if (word.charAt(i) == word1.charAt(i)) {
                        wordsToRemove.add(word1);
                        break;
                    }
                }

            }

        } else {

            for (String word1 : wordsSet) {
                int tempGuesses = 0;
                for (int i = 0; i < 6; i++) {
                    if (word.charAt(i) == word1.charAt(i))
                        tempGuesses++;
                }
                if (tempGuesses != matchedLetters)
                    wordsToRemove.add(word1);
            }
        }
        wordsSet.removeAll(wordsToRemove);
;
    }
}



}
