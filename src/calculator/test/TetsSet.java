package calculator.test;

import kaa.controler.analiz.AnalizInputStringToCorectExsspresion;
import kaa.controler.analiz.rez.AnalizRez;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 13.08.13
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 */
public class TetsSet {
    public static void main(String[] arg){
        AnalizInputStringToCorectExsspresion analiz = new AnalizInputStringToCorectExsspresion("psqrt(5)+345)))");
        AnalizRez analizRez = analiz.runAnalizExpression();
        String[] lexem = analizRez.lexem;
        System.out.println("Start: "+analizRez.start);
        System.out.println("Position cursor: ('"+lexem[analizRez.indexErrorSimbolAtTheStr.get(0)+1]+
                                               "') Index: "+analizRez.indexErrorSimbolAtTheStr+
                                               "Numbers error: "+analizRez.numberError);
        if(analizRez.error == true)
            System.err.println("Error:"+analizRez.error);
        else
            System.out.println("Error:"+analizRez.error);

    }
}
