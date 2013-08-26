package kaa.calculator.controler.rpn;
import java.util.ArrayList;
import kaa.calculator.controler.rpn.stack.StackList;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 25.08.13
 * Time: 6:36
 * To change this template use File | Settings | File Templates.
 */

public class RPN {

    private StackList<String> stuck;
    private ArrayList<String> rpn;
    private String[] arrayOfLexemesWithUserExpression;

    public RPN()
    {
       stuck = new StackList<String>();
       rpn = new ArrayList<String>();
    }

    public ArrayList<String> convertToRPN2(String[] aArrayOfLexemesWithUserExpression)
    {
        arrayOfLexemesWithUserExpression=aArrayOfLexemesWithUserExpression;

        for(int i=0;i<arrayOfLexemesWithUserExpression.length;i++){

            if(checkNum(arrayOfLexemesWithUserExpression[i].charAt(0)))
                rpn.add(arrayOfLexemesWithUserExpression[i]);

            if(checkOperationsB(arrayOfLexemesWithUserExpression[i].charAt(0))){
                if(stuck.size()!=0){
                    while(checkPriorityOperations(arrayOfLexemesWithUserExpression[i].charAt(0))< checkPriorityOperations(stuck.getTopElement().charAt(0)) ){
                        rpn.add(stuck.pop());
                    }
                    stuck.push(arrayOfLexemesWithUserExpression[i]);
                }
                else
                    stuck.push(arrayOfLexemesWithUserExpression[i]);
            }

            if(arrayOfLexemesWithUserExpression[i].equals("(")){
                stuck.push(arrayOfLexemesWithUserExpression[i]);

            }

            if(arrayOfLexemesWithUserExpression[i].equals(")")){
                while(!stuck.getTopElement().equals("(")){
                    rpn.add(stuck.pop());
                }
                stuck.pop();
            }
        }

        while(stuck.getTopElement()!= null){
            rpn.add(stuck.pop());
        }

        return rpn;
    }

    private boolean checkNum(char aStr){
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

    private int checkPriorityOperations(char aStr){
        switch (aStr){
            case 'm':
                return 3 ;
            case '^':
                return 3 ;
            case 's':
                return 3 ;
            case '+':
                return 0 ;
            case '-':
                return 0 ;
            case '/':
                return 1 ;
            case '*':
                return 1 ;
        }
        return -1;
    }

    private boolean checkOperationsB(char aStr){
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

    public static void main(String[] args) {
        RPN tets = new RPN();
        // ok1 ex ={"(","(","22","-","24",")","/","2","*","21","/","(","5","+","2",")",")"};
        // ok ex ={"8","-","1259","*","(","2","+","9","/","89",")"};
        // ok ex ={"25","-","36","+","(","(","2","+","9",")","/","89","+","(","23","*","94",")",")"};
        // ok ex ={"60","-","30","+","sqrt","(","4","*","(","12","-","4",")","/","(","20","-","18",")",")","/","4"};
        String[] ex ={"(","(","22","-","24",")","/","2","*","21","/","(","5","+","2",")",")"};
        for (String ss: ex){
            System.out.print(ss+" ");
        }
        System.out.println("");

        ArrayList<String> ex1=tets.convertToRPN2(ex);

        for (String ss: ex1){
            System.out.print("["+ss+"] ");
        }
    }

}
