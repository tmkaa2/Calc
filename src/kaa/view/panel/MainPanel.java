package kaa.view.panel;

import javax.swing.*;
public class MainPanel extends JPanel {
    public MainPanel(JLabel aLabelExpression, JLabel aLabelMsgError, JTextField aJtfExpression, JTextField aJtfResult,
                                                                                  JTextArea aJtaError, JButton aResult){
        setLayout(new SpringLayout());
        aLabelExpression = new JLabel("Expression: ", JLabel.TRAILING);
        aLabelMsgError = new JLabel("Error msg: ", JLabel.TRAILING);
        aJtfExpression = new JTextField(30);
        aJtfResult = new JTextField(30);
        aJtaError = new JTextArea(4,30);
        aResult = new JButton("result");

        JScrollPane infoScroller = new JScrollPane(aJtaError);
        infoScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        infoScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(aLabelExpression);
        add(aJtfExpression);
        add(aResult);
        add(aJtfResult);
        add(aLabelMsgError);
        add(infoScroller);

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(this,
                3, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        setOpaque(true);
    }
}
