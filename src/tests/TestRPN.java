package tests;
import kaa.calculator.controller.rpn.RPN;
import org.junit.Test;

import java.util.ArrayList;

public class TestRPN {
    @Test
    public void   testAssertRPNConvertExpression1(){
        RPN tets = new RPN();
        String resultSTR="";
        String trueStr="315*2/-";
        String[]  ex ={"3","-","1","*","5","/","2"};
        ArrayList<String> ex1=tets.convertToRPN2(ex);
        System.out.println("");
        for (String ss: ex1){
            resultSTR+=ss;
        }
        org.junit.Assert.assertEquals("failure - rpn strings not same", trueStr, resultSTR);
    }

    @Test
    public void   testAssertRPNConvertExpression2(){
        RPN tets = new RPN();
        String resultSTR="";
        String trueStr="5515min108+2/+";
        String[]  ex ={"min","(","55",",","15",")","+","(","10","+","8",")","/","2"};
        ArrayList<String> ex1=tets.convertToRPN2(ex);
        System.out.println("");
        for (String ss: ex1){
            resultSTR+=ss;
        }
        org.junit.Assert.assertEquals("failure - rpn strings not same", trueStr, resultSTR);
    }
    @Test
    public void   testAssertRPNConvertExpression3(){
        RPN tets = new RPN();
        String resultSTR="";
        String trueStr="2133max1015+sqrt+32*";
        String[]  ex ={"(","max","(","21",",","33",")","+","sqrt","(","101","+","5",")",")","*","32"};
        ArrayList<String> ex1=tets.convertToRPN2(ex);
        System.out.println("");
        for (String ss: ex1){
            resultSTR+=ss;
        }
        org.junit.Assert.assertEquals("failure - rpn strings not same", trueStr, resultSTR);
    }

    @Test
    public void   testAssertRPNConvertExpression4(){
        RPN tets = new RPN();
        String resultSTR="";
        String trueStr="66633/+4^";
        String[]  ex ={"^","(","6","+","66","/","33",")","4",};
        ArrayList<String> ex1=tets.convertToRPN2(ex);
        System.out.println("");
        for (String ss: ex1){
            resultSTR+=ss;
        }
        org.junit.Assert.assertEquals("failure - rpn strings not same", trueStr, resultSTR);
    }
}
