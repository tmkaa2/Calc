package kaa.calculator.model.analysis.result;

import java.util.ArrayList;

public class AnalysisInfo {
    public AnalysisInfo(){
        setArrayOfNumbersOfErrors(new ArrayList<Integer>());
        setArrayIndexesOfIncorrectLexeme(new ArrayList<Integer>());
        getArrayOfNumbersOfErrors().add(-1);
        getArrayIndexesOfIncorrectLexeme().add(-1);
    }
    private String start;
    private ArrayList<Integer> arrayIndexesOfIncorrectLexeme;
    private ArrayList<Integer> arrayOfNumbersOfErrors;
    private ArrayList<Integer> arrayOfNumbersOfGrammarRules;
    private String[] arrayOflexemesWithCustomExpression;
    private boolean errorFlag;
    private String[] firstArrayOfLexemesWithUserExpression;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public ArrayList<Integer> getArrayIndexesOfIncorrectLexeme() {
        return arrayIndexesOfIncorrectLexeme;
    }

    public void setArrayIndexesOfIncorrectLexeme(ArrayList<Integer> arrayIndexesOfIncorrectLexeme) {
        this.arrayIndexesOfIncorrectLexeme = arrayIndexesOfIncorrectLexeme;
    }

    public ArrayList<Integer> getArrayOfNumbersOfErrors() {
        return arrayOfNumbersOfErrors;
    }

    public void setArrayOfNumbersOfErrors(ArrayList<Integer> arrayOfNumbersOfErrors) {
        this.arrayOfNumbersOfErrors = arrayOfNumbersOfErrors;
    }

    public ArrayList<Integer> getArrayOfNumbersOfGrammarRules() {
        return arrayOfNumbersOfGrammarRules;
    }

    public void setArrayOfNumbersOfGrammarRules(ArrayList<Integer> arrayOfNumbersOfGrammarRules) {
        this.arrayOfNumbersOfGrammarRules = arrayOfNumbersOfGrammarRules;
    }

    public String[] getArrayOflexemesWithCustomExpression() {
        return arrayOflexemesWithCustomExpression;
    }

    public void setArrayOflexemesWithCustomExpression(String[] arrayOflexemesWithCustomExpression) {
        this.arrayOflexemesWithCustomExpression = arrayOflexemesWithCustomExpression;
    }

    public boolean isErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }

    public String[] getFirstArrayOfLexemesWithUserExpression() {
        return firstArrayOfLexemesWithUserExpression;
    }

    public void setFirstArrayOfLexemesWithUserExpression(String[] firstArrayOfLexemesWithUserExpression) {
        this.firstArrayOfLexemesWithUserExpression = firstArrayOfLexemesWithUserExpression;
    }
}
