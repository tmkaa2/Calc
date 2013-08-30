
package kaa.calculator.controller.analysis.manipulations.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitExpressionInArrayList {

    private SplitExpressionInArrayList(){};
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
         HelperSplitExpression lexeme = new HelperSplitExpression();
         lexeme.setIndex(index);
         lexeme.setTempNum("");
         
         for (int i = index; i < str.length(); i++) {
             if(((int)str.charAt(i)>47 && ((int)str.charAt(i))<58)){
                
                lexeme.setTempNum(lexeme.getTempNum() + str.charAt(i));
                lexeme.setIndex(i);
             }
             else{
                lexeme.setTempNum(lexeme.getTempNum() + "!");
                 return lexeme;
             }
         }
         return lexeme;
     }
     
     private static HelperSplitExpression parsSubString(String str, int index){
       
         HelperSplitExpression lexeme = new HelperSplitExpression();
         lexeme.setIndex(index);
         lexeme.setTempNum("");
         
         for (int i = index; i < str.length(); i++) {
             if(((int)str.charAt(i)>96 && ((int)str.charAt(i))<123)){
                
                lexeme.setTempNum(lexeme.getTempNum() + str.charAt(i));
                lexeme.setIndex(i);
             }
             else{
                lexeme.setTempNum(lexeme.getTempNum() + "!");
                 return lexeme;
             }
         }
         return lexeme;
     }
     // Add the "$" symbol  to the end  string and places where's this to need 
     public static String[] addToStrinArray$(String[] aArrayString)
     {
        List<String> StringList = new ArrayList<String>(Arrays.asList(aArrayString));
        for (int i = 0; i < StringList.size(); i++) {
             if(StringList.get(i).equals(")")){
                  StringList.add(i,"$");
                 if((StringList.size()-1 <= i+1))
                     break;
                 i+=1;
             }

            if(StringList.get(i).equals(",")){
                StringList.add(i,"$");
                if((StringList.size()-1 <= i+1))
                    break;
                i+=1;
            }
        }
        StringList.add(StringList.size(),"$");
        return StringList.toArray(new String[StringList.size()]);
     }
}
