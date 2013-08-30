package kaa.calculator.model.grammar.rules;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 21.08.13
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
public class G extends RootRule {
    public G(String aComplect,String aZamena, boolean aStur){
        complect=aComplect;
        zamena=aZamena;
        str="G";
        shiftFlag =aStur;
        ///shiftFlag=true;
    }
}
