/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.test;

import kaa.controler.analiz.AnalizInputStringToCorectExsspresion;
import kaa.model.SetGrammar;
import kaa.controler.analiz.rez.AnalizRez;
import kaa.controler.analiz.str.SplitExpressionAtArrayList;

import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author Mr.Green
 */
public class TestString {
   public static  void  main(String[] arg){
       AnalizInputStringToCorectExsspresion analiz = new AnalizInputStringToCorectExsspresion("^(h5h)2");
       AnalizRez rez=analiz.runAnalizExpression();
       SetGrammar sets=new SetGrammar(new ArrayList<String>(Arrays.asList("+","-","/","*","0","1","2","3","4","5","6",
                                                                          "7","8","9","(",")","min","max","^","sqrt")));
       String[] rezz= SplitExpressionAtArrayList.ParsStringPart("^(h5h)2");
       System.out.println("\n----");

       for(String sss:rezz){
           System.out.print(sss+" ");
       }

       boolean counterErrors=false;
       for (int i=0;i<rezz.length;i++){
           counterErrors=false;
           for (int j=0;j<sets.getSet().size();j++){
               if(sets.getSet().get(j).equals(rezz[i])== true){
                   counterErrors=true;
                   break;
               }
           }
           if(counterErrors == false){
               System.out.println("\nWe have a problem! Expression is not correct!");
               System.out.println("\nWith this symbol: {"+rezz[i]+"}");
               i=rezz.length;
               break;
           }
       }

       System.out.println("\n----\n");


       if(rez.error==false && rez.start.length()==0 ){
           analiz.showListUsedReules();
       }else{
           System.err.println("We have problems");
       }
       
   }
      
}
