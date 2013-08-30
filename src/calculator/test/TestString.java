/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.test;

import kaa.calculator.controller.analysis.AnalysisInputStringToCorrectExpression;
import kaa.calculator.controller.analysis.manipulations.expression.SplitExpressionInArrayList;
import kaa.calculator.model.SetGrammar;
import kaa.calculator.model.analysis.result.AnalysisInfo;

import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author Mr.Green
 */
public class TestString {
   public static  void  main(String[] arg){
       AnalysisInputStringToCorrectExpression analiz = new AnalysisInputStringToCorrectExpression("^(h5h)2");
       AnalysisInfo rez=analiz.runAnalysisExpression();

       //temp Start
       SetGrammar sets=new SetGrammar(new ArrayList<String>(Arrays.asList("+","-","/","*","0","1","2","3","4","5","6",
                                                                          "7","8","9","(",")","min","max","^","sqrt")));
       String[] rezz= SplitExpressionInArrayList.ParsStringPart("(36+(5-(89-9690)))");
       System.out.println("\n----");
       for(String sss:rezz){
           System.out.print(sss+" ");
       }
       System.out.println("\n----");

       checkStr(rezz,sets);
   }

    public static void checkStr(String[] inputExpression,SetGrammar grammarElements){
        boolean counterErrors=false;
        String[] modifiedInpExpr=checkStrHelp(inputExpression) ;
        int counterBreaks=0;

        //Checking brackets at the expression
        for (int i=0;i<modifiedInpExpr.length;i++){
            // Amount brackets count up in the expression
            if(modifiedInpExpr[i].equals("("))
                counterBreaks++;
            if(modifiedInpExpr[i].equals(")"))
                counterBreaks--;
        }

        for (int i=0;i<modifiedInpExpr.length-1;i++){
            counterErrors=false;

            //  Checking the expression elements at the include elements of grammar.
            for (int j=0;j<grammarElements.getSet().size();j++){
                // Do are brackets enter correct?
                if(modifiedInpExpr[i].equals(")") && modifiedInpExpr[i+1].equals("(") ||modifiedInpExpr[i].equals("(") && modifiedInpExpr[i+1].equals(")") ){
                    break;
                }
                if(grammarElements.getSet().get(j).equals(modifiedInpExpr[i])== true){
                    counterErrors=true;
                }
            }

            if(counterErrors == false){
                System.err.println("\nWe have a problem! Expression is not correct!");
                System.err.println("\nWith this symbol: {"+modifiedInpExpr[i]+"}");
                i=modifiedInpExpr.length;
                break;
            }
        }

        counterErrors = false;
        //  Checking the expression elements at the include elements of grammar for last element expression.
        for (int j=0;j<grammarElements.getSet().size();j++){
            if(grammarElements.getSet().get(j).equals(inputExpression[inputExpression.length-1])== true){
                counterErrors=true;
            }
        }


        if(counterErrors == false){
            System.err.println("\nWe have a problem! Expression is not correct!\nLast element expression!");
        }

        if(counterBreaks != 0){
            System.err.println("\nBrackets are placed incorrectly!\n");
        }

    }

    public static String[] checkStrHelp(String[] inputExpression){
        for (int j=0;j<inputExpression.length;j++){
            if(inputExpression[j].charAt(0)>47 || inputExpression[j].charAt(0)<58){
                inputExpression[j]=inputExpression[j].charAt(0)+"";
            }
        }
        return inputExpression;
    }
}
