package kaa.controler.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kaa.controler.form.ElementControlGeter;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 19.08.13
 * Time: 16:15
 * To change this template use File | Settings | File Templates.
 */
public class ButtonListener extends ElementControlGeter implements ActionListener  {

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputExpression = frame.getJtfExpression().getText();
        if(inputExpression.length() != 0){
            frame.getJtaError().setText("");






            System.out.println("enter");

        }else{
             frame.getJtaError().setText("Expression is empty!\nPlease enter expression.");

        }

    }
}
