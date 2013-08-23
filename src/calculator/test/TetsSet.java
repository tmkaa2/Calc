package calculator.test;

import kaa.calculator.controler.analiz.AnalizInputStringToCorectExsspresion;
import kaa.calculator.view.analysis.result.AnalizInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 13.08.13
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 */
public class TetsSet {
    public static void main(String[] arg){
        AnalizInputStringToCorectExsspresion analiz = new AnalizInputStringToCorectExsspresion("(min(25,36)+69/sqrt(25/96*74-12+3+max(45,36)$)$)-^(69+32+14-89*8/7$)58$");
        AnalizInfo analizInfo = analiz.runAnalizExpression();
        String[] lexem = analizInfo.arrayOflexemesWithCustomExpression;
        /*System.out.println("Start: "+ analizInfo.start);
        System.out.println("Position cursor: ('"+lexem[analizInfo.arrayIndexesOfIncorrectLexeme.get(0)+1]+
                                               "') Index: "+ analizInfo.arrayIndexesOfIncorrectLexeme +
                                               "Numbers errorFlag: "+ analizInfo.arrayOfNumbersOfErrors);*/
        if(analizInfo.errorFlag == true)
            System.err.println("Error:"+ analizInfo.errorFlag);
        else
            System.out.println("Error:"+ analizInfo.errorFlag);

        analiz.showListUsedReules2();
    }
}
