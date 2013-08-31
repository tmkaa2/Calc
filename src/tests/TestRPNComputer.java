package tests;

import kaa.calculator.controller.rpn.RPN;
import kaa.calculator.controller.rpn.computing.RPNComputer;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 30.08.13
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */
public class TestRPNComputer {
    @Test
    public void   testAssertRPNConvertExpression1(){
        RPN tets = new RPN();
        RPNComputer com = new RPNComputer();
        String[]  ex ={"max","(","25",",","15",")"};
        ArrayList<String> ex1=tets.convertToRPN2(ex);
        org.junit.Assert.assertEquals("failure - answers not same", (int) 0x19,  (int)com.computing(ex1));
    }

    @Test
    public void   testAssertRPNConvertExpression2(){
        RPN tets = new RPN();
        RPNComputer com = new RPNComputer();
        String[]  ex ={"max","(","25",",","15",")","+","15","/","3"};
        ArrayList<String> ex1=tets.convertToRPN2(ex);
        org.junit.Assert.assertEquals("failure - answers not same", 0x1e,  (int)com.computing(ex1));
    }

    @Test
    public void   testAssertRPNConvertExpression3(){
        RPN tets = new RPN();
        RPNComputer com = new RPNComputer();
        String[]  ex ={"max","(","25",",","15",")","+","20","/","(","25","+","35","/","7","-","sqrt","(","100",")",")"};
        ArrayList<String> ex1=tets.convertToRPN2(ex);
        org.junit.Assert.assertEquals("failure - answers not same", 0x1a,  (int)com.computing(ex1));
    }

    @Test
    public void   testAssertRPNConvertExpression4(){
        RPN tets = new RPN();
        RPNComputer com = new RPNComputer();
        String[]  ex ={"^","(","2","+","3","*","2","/","6",")","2","+","(","max","(","25",",","15",")","+","20","/","(","25","+","35","/","7","-","sqrt","(","100",")",")",")",};
        ArrayList<String> ex1=tets.convertToRPN2(ex);
        org.junit.Assert.assertEquals("failure - answers not same", 0x23,  (int)com.computing(ex1));
    }
}
