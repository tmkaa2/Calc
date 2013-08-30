
package kaa.calculator.controller.analysis.manipulations.expression;
public class HelperSplitExpression {
    private String tempNum="";
    private int index=0;

    public HelperSplitExpression(){
        setTempNum("");
        setIndex(0);
    }

    public String getTempNum() {
        return tempNum;
    }

    public void setTempNum(String tempNum) {
        this.tempNum = tempNum;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
