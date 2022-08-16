package Stack;
import java.util.*;
/*
Given a string s which represents an expression, evaluate this expression and return its value.

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "3+2*2"
Output: 7
Example 2:

Input: s = " 3/2 "
Output: 1
Example 3:

Input: s = " 3+5 / 2 "
Output: 5
 */
public class BasicCalculatorII {

        public int calculate(String s1) {
            Stack<Integer> stack = new Stack<Integer>();
            int sign = 1; // we will use this to store sign of earlier operation to multiply (1 or -1) to any operand before pushing it to stack
            int operand = 0;
            char operation=(char) 0;
            int result = 0;
            String s = s1.replaceAll("\s+", ""); //replace multiple white space characters with empty
            //3-15/2
            for(int i=0;i<s.length();i++) {
                char ch = s.charAt(i);
                if( Character.isDigit(ch)) {
                    while(i < s.length() && Character.isDigit(s.charAt(i))) {
                        ch = s.charAt(i);
                        operand = 10*operand + (int)(ch-'0');
                        i++;
                    }
                    i--;
                    if(operation != (char) 0) {
                        if(operation == '/') {

                            operand = stack.pop()/operand;


                        } else {
                            operand *= stack.pop();
                        }

                    }
                    operation = (char) 0;
                }

                else  {
                    if(sign == 1)
                        stack.push(operand);
                    else stack.push(-operand);
                    sign = 1;
                    if(ch == '-')
                        sign = -1;
                    if(ch == '*' || ch == '/')
                        operation=ch;
                    operand=0;

                }

            }
            result += operand*sign;
            while(!stack.isEmpty()) {
                result += stack.pop();

            }


            return result;
        }

}
