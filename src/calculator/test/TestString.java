/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.test;

import kaa.calculator.controler.analiz.AnalizInputStringToCorectExsspresion;
import kaa.calculator.model.SetGrammar;
import kaa.calculator.view.analysis.result.AnalizInfo;
import kaa.calculator.controler.analiz.str.SplitExpressionAtArrayList;

import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author Mr.Green
 */
public class TestString {
   public static  void  main(String[] arg){
       AnalizInputStringToCorectExsspresion analiz = new AnalizInputStringToCorectExsspresion("^(h5h)2");
       AnalizInfo rez=analiz.runAnalizExpression();

       //temp Start
       SetGrammar sets=new SetGrammar(new ArrayList<String>(Arrays.asList("+","-","/","*","0","1","2","3","4","5","6",
                                                                          "7","8","9","(",")","min","max","^","sqrt")));
       String[] rezz= SplitExpressionAtArrayList.ParsStringPart("(36+(5-(89-9690)))");
       System.out.println("\n----");
       for(String sss:rezz){
           System.out.print(sss+" ");
       }
       System.out.println("\n----");
       //temp End

       //Test
       checkStr(rezz,sets);



     //  if(result.errorFlag==false && result.start.length()==0 ){
     //      analiz.showListUsedReules();
     //  }else{
     //      System.err.println("We have problems");
     //  }
       
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
