package kaa.calculator.model.grammar.rules;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 21.08.13
 * Time: 20:49
 * To change this template use File | Settings | File Templates.
 */
public class J extends RootRule {
    public J(String aComplect,String aZamena, boolean aStur){
        complect=aComplect;
        zamena=aZamena;
        str="J";
        shiftFlag =aStur;
        ///shiftFlag=true;
    }
}
