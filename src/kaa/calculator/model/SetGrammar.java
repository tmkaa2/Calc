package kaa.calculator.model;
import java.util.ArrayList;

public class SetGrammar {

    private ArrayList<String> set;

    public SetGrammar(ArrayList<String> aSet){
        set=aSet;
    }

    public ArrayList<String> getSet() {
        return set;
    }

    public void setSet(ArrayList<String> set) {
        this.set = set;
    }
}
