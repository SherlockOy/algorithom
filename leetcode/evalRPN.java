/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

*/


package com.sherlockoy;

import java.util.ArrayList;

public class Solution {
    
    int sp = 0;
    ArrayList<Integer> values = new ArrayList<Integer>();
    
    public int evalRPN(String[] tokens) {
        
        int p = 0;
        //op2 for the situations of '-' and '/'
        int op2 = 0;
        for(p = 0; p<tokens.length;p++){
            String operator = tokens[p];
            char type = judge(operator);
            switch(type){
                case '+':
                    pushVal(popVal()+popVal());
                    break;
                case '-':
                    op2 = popVal();
                    pushVal(popVal()-op2);
                    break;
                case '*':
                    pushVal(popVal()*popVal());
                    break;
                case '/':
                    op2 = popVal();
                    pushVal(popVal()/op2);
                    break;
                case 'm':
                    pushVal(Integer.parseInt(operator));
                    break;
                //default is the positive number
                default:
                    pushVal(Integer.parseInt(operator));
                    break;
            }
        }                   
        return popVal();
    }
    //to judge the type of the operator
    public char judge(String operator){
        char type = operator.charAt(0);
        //'m' represents minus integers
        return (operator.length()>1)?'m':type;
    }
    //push the Values into the stack
    public void pushVal(int n){
        values.add(sp, n);
        sp = sp+1;
    }
    //pop out the top value of the stack
    public int popVal(){
        if(sp>0){
            sp = sp-1;
            int res = values.get(sp);
            return res;
        }
        else
            return 0;
    }
}
