/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaa.calculator.controller.analysis.manipulations.expression;

/**
 *
 * @author Mr.Green
 */
public class RefactoringStrings {
     
    public static String RefactoringStr(String aRewriteStr,String aAddStr){
       if(RefactoringAnalysisStrLength(aRewriteStr)>=1){
           aRewriteStr=aRewriteStr.substring(0, RefactoringAnalysisStrLength(aRewriteStr))+aAddStr;
           return aRewriteStr;
       }
       else{
           return new String(aAddStr);
       }
   }
   
   public static char getLastCharacter(String aStr){
       if(aStr.length()>0){
           return aStr.charAt(aStr.length()-1);

       }else{
            return ' ';
       }
   } 
    
   private static int RefactoringAnalysisStrLength(String aStr){
       if(aStr.length()>0){
           return aStr.length()-1;
           
       }else{
            return 0;
       }
   }
}
