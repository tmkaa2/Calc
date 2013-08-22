/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.test;

import kaa.calculator.model.charters.*;

import java.util.ArrayList;


/**
 *
 * @author Mr.Green
 */
public class Brackets {
    public static void main(String[] aa){
        ArrayList<Bukva[]> arrayOfGrammarRules = new ArrayList<Bukva[]>();
        I[] iArray =  {new I("0","AN"),new I("1","AN"),new I("2","AN"),new I("3","AN"),new I("4","AN"),new I("5","AN"),
                new I("6","AN"),new I("7","AN"),new I("8","AN"),new I("9","AN"),new I("0","AN"),new I("1","AN"),
                new I("min","AM"),new I("max","AM"),new I("^","AK"),new I("sqrt","AK"),new I("(","G")
        };

        A[] aArray = {new A("+", "AY",true),   new A("-", "AY", true),  new A("*", "AY", true),    new A("/", "AY", true),
                      new A("0", "AN",true),   new A("1", "AN", true),  new A("2", "AN", true),    new A("3", "AN", true),
                      new A("4", "AN",true),   new A("5", "AN", true),  new A("6", "AN", true),    new A("7", "AN", true),
                      new A("8", "AN",true),   new A("9", "AN", true),  new A("min", "AM", true),  new A("max", "AM", true),
                      new A("sqrt","AK",true), new A("^", "AK", true),  new A("+", "Y", true),     new A("-", "Y", true),
                      new A("/", "Y",true),    new A("*", "Y", true),   new A("0", "N", true),     new A("1", "N", true),
                      new A("2", "N",true),    new A("3", "N", true),   new A("4", "N", true),     new A("5", "N", true),
                      new A("6", "N",true),    new A("7", "N", true),   new A("8", "N", true),     new A("9", "N", true),
                      new A("min", "M",true),  new A("max", "M", true), new A("sqrt", "K", true),  new A("^", "K", true),
                     new A("(", "G",true) };

        N[] nArray = { new N("0",""),new N("1",""),new N("2",""),new N("3",""),new N("4",""),new N("5",""),new N("6",""),
                new N("7",""),new N("8",""),new N("9","")
        };
        M[] mArray = { new M("min","Zmin",true),new M("max","Zmax",true)};
        K[] kArray = { new K("sqrt","Jsqrt",true),new K("^","NJ^",true)};
        Y[] yArray = { new Y("+",""),new Y("-","") ,new Y("/",""),new Y("*","")};
      //  G[] gArray = { new G("(","A)A(",ture)};
        J[] jArray = { new J("(",")A(")};
        Z[] zArray = { new Z("(",")N,N(")};
        Bukva[] bukvaArray = {new Bukva(",",",",""),new Bukva("(","(",""),new Bukva(")",")","")};

        arrayOfGrammarRules.add(iArray);
        arrayOfGrammarRules.add(aArray);
        arrayOfGrammarRules.add(nArray);
        arrayOfGrammarRules.add(mArray);
        arrayOfGrammarRules.add(kArray);
        arrayOfGrammarRules.add(yArray);
        //arrayOfGrammarRules.add(gArray);
        arrayOfGrammarRules.add(jArray);
        arrayOfGrammarRules.add(zArray);
        arrayOfGrammarRules.add(bukvaArray);

        System.out.println("KAa");

        //for(int i=0; i<arrayOfGrammarRules.size();i++){
            //Bukva ss = (Bukva)arrayOfGrammarRules.get(i);
        //    System.out.println("sss");
        //}


        for (Bukva[] row : arrayOfGrammarRules) {
            System.out.println(row.length+" : "+row[0].str);
        } //
        
    }


}
