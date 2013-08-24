package calculator.test;

import kaa.calculator.controler.analiz.AnalysisInputStringToCorectExsspresionn;
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
        AnalysisInputStringToCorectExsspresionn analiz = new AnalysisInputStringToCorectExsspresionn("sqrt(5)+(1+min(5,6))");
        AnalysisInfo analysisInfo = analiz.runAnalizExpression();
        String[] lexem = analysisInfo.arrayOflexemesWithCustomExpression;
        if(analysisInfo.errorFlag == true)
            System.err.println("Error:"+ analysisInfo.errorFlag);
        else
            System.out.println("Error:"+ analysisInfo.errorFlag);

        analiz.showListUsedReules();
    }
}
