package Backtracking;

public class ParanthesisString {
    boolean foundValid = false;
    public boolean removeParanthesis(String input) {
        int openCount = 0;
        int closeCount = 0;
        int matchedCloseCount = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(input);
       updateCount(openCount, closeCount, matchedCloseCount, sb, 0);
       return foundValid;
    }
    public void updateCount(int openCount, int closeCount, int matchedCloseCount, StringBuilder input, int index) {
        if(index >= input.length()) { //())3()1)
           System.out.println(openCount + " " + matchedCloseCount + " " + closeCount);
            if(openCount == matchedCloseCount && closeCount == 0)
             foundValid = true;
         return;
        }
        char ch=input.charAt(index);
        if(foundValid) return;
        if(!Character.isDigit(ch)) {
            if(ch == '(')
            openCount++;
            else {
                System.out.println(openCount + " " + matchedCloseCount + " " + index);
                if(openCount-1 >= matchedCloseCount) {
                    matchedCloseCount++;
                }
                   else closeCount++;
                
            }
            updateCount(openCount, closeCount, matchedCloseCount, input, index+1);
            return;
        }
        else {
            if(ch == '0') {
             updateCount(openCount, closeCount, matchedCloseCount, input, index+1);
             return;
           }
              
            int digit = Character.getNumericValue(ch);
            if(foundValid) return;
            if(closeCount > 0)
              {
                int valuesToRemove = Math.min(digit, closeCount);
                closeCount -= valuesToRemove;
                digit -= valuesToRemove;
                input.replace(index, index+1, String.valueOf(digit));
              //  System.out.println(openCount + " " + matchedCloseCount);
                if(digit>0)
                 updateCount(openCount, closeCount, matchedCloseCount, input, index);
                 else 
                 updateCount(openCount, closeCount, matchedCloseCount, input, index+1);
                 return;
              }
            else {
                if(!foundValid) {
                    System.out.println(openCount + " " + closeCount + " " + matchedCloseCount + " " + index);
                if(openCount >= 1)
                 updateCount(openCount-1, closeCount, matchedCloseCount, input, index);
                // updateCount(openCount, closeCount, matchedCloseCount-1, input, index);
                
               if(matchedCloseCount >= 1 && !foundValid)
                    updateCount(openCount, closeCount, matchedCloseCount-1, input, index);
                return;
                }
            
            }
            
        }
    }

    public static void main(String[] args) {
        ParanthesisString str = new ParanthesisString();
        String input = "())3()1)";
      
        System.out.println(str.removeParanthesis(input));
    }
} 

