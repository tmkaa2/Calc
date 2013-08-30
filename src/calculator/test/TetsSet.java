package calculator.test;

import kaa.calculator.controller.analysis.AnalysisInputStringToCorrectExpression;
import kaa.calculator.model.analysis.result.AnalysisInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 13.08.13
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 */
public class TetsSet {
    public static void main(String[] arg){
        AnalysisInputStringToCorrectExpression analiz = new AnalysisInputStringToCorrectExpression("(sqrt(5))+(min(5,6))");
        AnalysisInfo analysisInfo = analiz.runAnalysisExpression();
        String[] lexem = analysisInfo.getArrayOflexemesWithCustomExpression();
        if(analysisInfo.isErrorFlag() == true)
            System.err.println("Error:"+ analysisInfo.isErrorFlag());
        else
            System.out.println("Error:"+ analysisInfo.isErrorFlag());

        analiz.showListUsedRules();
    }
}
