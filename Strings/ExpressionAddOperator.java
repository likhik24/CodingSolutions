package Strings;
/*
Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.

Note that operands in the returned expressions should not contain leading zeros.



Example 1:

Input: num = "123", target = 6
Output: ["1*2*3","1+2+3"]
Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2","2+3*2"]
Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
Example 3:

Input: num = "3456237490", target = 9191
Output: []
Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 */
import java.util.*;
//when we are multipling we need t use precedence of it over +,- so we subract the curr result and multiply it with currvalue carrying over the prev value
/*overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
        0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
        a little trick is that we should save the value that is to be multiplied in the next recursion. */
public class ExpressionAddOperator {

        ArrayList<String> result = new ArrayList<>();
        public ArrayList<String> addOperators(String num, int target) {
            StringBuilder curr = new StringBuilder();
            if(num.length() < 2)
            {
                if(Integer.parseInt(num) == target) {
                    result.add(num);
                }
                return result;

            }
            // backtrack(curr,1,num, target);
            dfs(curr,0, num,target,0L,0L);
            return result;
        }


        public void dfs(StringBuilder curr, int index, String input, int target, long prev, long multi) {
            if(index == input.length()) {
                if(prev == target)
                    result.add(curr.toString());
                return;
            }
            for(int i=index;i<input.length();i++) {
                if(input.charAt(index) == '0' && i != index) break;
                long curValue = Long.parseLong(input.substring(index,i+1));
                int len = curr.length();
                if(index == 0) {
                    dfs(curr.append(curValue),index+1,input,target, curValue, curValue);
                    curr.setLength(len);
                } else {
                    dfs(curr.append("+").append(curValue),index+1,input,target, curValue+prev, curValue);
                    curr.setLength(len);
                    dfs(curr.append("-").append(curValue),index+1,input,target, -curValue+prev, curValue);
                    curr.setLength(len);
                    dfs(curr.append("*").append(curValue),index+1,input,target, -multi+multi*curValue+prev, multi*curValue);
                    curr.setLength(len);
                }

            }
        }

        public void backtrack(StringBuilder curr, int index, String input, int target) {
            if(index >= curr.length()-1)
            {
                if(curr.lastIndexOf("#") == -1) {
                    if(evaluateExpr(curr.toString()) == target) {
                        result.add(curr.toString());
                    }
                }
                return;
            }
            String[] opers = new String[]{"+", "-","*"};
            for(int i=0;i<opers.length;i++) {
                curr.replace(index,index+1,opers[i]);
                System.out.println(String.valueOf(curr.toString()));
                backtrack(curr, index+2, input, target);
            }
            return;
        }
        public int evaluateExpr(String input) {
            int oper = -1;
            int res = 0;
            int prev=-1;
            for(int i=input.length()-1; i>=0;i--) {
                char ch = input.charAt(i);
                if(Character.isDigit(ch)) {

                    oper = Character.getNumericValue(ch);
                }
                else {

                    char operand = ch;
                    if(operand == '*') {
                        int oper1 = Character.getNumericValue(i+1);
                        int oper2 = Character.getNumericValue(input.charAt(--i));
                        res = oper1*oper2+(prev);
                        prev=oper1*oper2;
                    } else {
                        prev=oper; //10

                        int oper2 = Character.getNumericValue(input.charAt(--i));
                        if(operand == '+') {
                            res = oper+oper2;
                        }
                        else if(operand == '-') {
                            res = oper-oper2;
                            prev *= -1;
                        }
                    }
                    oper = res;

                }

            }
            return res;
        }

        public static void main(String[] args) {
            ExpressionAddOperator expression = new ExpressionAddOperator();
            ArrayList<String> res = expression.addOperators("123", 6);
            for(String re: res)
            {
                System.out.println(re);
            }
        }

}
