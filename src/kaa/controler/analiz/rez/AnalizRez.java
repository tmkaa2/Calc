package kaa.controler.analiz.rez;

import java.util.ArrayList;

public class AnalizRez {
    public AnalizRez(){
        numberError = new ArrayList<Integer>();
        indexErrorSimbolAtTheStr = new ArrayList<Integer>();
        numberError.add(-1);
        indexErrorSimbolAtTheStr.add(-1);
    }
    public String start;
    public ArrayList<Integer> indexErrorSimbolAtTheStr;
    public ArrayList<Integer> numberError;
    public ArrayList<Integer> rules;
    public String[] lexem;
    public boolean error;
}
