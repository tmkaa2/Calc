package kaa.calculator.controller.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import kaa.calculator.controller.analysis.AnalysisInputStringToCorrectExpression;
import kaa.calculator.controller.frame.ElementControlGeter;
import kaa.calculator.controller.rpn.RPN;
import kaa.calculator.controller.rpn.computing.RPNComputer;
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
        frame.getJtfResult().setText("");
        frame.getJtaError().setText("");


        if(inputExpression.length() != 0){
            frame.getJtaError().setText("");
            AnalysisInputStringToCorrectExpression analysis = new AnalysisInputStringToCorrectExpression(inputExpression);
            AnalysisInfo analysisInfo = analysis.runAnalysisExpression();
            if(analysisInfo.isErrorFlag()){
                    frame.getResult().setFocusable(false);
                    frame.getJtaError().setFocusable(false);
                    frame.getJtfResult().setFocusable(false);
                    frame.getJtfExpression().setCaretPosition(calculationOfTheIndexErrors(analysisInfo,
                                                              analysisInfo.getArrayIndexesOfIncorrectLexeme().get(1)));
                    frame.getJtfExpression().setFocusable(true);
                    frame.getJtaError().setText(returnErrorMessage(analysisInfo));
            }
            else{
                RPN tets = new RPN();
                RPNComputer com = new RPNComputer();
                ArrayList<String> rptExpression=tets.convertToRPN2(analysisInfo.getFirstArrayOfLexemesWithUserExpression());
                System.out.println(com.computing(rptExpression));
                frame.getJtfResult().setText("="+String.valueOf(com.computing(rptExpression)));
            }
        }else{
             frame.getJtaError().setText("Expression is empty!\nPlease enter expression.");

        }

    }
   private String returnErrorMessage(AnalysisInfo aAnalysisInfo){
        String outputStr="";
        for (int i=0;i< aAnalysisInfo.getArrayOfNumbersOfErrors().size();i++)
        switch (aAnalysisInfo.getArrayOfNumbersOfErrors().get(i)){
            case 701:   outputStr+="Error(701):: No elements of grammar!\n"; break;
            case 702:   outputStr+="Error(702):: Conflict brackets\n"; break;
            case 703:   outputStr+="Error(703):: Not the last element in the grammar\n";break;
            case 704:   outputStr+="Error(704):: Not Enough bracket\n"; break;
            case 7061:  outputStr+="Error(7061):: The first character is not on their site\n";break;
            case 705:   outputStr+="Error(705):: Short of the mark after an argument\n"; break;
            case 706:   outputStr+="Error(706):: Not true location of an item of expression\n";break;
            case 800:   outputStr+="Error(800):: Not enough of the expression between the brackets {(..(}.\n";break;
            case 801:   outputStr+="Error(801):: Not enough of the expression between the brackets {)..)}.\n"; break;
            case 802:   outputStr+="Error(802):: Not true location of the argument between {)..(}\n"; break;
            case 803:   outputStr+="Error(803):: After the decimal point should go argument, remove sign! { , ( or ,<argument>.. }\n";break;
            case 804:   outputStr+="Error(804):: After the brackets must be present and sign the argument{ )[sign]<argument> }\n"; break;
            case 805:   outputStr+="Error(805):: Solve the conflict between the brackets {(<argument>[sign]( }\n"; break;
            case 806:   outputStr+="Error(806):: Resolve the conflict between the brackets {)[sign]<argument>)\n";  break;
        }
        return outputStr;
    }

    private int calculationOfTheIndexErrors(AnalysisInfo aAnalysisInfo, int index){
        int numberOfCharacters=0;
        if(index>0 && aAnalysisInfo.getFirstArrayOfLexemesWithUserExpression().length>index){
            for(int i=0;i<=index;i++){
               numberOfCharacters+= aAnalysisInfo.getFirstArrayOfLexemesWithUserExpression()[i].length();
            }
        }

        if(aAnalysisInfo.getFirstArrayOfLexemesWithUserExpression().length == index){
                for(int i=0;i<=index-2;i++){
                    numberOfCharacters+= aAnalysisInfo.getFirstArrayOfLexemesWithUserExpression()[i].length();
                }
        }
     return numberOfCharacters;
    }
}
