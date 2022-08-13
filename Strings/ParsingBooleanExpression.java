package Strings;

import java.util.*;
//Return the result of evaluating a given boolean expression, represented as a string.
//
//        An expression can either be:
//
//        "t", evaluating to True;
//        "f", evaluating to False;
//        "!(expr)", evaluating to the logical NOT of the inner expression expr;
//        "&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
//        "|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...
//
//
//        Example 1:
//
//        Input: expression = "!(f)"
//        Output: true
//        Example 2:
//
//        Input: expression = "|(f,t)"
//        Output: true

public class ParsingBooleanExpression {
    public boolean parseBoolExpr(String expression) {
        String str = expression;
        Stack<Character> logo = new Stack<>(); //to store AND,OR,NO
        Stack<Character> use = new Stack<>();  //to store everything other than AND,OR,NOT
      for(int i = 0; i<str.length(); i++){
            if(str.charAt(i)=='!' || str.charAt(i)=='|' || str.charAt(i)=='&'){
                logo.push(str.charAt(i)); // pushing AND/OR/NOT to logo stack
            }else if(str.charAt(i)==')'){  //if ")" fount we will do operation
                char l = logo.pop(); //storing last pushed boolean operator
                if(l=='!'){  //if its not we have to do very few operations
                    char c = use.pop(); //storing the expr
                    use.pop();
                    if(c == 't'){  //returning the opposite expr
                        use.push('f');
                    }else{
                        use.push('t');
                    }
                }else{   //if the operator is either AND or OR
                    char flag1 = 'f';  //default
                    char flag2 = 't';
                    while(use.peek()!='('){
                        if(l=='|' && use.peek()=='t'){ //we check if operator is OR and any one of expr is true
                            flag1 = 't';
                        }
                        if(l=='&' && use.peek()=='f'){ // //we check if operator is AND and any one of expr is false
                            flag2 = 'f';
                        }
                        use.pop();
                    }
                    use.pop();  //poping '('
                    if(l=='&'){  //pushing into use stack
                        use.push(flag2);
                    }else{
                        use.push(flag1);
                    }
                }
            }else{
                use.push(str.charAt(i));  //pushing every element expect AND/OR/NOT and ")"
            }
        }
       if(use.pop()=='t'){  //checking and returning
            return true;
        }
         return false;
    }
}

