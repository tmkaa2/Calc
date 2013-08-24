/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaa.calculator.model.grammar.rules;

/**
 *
 * @author Mr.Green
 */
public class M extends Bukva{
        public M(String aComplect,String aZamena){
        complect=aComplect;
        zamena=aZamena;
        str="M";
    }
    public M(String aComplect,String aZamena, boolean aStur){
        complect=aComplect;
        zamena=aZamena;
        str="M";
        shiftFlag =aStur;
        ///shiftFlag=true;
    }
}
