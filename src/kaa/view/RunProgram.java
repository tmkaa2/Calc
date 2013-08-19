package kaa.view;

import javax.swing.*;
import kaa.view.frame.MainFrame;
/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 14.08.13
 * Time: 2:55
 * To change this template use File | Settings | File Templates.
 */
public class RunProgram {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new MainFrame();
            }
        });
    }
}
