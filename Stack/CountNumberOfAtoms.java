package Stack;

import java.util.*;

//        Given a string formula representing a chemical formula, return the count of each atom.
//
//        The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
//
//        One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow.
//
//        For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
//        Two formulas are concatenated together to produce another formula.
//
//        For example, "H2O2He3Mg4" is also a formula.
//        A formula placed in parentheses, and a count (optionally added) is also a formula.
//
//        For example, "(H2O2)" and "(H2O2)3" are formulas.
//        Return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
//
//        The test cases are generated so that all the values in the output fit in a 32-bit integer.
//
//
//
//        Example 1:
//
//        Input: formula = "H2O"
//        Output: "H2O"
//        Explanation: The count of elements are {'H': 2, 'O': 1}.
//        Example 2:
//
//        Input: formula = "Mg(OH)2"
//        Output: "H2MgO2"
//        Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
//        Example 3:
//
//        Input: formula = "K4(ON(SO3)2)2"
//        Output: "K4N2O14S4"
//        Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
//
//
//        Constraints:
//
//        1 <= formula.length <= 1000
//        formula consists of English letters, digits, '(', and ')'.
//        formula is always valid.

//count number of atoms
//        by level for every open brackets we treat it as new level by adding new map to stack
//        do processing level by level to combine counts of all chars
//        we need to store each level and combine them whenever we see ) by computing multiplcation factor, atom can be a strign with more than one letter so when char is a letter we will find laregest atom name without any numbers in between

//Time complexity: O(N^2) // Space Complexity: O(N) // each map can be of size N max if string has N different characters
public class CountNumberOfAtoms {

        public String countOfAtoms(String formula) {
            int N = formula.length();
            Stack<Map<String, Integer>> stack = new Stack();
            stack.push(new TreeMap());

            for (int i = 0; i < N;) {
                if (formula.charAt(i) == '(') {
                    stack.push(new TreeMap());
                    i++;
                } else if (formula.charAt(i) == ')') {
                    Map<String, Integer> top = stack.pop();
                    int iStart = ++i, multiplicity = 1;
                    while (i < N && Character.isDigit(formula.charAt(i))) i++;
                    if (i > iStart) multiplicity = Integer.parseInt(formula.substring(iStart, i));
                    for (String c: top.keySet()) {
                        int v = top.get(c);
                        stack.peek().put(c, stack.peek().getOrDefault(c, 0) + v * multiplicity);
                    }
                } else {
                    int iStart = i++;
                    while (i < N && Character.isLowerCase(formula.charAt(i))) i++;
                    String name = formula.substring(iStart, i); //find atom name without any numbers in between
                    iStart = i;
                    while (i < N && Character.isDigit(formula.charAt(i))) i++;
                    int multiplicity = i > iStart ? Integer.parseInt(formula.substring(iStart, i)) : 1;
                    stack.peek().put(name, stack.peek().getOrDefault(name, 0) + multiplicity);
                }
            }

            StringBuilder ans = new StringBuilder();
            for (String name: stack.peek().keySet()) {
                ans.append(name);
                int multiplicity = stack.peek().get(name);
                if (multiplicity > 1) ans.append("" + multiplicity);
            }
            return new String(ans);
        }

}
