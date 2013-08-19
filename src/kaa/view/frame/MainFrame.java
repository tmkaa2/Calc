package kaa.view.frame;

import kaa.view.panel.MainPanel;

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
        setSize(350, 150);
        panel = new MainPanel( labelExpression,labelMsgError,jtfExpression,jtfResult,jtaError, result);

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
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
}
