/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaa.calculator.model.grammar.rules;

/**
 *
 * @author Mr.Green
 */
public class RootRule {
    public RootRule(String aStr, String aComplect, String aZamena){
        str=aStr;
        complect=aComplect;
        zamena=aZamena;
        shiftFlag =false;
    }
    public RootRule(){
        str="KAA";
        complect="";
        zamena="";
        shiftFlag =false;
    }
    public String str;
    public String complect;
    public String zamena;
    public boolean shiftFlag;
}
