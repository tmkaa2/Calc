/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mr.Green
 */
public class Brackets {
    public static void main(String[] aa){
     String[] aArrayString={"sqrt","(","96",")","+","^","(","5",")","6","+","max","(","9",",","36",")"};
    
     aArrayString=addToStrinArray$2(aArrayString);
     
     for (int i = 0; i < aArrayString.length; i++) {
               System.out.print(aArrayString[i]);
        }
     
     /* List<String> ss = new ArrayList<String>(Arrays.asList(aArrayString));
     boolean flagBrackets=false;
     
     
     for (int i = 0; i < a1.length-1; i++) {
         if(ss.get(i).equals("min")== true ||
                   ss.get(i).equals("max")== true ||
                        ss.get(i).equals("^")== true){
               ss.add(ss.size(),"$");
          } 
         if(ss.get(i).equals("sqrt")== true && ss.get(i+1).equals("(")==true){
                flagBrackets=true;
                continue;
          }
          if(flagBrackets == true && ss.get(i+1).equals(")")){
               ss.add(i+1,"$");
               flagBrackets=false;
               continue;
          }
     }
     ss.add(ss.size(),"$");
        for (int i = 0; i < ss.size(); i++) {
               System.out.print(ss.get(i));
        }
            
         System.out.println("");
             */
    }
    
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
                  flagBrackets=false;
                  continue;
             }
        }
        StringList.add(StringList.size(),"$");

       return StringList.toArray(new String[StringList.size()]);
    }
    
     public static String[] addToStrinArray$2(String[] aArrayString){
        List<String> StringList = new ArrayList<String>(Arrays.asList(aArrayString));
        boolean flagBrackets=false;     
        int counterBrackets=0;
        for (int i = 0; i < StringList.size()-1; i++) {
            
            if(StringList.get(i).equals("sqrt")== true && StringList.get(i+1).equals("(")==true ||
                    StringList.get(i).equals("min")== true && StringList.get(i+1).equals("(")==true||
                        StringList.get(i).equals("max")== true && StringList.get(i+1).equals("(")==true){
                   flagBrackets=true;
                   counterBrackets++;
                   continue;
             }
             if(flagBrackets == true && StringList.get(i+1).equals(")")){
                  StringList.add(i+1,"$");
                  counterBrackets--;
                  if(counterBrackets ==0)
                    flagBrackets=false;
                  continue;
             }
        }
        StringList.add(StringList.size(),"$");

       return StringList.toArray(new String[StringList.size()]);
    }
    
}
