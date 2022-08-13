package Backtracking;

import java.util.*;
//Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
//
//        A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
//
//
//
//
//        Example 1:
//
//        Input: digits = "23"
//        Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
//        Example 2:
//
//        Input: digits = ""
//        Output: []
//        Example 3:
//
//        Input: digits = "2"
//        Output: ["a","b","c"]
//Complexity Analysis
//
//        Time complexity: O(4^N* N), where N is the length of digits. Note that 4 in this expression is referring to the maximum value length in the hash map, and not to the length of the input.
//
//        The worst-case is where the input consists of only 7s and 9s. In that case, we have to explore 4 additional paths for every extra digit. Then, for each combination, it costs up to N to build the combination. This problem can be generalized to a scenario where numbers correspond with up to MM digits, in which case the time complexity would be O(M^N \cdot N)O(M
//        N
//        ⋅N). For the problem constraints, we're given, M = 4M=4, because of digits 7 and 9 having 4 letters each.
//
//        Space complexity: O(N), where N is the length of digits.
//
//        Not counting space used for the output, the extra space we use relative to input size is the space occupied by the recursion call stack. It will only go as deep as the number of digits in the input since whenever we reach that depth, we backtrack.
//
//        As the hash map does not grow as the inputs grows, it occupies O(1) space.



public class letterCombinationsOfPhoneNumber {
    List<String> combinations = new ArrayList<>();
    private Map<Character, String> letters = Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");

    public List<String> letterCombinations(String digits) {
        char[] digit = digits.toCharArray();
        if (digits.isEmpty() )
            return combinations;
        backtrack(digits, 0, new StringBuilder(), digits.length());
        return combinations;
    }

    public void backtrack(String digits, int digitIndex, StringBuilder curr, int resultLength) {
        if(curr.length() == resultLength)
        {
            combinations.add(String.valueOf(curr.toString()));

        }
        if(digitIndex < resultLength) {
            char[] possibleChars = letters.get(digits.charAt(digitIndex)).toCharArray();
            for(int i=0;i<possibleChars.length;i++) { // this loop iterates max of 4 times for 7 and 9 so 4 paths for each combination and if they are n digits it takes 4^N and each combination building takes up to N
                curr.append(possibleChars[i]);
                backtrack(digits,digitIndex+1, curr, resultLength);
                curr.deleteCharAt(curr.length()-1);
            }
        }

    }
}
