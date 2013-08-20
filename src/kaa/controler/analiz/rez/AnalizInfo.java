package kaa.controler.analiz.rez;

import java.util.ArrayList;

public class AnalizInfo {
    public AnalizInfo(){
        arrayOfNumbersOfErrors = new ArrayList<Integer>();
        arrayIndexesOfIncorrectLexeme = new ArrayList<Integer>();
        // temp lines
        arrayOfNumbersOfErrors.add(-1);
        arrayIndexesOfIncorrectLexeme.add(-1);
    }
    public String start;
    public ArrayList<Integer> arrayIndexesOfIncorrectLexeme;
    public ArrayList<Integer> arrayOfNumbersOfErrors;
    public ArrayList<Integer> arrayOfNumbersOfGrammarRules;
    public String[] arrayOflexemesWithCustomExpression;
    public boolean errorFlag;
}
