package kaa.calculator.model.analysis.result;

import java.util.ArrayList;

public class AnalysisInfo {
    public AnalysisInfo(){
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
    public String[] firstArrayOfLexemesWithUserExpression;
}
