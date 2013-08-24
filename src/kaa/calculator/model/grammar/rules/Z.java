package kaa.calculator.model.grammar.rules;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 21.08.13
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */
public class Z extends Bukva {
    public Z(String aComplect,String aZamena, boolean aStur){
        complect=aComplect;
        zamena=aZamena;
        str="Z";
        shiftFlag =aStur;
    }
}
