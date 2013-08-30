package kaa.calculator.controller.rpn.computing;

import kaa.calculator.controller.rpn.stack.StackList;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 26.08.13
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public class RPNComputer {
    StackList<String> stack = new StackList<String>();

    public  double computing (ArrayList<String> aRpn){
        for(int i=0; i < aRpn.size();i++){
            if(checkNum(aRpn.get(i).charAt(0))){
                stack.push(aRpn.get(i));
            }

            if(checkOperationsB(aRpn.get(i).charAt(0))){
                if(aRpn.get(i).equals("min")){
                    stack.push(String.valueOf(computeExpression('z')));
                    continue;
                }
                if(aRpn.get(i).equals("max")){
                    stack.push(String.valueOf(computeExpression('w')));
                    continue;
                }
                stack.push(String.valueOf(computeExpression(aRpn.get(i).charAt(0))));

            }
        }
        return Double.parseDouble(stack.pop());
    }
    private  boolean checkNum(char aStr){
        switch (aStr){
            case '0':
                return true ;
            case '1':
                return true ;
            case '2':
                return true ;
            case '3':
                return true ;
            case '4':
                return true ;
            case '5':
                return true ;
            case '6':
                return true ;
            case '7':
                return true ;
            case '8':
                return true ;
            case '9':
                return true ;
        }
        return false;
    }

    private  boolean checkOperationsB(char aStr){
        switch (aStr){
            case 'm':
                return true ;
            case '^':
                return true ;
            case 's':
                return true ;
            case '+':
                return true ;
            case '-':
                return true ;
            case '/':
                return true ;
            case '*':
                return true ;
        }
        return false;
    }

    private  double computeExpression(char aStr){
        double x=0;
        double y=0;
        double ones=0;

        switch (aStr){
            case 'w':
                 y = Double.parseDouble(stack.pop());
                 x = Double.parseDouble(stack.pop());
                if (x>y)
                    return x;
                else
                    return y;
            case 'z':
                y = Double.parseDouble(stack.pop());
                x = Double.parseDouble(stack.pop());
                if (x<y)
                    return x;
                else
                    return y;
            case '^':
                y = Double.parseDouble(stack.pop());
                x = Double.parseDouble(stack.pop());
                return Math.pow(x,y);
            case 's':
                ones = Double.parseDouble(stack.pop());
                return  Math.sqrt(ones) ;
            case '+':
                y = Double.parseDouble(stack.pop());
                x = Double.parseDouble(stack.pop());
                return x+y ;
            case '-':
                y = Double.parseDouble(stack.pop());
                x = Double.parseDouble(stack.pop());
                return x-y ;
            case '/':
                y = Double.parseDouble(stack.pop());
                x = Double.parseDouble(stack.pop());
                return x/y ;
            case '*':
                y = Double.parseDouble(stack.pop());
                x = Double.parseDouble(stack.pop());
                return x*y ;
        }
        return -777;
    }
/*
    public static void main(String[] args) {
        RPN tets = new RPN();
        RPNComputer com = new RPNComputer();
        String[]  ex ={"max","(","25",",","15",")"};
        for (String ss: ex){
            System.out.print(ss+" ");
        }
        System.out.println("");

        ArrayList<String> ex1=tets.convertToRPN2(ex);
        System.out.println(com.computing(ex1));
    }
    */
}
