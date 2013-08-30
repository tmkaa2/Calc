package calculator.test;

import kaa.calculator.controller.analysis.manipulations.expression.HelperSplitExpression;

public class Split {
    public static void main(String[] args) {
    String[] ss = ParsStringPart("6+25-58s");
        for (String string : ss) {
            System.err.println(string);
        }
    }
    public static String[] ParsStringPart(String inputString){
        
        inputString = inputString.replace(" ", "");
        inputString = inputString.replace("\n", "");
        
        String resultString="";
              for (int i=0;i <inputString.length();i++) {
                   if(39 <((int)inputString.charAt(i)) && 
                           46 >((int)inputString.charAt(i)) || 
                                47==((int)inputString.charAt(i)) ||
                                    94==((int)inputString.charAt(i))||
                                         36==((int)inputString.charAt(i))){
                        
                       resultString+=inputString.charAt(i)+"!";
                        continue;
                    }
                   if(((int)inputString.charAt(i)>47 && 
                           ((int)inputString.charAt(i))<58)){
                       
                       HelperSplitExpression lexem=parsInt(inputString,i);
                       resultString+= lexem.getTempNum();
                       i= lexem.getIndex();
                       continue;
                   }
                   if(((int)inputString.charAt(i)>96 && 
                           ((int)inputString.charAt(i))<123)){
                       
                       HelperSplitExpression lexem=parsSubString(inputString,i);
                       resultString+= lexem.getTempNum();
                       i= lexem.getIndex();
                       continue;
                   }
                    
                    resultString+=inputString.charAt(i)+"";
              }             
              return resultString.split("!");
    }
    
     private static HelperSplitExpression parsInt(String str, int index){
         HelperSplitExpression lexem = new HelperSplitExpression();
         lexem.setIndex(index);
         lexem.setTempNum("");
         
         for (int i = index; i < str.length(); i++) {
             if(((int)str.charAt(i)>47 && ((int)str.charAt(i))<58)){
                
                lexem.setTempNum(lexem.getTempNum() + str.charAt(i));
                lexem.setIndex(i);
             }
             else{
                lexem.setTempNum(lexem.getTempNum() + "!");
                 return lexem;
             }
         }
         return lexem;
     }
     
     private static HelperSplitExpression parsSubString(String str, int index){
       
         HelperSplitExpression lexem = new HelperSplitExpression();
         lexem.setIndex(index);
         lexem.setTempNum("");
         
         for (int i = index; i < str.length(); i++) {
             if(((int)str.charAt(i)>96 && ((int)str.charAt(i))<123)){
                
                lexem.setTempNum(lexem.getTempNum() + str.charAt(i));
                lexem.setIndex(i);
             }
             else{
                lexem.setTempNum(lexem.getTempNum() + "!");
                 return lexem;
             }
         }
         return lexem;       
     }
}
