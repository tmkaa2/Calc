package kaa.calculator.controler.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kaa.calculator.controler.analiz.AnalysisInputStringToCorrectExpressionn;
import kaa.calculator.controler.form.ElementControlGeter;
import kaa.calculator.model.analysis.result.AnalysisInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 19.08.13
 * Time: 16:15
 * To change this template use File | Settings | File Templates.
 */
public class ButtonListener extends ElementControlGeter implements ActionListener  {
    static int ck=0;
    @Override
    public void actionPerformed(ActionEvent e) {
        String inputExpression = frame.getJtfExpression().getText();
        if(inputExpression.length() != 0){
            frame.getJtaError().setText("");
            AnalysisInputStringToCorrectExpressionn analiz = new AnalysisInputStringToCorrectExpressionn(inputExpression);
            AnalysisInfo analysisInfo = analiz.runAnalysisExpression();





            System.out.println((++ck)+".enter: "+inputExpression);

        }else{
             frame.getJtaError().setText("Expression is empty!\nPlease enter expression.");

        }

    }
}
