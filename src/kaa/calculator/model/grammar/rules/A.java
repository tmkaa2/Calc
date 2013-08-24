/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaa.calculator.model.grammar.rules;

/**
 *
 * @author Mr.Green
 */
public class A extends Bukva{
    public A(String aComplect,String aZamena){
        complect=aComplect;
        zamena=aZamena;
        str="A";
        ///shiftFlag=true;
    }
    
     public A(String aComplect,String aZamena, boolean aStur){
        complect=aComplect;
        zamena=aZamena;
        str="A";
        shiftFlag =aStur;
        ///shiftFlag=true;
    }
}
