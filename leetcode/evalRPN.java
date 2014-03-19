package com.sherlockoy;

import java.util.ArrayList;

public class Solution {
    
    int sp = 0;
    ArrayList<Integer> values = new ArrayList<Integer>();
    
    public int evalRPN(String[] tokens) {
        
        int p = 0;
        int op2 = 0;
        for(p = 0; p<tokens.length;p++){
            String operator = tokens[p];
            char type = type(operator);
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
                case 'n':
                    pushVal(Integer.parseInt(operator));
                    break;
                default:
                    pushVal(Integer.parseInt(operator));
                    break;
            }
        }                   
        return popVal();
    }
    
    public char type(String operator){
        char type = operator.charAt(0);
        return (operator.length()>1)?'n':type;
    }
    
    public void pushVal(int n){
        values.add(sp, n);
        sp = sp+1;
    }
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
