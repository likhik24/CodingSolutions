package Strings;
import java.util.*;
public class RemoveInvalidParanthesis {
 //first chars or sepcial characters
    class Pair {
        char value;
        int index;
        Pair(char value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public boolean isValid(String s) {
        int count = 0;
        int i=0;
        while(i<s.length()) {
            if(s.charAt(i) == '(')
                count++;
            else if(s.charAt(i) == ')')
                count--;
            if(count < 0)
                return false;
        }
        return count == 0;
    }
    public List<String> removeInvalidParanthesis(String s) {
        Deque<Pair> indexesOfFailure = new LinkedList<Pair>();
        List<String> result = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') count++;
            else if (s.charAt(i) == ')') count--;
            if (count > 1)
                indexesOfFailure.addLast(new Pair('(', i));
            if (count < 0)
                indexesOfFailure.addFirst(new Pair(')', i));
        }
        if (indexesOfFailure.isEmpty()) {
            result.add(s);
            return result;
        }
        int lastValidIndex = 0;
        while(!indexesOfFailure.isEmpty()) {
            Pair curr = indexesOfFailure.pollFirst();
            for(int i=lastValidIndex;i<curr.index+1 && i<s.length();i++) {
                if(s.charAt(i) != ')')
                    continue;
                if(isValid(s.substring(lastValidIndex,i) + s.substring(i+1))) {
                    result.add(s.substring(lastValidIndex, i) + s.substring(i + 1));

                }
            }
        }
        return result;
    }

        // method checks if character is parenthesis(open
// or closed)
    /*
        static boolean isParenthesis(char c)
        {
            return ((c == '(') || (c == ')'));
        }

        // method returns true if string contains valid
// parenthesis
        static boolean isValidString(String str)
        {
            int cnt = 0;
            for (int i = 0; i < str.length(); i++)
            {
                if (str.charAt(i) == '(')
                    cnt++;
                else if (str.charAt(i) == ')')
                    cnt--;
                if (cnt < 0)
                    return false;
            }
            return (cnt == 0);
        }

        // method to remove invalid parenthesis
        static void removeInvalidParenthesis(String str)
        {
            if (str.isEmpty())
                return;

            // visit set to ignore already visited string
            HashSet<String> visit = new HashSet<String>();

            // queue to maintain BFS
            Queue<String> q = new LinkedList<>();
            String temp;
            boolean level = false;

            // pushing given string as
            // starting node into queue
            q.add(str);
            visit.add(str);
            while (!q.isEmpty())
            {
                str = q.peek(); q.remove();
                if (isValidString(str))
                {
                    System.out.println(str);

                    // If answer is found, make level true
                    // so that valid string of only that level
                    // are processed.
                    level = true;
                }
                if (level)
                    continue;
                for (int i = 0; i < str.length(); i++)
                {
                    if (!isParenthesis(str.charAt(i)))
                        continue;

                    // Removing parenthesis from str and
                    // pushing into queue,if not visited already
                    temp = str.substring(0, i) + str.substring(i + 1);
                    if (!visit.contains(temp))
                    {
                        q.add(temp);
                        visit.add(temp);
                    }
                }
            }
        } */

        // Driver Code
        public static void main(String[] args)
        {
            String expression = "()())()";
            RemoveInvalidParanthesis str = new RemoveInvalidParanthesis();
            str.removeInvalidParanthesis(expression);

            expression = "()v)";
            str.removeInvalidParanthesis(expression);

        }

}
