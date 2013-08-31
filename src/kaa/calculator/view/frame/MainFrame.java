package kaa.calculator.view.frame;

import kaa.calculator.controller.button.ButtonListener;
import kaa.calculator.view.panel.MainPanel;
import javax.swing.*;

public class MainFrame extends JFrame{
    private JLabel labelExpression;
    private JLabel labelMsgError;
    private JTextField jtfExpression;
    private JTextField jtfResult;
    private JTextArea jtaError;
    private JButton result;
    private MainPanel panel;

    public MainFrame(){
        setTitle("String calculator[TMKAA]");
        setSize(550, 150);

        labelExpression = new JLabel("Expression: ", JLabel.TRAILING);
        labelMsgError = new JLabel("Error msg: ", JLabel.TRAILING);
        setJtfExpression(new JTextField(30));
        jtfResult = new JTextField(30);
        jtaError = new JTextArea(4,30);
        result = new JButton("result");
        JScrollPane infoScroller = new JScrollPane(jtaError);
        infoScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        infoScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        result.addActionListener(new ButtonListener());

        panel = new MainPanel( labelExpression,labelMsgError, getJtfExpression(),jtfResult,infoScroller, result);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public JLabel getLabelMsgError() {
        return labelMsgError;
    }

    public void setLabelMsgError(JLabel labelMsgError) {
        this.labelMsgError = labelMsgError;
    }

    public JTextField getJtfResult() {
        return jtfResult;
    }

    public void setJtfResult(JTextField jtfResult) {
        this.jtfResult = jtfResult;
    }

    public JTextArea getJtaError() {
        return jtaError;
    }

    public void setJtaError(JTextArea jtaError) {
        this.jtaError = jtaError;
    }

    public JButton getResult() {
        return result;
    }

    public void setResult(JButton result) {
        this.result = result;
    }

    public MainPanel getPanel() {
        return panel;
    }

    public void setPanel(MainPanel panel) {
        this.panel = panel;
    }

    public JTextField getJtfExpression() {
        return jtfExpression;
    }

    public void setJtfExpression(JTextField jtfExpression) {
        this.jtfExpression = jtfExpression;
    }
}
