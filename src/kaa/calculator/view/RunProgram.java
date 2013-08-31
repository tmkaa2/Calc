package kaa.calculator.view;

import kaa.calculator.controller.frame.ElementControlGeter;
import javax.swing.*;

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
