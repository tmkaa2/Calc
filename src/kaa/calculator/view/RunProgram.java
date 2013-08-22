package kaa.calculator.view;

import kaa.calculator.controler.form.ElementControlGeter;

import javax.swing.*;



/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 14.08.13
 * Time: 2:55
 * To change this template use File | Settings | File Templates.
 */
public class RunProgram extends ElementControlGeter {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                frame.setVisible(true);


            }
        });
    }
}
