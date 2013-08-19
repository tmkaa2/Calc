
package kaa.controler.analiz.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitExpressionAtArrayList {

    private SplitExpressionAtArrayList(){};
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
                       resultString+=lexem.tempNum;
                       i=lexem.index;
                       continue;
                   }
                   if(((int)inputString.charAt(i)>96 && 
                           ((int)inputString.charAt(i))<123)){
                       
                       HelperSplitExpression lexem=parsSubString(inputString,i);
                       resultString+=lexem.tempNum;
                       i=lexem.index;
                       continue;
                   }
                    
                    resultString+=inputString.charAt(i)+"";
              }             
              return resultString.split("!");
    }
    
     private static HelperSplitExpression parsInt(String str, int index){
         HelperSplitExpression lexem = new HelperSplitExpression();
         lexem.index=index;
         lexem.tempNum="";
         
         for (int i = index; i < str.length(); i++) {
             if(((int)str.charAt(i)>47 && ((int)str.charAt(i))<58)){
                
                lexem.tempNum+=str.charAt(i);
                lexem.index=i;
             }
             else{
                lexem.tempNum+="!"; 
                 return lexem;
             }
         }
         return lexem;
     }
     
     private static HelperSplitExpression parsSubString(String str, int index){
       
         HelperSplitExpression lexem = new HelperSplitExpression();
         lexem.index=index;
         lexem.tempNum="";
         
         for (int i = index; i < str.length(); i++) {
             if(((int)str.charAt(i)>96 && ((int)str.charAt(i))<123)){
                
                lexem.tempNum+=str.charAt(i);
                lexem.index=i;
             }
             else{
                lexem.tempNum+="!"; 
                 return lexem;
             }
         }
         return lexem;       
     }
     // Add the "$" symbol  to the end  string and places where's this to need 
     public static String[] addToStrinArray$(String[] aArrayString){
        List<String> StringList = new ArrayList<String>(Arrays.asList(aArrayString));
        boolean flagBrackets=false;     
     
        for (int i = 0; i < StringList.size()-1; i++) {
            if(StringList.get(i).equals("min")== true ||
                      StringList.get(i).equals("max")== true ||
                           StringList.get(i).equals("^")== true){
                  StringList.add(StringList.size(),"$");
             } 
            if(StringList.get(i).equals("sqrt")== true && StringList.get(i+1).equals("(")==true){
                   flagBrackets=true;
                   continue;
             }
             if(flagBrackets == true && StringList.get(i+1).equals(")")){
                  StringList.add(i+1,"$");
                  StringList.add(StringList.size(),"$");
                  flagBrackets=false;
                  continue;
             }
        }
        StringList.add(StringList.size(),"$");

       return StringList.toArray(new String[StringList.size()]);
    }
}
