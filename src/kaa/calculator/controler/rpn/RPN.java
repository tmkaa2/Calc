package kaa.calculator.controler.rpn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        arrayOfLexemesWithUserExpression=helpForOperation();

        for(int i=0;i<arrayOfLexemesWithUserExpression.length;i++){

            if(checkNum(arrayOfLexemesWithUserExpression[i].charAt(0)))
                rpn.add(arrayOfLexemesWithUserExpression[i]);

            if(checkOperationsB(arrayOfLexemesWithUserExpression[i].charAt(0))){
                if(stuck.size()!=0){
                    while(checkPriorityOperations(arrayOfLexemesWithUserExpression[i].charAt(0))< checkPriorityOperations(stuck.getTopElement().charAt(0)) && !stuck.getTopElement().equals("(")){
                        rpn.add(stuck.pop());
                        if(stuck.size()==0)
                            break;
                    }
                    stuck.push(arrayOfLexemesWithUserExpression[i]);
                }
                else
                    stuck.push(arrayOfLexemesWithUserExpression[i]);
            }

            if(arrayOfLexemesWithUserExpression[i].equals("(")){
                stuck.push(arrayOfLexemesWithUserExpression[i]);

            }

            if(arrayOfLexemesWithUserExpression[i].equals(",")){
                while(!stuck.getTopElement().equals("(")){
                    rpn.add(stuck.pop());
                }
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

    private String[] helpForOperation(){
        boolean flag= false;
        int blance=0;
        List<String> stringList = new ArrayList<String>(Arrays.asList(arrayOfLexemesWithUserExpression));

            for(int i =0;i<stringList.size()-1;i++){
                 if(stringList.get(i).equals("^")){
                     flag = true;
                     stringList.remove(i);
                 }
                if(stringList.get(i).equals("(") && flag ){
                     blance++;
                }
                if(stringList.get(i).equals(")") && flag ){
                    blance--;
                }
                if(blance == 0 && checkNum(stringList.get(i+1).charAt(0)) && flag){
                    i++;
                    stringList.add(i,"^");
                    flag=false;
                }
            }
        return stringList.toArray(new String[stringList.size()]);
    }

   /*
    public static void main(String[] args) {
        RPN tets = new RPN();
        // ok1 ex ={"(","(","22","-","24",")","/","2","*","21","/","(","5","+","2",")",")"};
        // ok1 ex ={"8","-","1259","*","(","2","+","9","/","89",")"};
        // ok1 ex ={"25","-","36","+","(","(","2","+","9",")","/","89","+","(","23","*","94",")",")"};
        // ok1 ex ={"60","-","30","+","sqrt","(","4","*","(","12","-","4",")","/","(","20","-","18",")",")","/","4"};
        // ok1 ex ={"^","(","22","-","24","*","60","-","30",")","30","+","^","(","22","-","24",")","25","+","sqrt","(","4","*","(","12","-","4",")","/","(","20","-","18",")",")","/","4"};
        // ok1 ex ={"min","(","208","*","115","-","(","28","*","15","-","9",")",",","24","-","(","5","*","5","-","92",")",")","*","165","-","(","628","*","615","-","91",")"};
        String[]  ex ={"min","(","208","*","115","-","(","28","*","15","-","9",")",",","24","-","(","5","*","5","-","92",")",")","*","165","-","(","628","*","615","-","91",")"};
        for (String ss: ex){
            System.out.print(ss+" ");
        }
        System.out.println("");

        ArrayList<String> ex1=tets.convertToRPN2(ex);

        System.out.println("");
        for (String ss: ex1){
            System.out.print("["+ss+"] ");
        }
    }
     */
}
