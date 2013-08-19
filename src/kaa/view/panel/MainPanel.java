package kaa.view.panel;

import kaa.controler.button.ButtonListener;

import javax.swing.*;
public class MainPanel extends JPanel {
    public MainPanel(JLabel aLabelExpression, JLabel aLabelMsgError, JTextField aJtfExpression, JTextField aJtfResult,
                     JScrollPane aInfoScroller, JButton aResult){

        setLayout(new SpringLayout());
        add(aLabelExpression);
        add(aJtfExpression);
        add(aResult);
        add(aJtfResult);
        add(aLabelMsgError);
        add(aInfoScroller);

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(this,
                3, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        setOpaque(true);
    }
}
