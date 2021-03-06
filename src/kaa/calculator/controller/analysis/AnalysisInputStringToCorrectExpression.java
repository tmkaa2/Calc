
package kaa.calculator.controller.analysis;



import kaa.calculator.controller.analysis.manipulations.expression.SplitExpressionInArrayList;
import kaa.calculator.model.grammar.rules.*;
import kaa.calculator.model.analysis.result.AnalysisInfo;
import kaa.calculator.controller.analysis.manipulations.expression.RefactoringStrings;
import kaa.calculator.model.SetGrammar;

import java.util.ArrayList;
import java.util.Arrays;

public class AnalysisInputStringToCorrectExpression {

    private ArrayList<RootRule[]> arrayOfGrammarRules;
    private ArrayList<Integer[]> view = new ArrayList<Integer[]>();
    private ArrayList<Integer> arrayOfNumbersOfGrammarRules;
    private String[] arrayOfLexemesWithUserExpression;
    private String userExpression;
    private String start= "I";
    private AnalysisInfo analysisInfo;
    
    public AnalysisInputStringToCorrectExpression(String aUserExpression)
    {
        userExpression = aUserExpression;
        arrayOfNumbersOfGrammarRules = new ArrayList<Integer>();
        analysisInfo = new AnalysisInfo();
        analysisInfo.setStart(aUserExpression);
    }

    public AnalysisInfo runAnalysisExpression()
    {
        initGrammarRules();
        arrayOfLexemesWithUserExpression = SplitExpressionInArrayList.ParsStringPart(userExpression);
        analysisInfo.setFirstArrayOfLexemesWithUserExpression(SplitExpressionInArrayList.ParsStringPart(userExpression));
        checkExpressionOnTheErrorsPartOne(arrayOfLexemesWithUserExpression, new SetGrammar(new ArrayList<String>(
           Arrays.asList("+","-","/","*","0","1","2","3","4","5","6","7","8","9","(",")","min","max","^","sqrt",","))));
        if(analysisInfo.isErrorFlag())
            return analysisInfo;

        checkExpressionOnTheErrorsPartTwo(arrayOfLexemesWithUserExpression);
        arrayOfLexemesWithUserExpression = SplitExpressionInArrayList.addToStrinArray$(arrayOfLexemesWithUserExpression);

        if(analysisInfo.isErrorFlag() ==false){
            recognitionGrammarOfExpression();
        }

        analysisInfo.setArrayOfNumbersOfGrammarRules(arrayOfNumbersOfGrammarRules);
        analysisInfo.setArrayOflexemesWithCustomExpression(arrayOfLexemesWithUserExpression);

        return analysisInfo;
    }

    public void showListUsedRules(){
        for(int i=0;i<view.size(); i++){
            System.out.println( (i+1)+". "+
                    arrayOfGrammarRules.get(view.get(i)[0])[view.get(i)[1]].str+", "
                    + arrayOfGrammarRules.get(view.get(i)[0])[view.get(i)[1]].complect+" :: "
                    + arrayOfGrammarRules.get(view.get(i)[0])[view.get(i)[1]].zamena+" *:"
                    + arrayOfGrammarRules.get(view.get(i)[0])[view.get(i)[1]].shiftFlag);
        }
    }

    private void initGrammarRules()
    {
        arrayOfGrammarRules = new ArrayList<RootRule[]>();
        I[] iArray = { new I("0","AN"),new I("1","AN"),new I("2","AN"),new I("3","AN"),new I("4","AN"),
                new I("5","AN"),new I("6","AN"),new I("7","AN"),new I("8","AN"),new I("9","AN"),
                new I("min","AM"),new I("max","AM"),
                new I("^","AK"),new I("sqrt","AK"),
                new I("(","G")
        };

        A[] aArray =  { new A("+","AY",true),new A("-","AY",true),new A("*","AY",true),new A("/","AY",true),
                new A("0","AN",true),new A("1","AN",true),new A("2","AN",true),new A("3","AN",true),new A("4","AN",true),
                new A("5","AN",true),new A("6","AN",true),new A("7","AN",true),new A("8","AN",true),new A("9","AN",true),
                new A("min","AM",true),new A("max","AM",true),
                new A("sqrt","AK",true),new A("^","AK",true),
                new A("(","G",true),new A("$","",false)
        };

        N[] nArray = { new N("0",""),new N("1",""),new N("2",""),new N("3",""),new N("4",""),
                new N("5",""),new N("6",""),new N("7",""),new N("8",""),new N("9","")
        };

        M[] mArray = { new M("min","Zm",true),new M("max","Zm",true)};

        K[] kArray = { new K("sqrt","Js",true),new K("^","NJ^",true)};

        Y[] yArray = { new Y("+",""),new Y("-","") ,new Y("/",""),new Y("*","")};
        G[] gArray = { new G("(","A)A(",true)};
        J[] jArray = { new J("(",")A(",true)};
        //Z[] zArray = { new Z("(",")N,N(",true)};
        Z[] zArray = { new Z("(",")A,A(",true)};

        RootRule[] t1 = { new RootRule(",",",","")};
        RootRule[] t2 = { new RootRule("(","(","")};
        RootRule[] t3 = { new RootRule(")",")","")};

        RootRule[] t4 = { new RootRule("m","m","")};
        RootRule[] t5 = { new RootRule("^","^","")};
        RootRule[] t6 = { new RootRule("s","s","")};

        arrayOfGrammarRules.add(iArray);
        arrayOfGrammarRules.add(aArray);
        arrayOfGrammarRules.add(nArray);
        arrayOfGrammarRules.add(mArray);
        arrayOfGrammarRules.add(kArray);
        arrayOfGrammarRules.add(yArray);
        arrayOfGrammarRules.add(gArray);
        arrayOfGrammarRules.add(jArray);
        arrayOfGrammarRules.add(zArray);
        arrayOfGrammarRules.add(t1);
        arrayOfGrammarRules.add(t2);
        arrayOfGrammarRules.add(t3);
        arrayOfGrammarRules.add(t4);
        arrayOfGrammarRules.add(t5);
        arrayOfGrammarRules.add(t6);
    }

    // Check for correct grammar and brackets (Start)
    private void checkExpressionOnTheErrorsPartOne(String[] inputExpression, SetGrammar grammarElements)
    {
        boolean counterErrors=true;
        int  counterErrorsInt=0;
        int BracersFlag=0;
        String[] modifiedInpExpr= helpCheckErrorsPartOne(inputExpression) ;
        int counterBreaks=0;

        //Checking brackets at the expression
        for (int i=0;i<modifiedInpExpr.length;i++){
            // Amount brackets count up in the expression
            if(modifiedInpExpr[i].equals("("))
                counterBreaks++;
            if(modifiedInpExpr[i].equals(")"))
                counterBreaks--;
        }

        for (int i=0;i<modifiedInpExpr.length-1;i++){
            if(counterErrors==false){
                System.err.println("Error(701)");
                analysisInfo.getArrayOfNumbersOfErrors().add(701);
                analysisInfo.setErrorFlag(true);
                analysisInfo.getArrayIndexesOfIncorrectLexeme().add(i);
                BracersFlag=1;
                break;
            }else{
                //  Checking the expression elements at the include elements of grammar.
                for (int j=0;j<grammarElements.getSet().size();j++){
                    // Do are brackets enter correct?
                    if(modifiedInpExpr[i].equals(")") && modifiedInpExpr[i+1].equals("(") ||modifiedInpExpr[i].equals("(") && modifiedInpExpr[i+1].equals(")") ){
                        System.err.println("Error(702)");
                        analysisInfo.setErrorFlag(true);
                        analysisInfo.getArrayOfNumbersOfErrors().add(702);
                        analysisInfo.getArrayIndexesOfIncorrectLexeme().add(i);
                        i= modifiedInpExpr.length;
                        BracersFlag=1;
                        counterErrors=true;
                        break;
                    }
                    counterErrorsInt=0;
                    if(grammarElements.getSet().get(j).equals(modifiedInpExpr[i])== true){
                        counterErrors=true;
                        counterErrorsInt=1;
                        break;
                    }
                }
                if(counterErrorsInt==0){
                    counterErrors=false;
                }
            }
        }

        counterErrors = false;
        //  Checking the expression elements at the include elements of grammar for last element expression.
        for (int j=0;j<grammarElements.getSet().size();j++){
            if(grammarElements.getSet().get(j).equals(inputExpression[inputExpression.length-1])== true){
                counterErrors=true;
            }
        }

        if(counterErrors==false){
            System.err.println("Error(703)");
            analysisInfo.getArrayOfNumbersOfErrors().add(703);
            analysisInfo.setErrorFlag(true);
            analysisInfo.getArrayIndexesOfIncorrectLexeme().add(inputExpression.length - 1);
            BracersFlag=1;
        }

        if(counterBreaks != 0 && BracersFlag==0){
            System.err.println("Error(704)");
            analysisInfo.getArrayOfNumbersOfErrors().add(704);
            analysisInfo.setErrorFlag(true);
            analysisInfo.getArrayIndexesOfIncorrectLexeme().add(0);
        }
    }

    private static String[] helpCheckErrorsPartOne(String[] inputExpression)
    {
        for (int j=0;j<inputExpression.length;j++){
            if(((int)inputExpression[j].charAt(0)>47) && ((int)inputExpression[j].charAt(0)<58)){
                inputExpression[j]=inputExpression[j].charAt(0)+"";
            }
        }
        return inputExpression;
    }
    // Check for correct grammar and brackets (end)

    // Recognition grammar(Start)
    private void recognitionGrammarOfExpression()
    {
        boolean sel=false;
        //Для первой замены
        exit1: for (int f = 0; f < arrayOfGrammarRules.size(); f++) {
                if('I' == arrayOfGrammarRules.get(f)[0].str.charAt(0)){
                    for (int j = 0; j < arrayOfGrammarRules.get(f).length; j++) {
                        sel=false;
                        if(arrayOfLexemesWithUserExpression[0].charAt(0)== arrayOfGrammarRules.get(f)[j].complect.charAt(0)
                                && arrayOfGrammarRules.get(f)[j].shiftFlag == true){

                            start=RefactoringStrings.RefactoringStr(start, arrayOfGrammarRules.get(f)[j].zamena);
                            Integer[] ka1 = new Integer[2];
                            ka1[0]=f;
                            ka1[1]=j;
                            view.add(ka1);
                            sel=true;
                            f=0;
                            break exit1;
                        }
                    }
                    break;
                }
            }

        if(sel==false) {
            System.err.println("Error(7061)");
            analysisInfo.getArrayOfNumbersOfErrors().add(7061);
            analysisInfo.getArrayIndexesOfIncorrectLexeme().add(0);
            return;
        }
        // Subsequent replacement
    exitT:for (int i = 0; i < arrayOfLexemesWithUserExpression.length; i++) {
        toNext:for (int f = 0; f < arrayOfGrammarRules.size(); f++) {
                if(RefactoringStrings.getLastCharacter(start) == arrayOfGrammarRules.get(f)[0].str.charAt(0) &&
                        arrayOfGrammarRules.get(f)[0].str.charAt(0) != 'I'){
                    for (int j = 0; j < arrayOfGrammarRules.get(f).length; j++) {
                        sel=false;
                        //Rules are modified with an asterisk replaces the string start
                        if(arrayOfLexemesWithUserExpression[i].charAt(0)== arrayOfGrammarRules.get(f)[j].complect.charAt(0)
                                && arrayOfGrammarRules.get(f)[j].shiftFlag == true){
                            //For the case of a signed
                            if((arrayOfLexemesWithUserExpression[i].charAt(0) == '*'
                                    || arrayOfLexemesWithUserExpression[i].charAt(0) == '/'
                                    || arrayOfLexemesWithUserExpression[i].charAt(0) == '-'
                                    || arrayOfLexemesWithUserExpression[i].charAt(0) == '+')
                                    &&RefactoringStrings.getLastCharacter(start)=='A')
                            {
                                String temp= new String(helpRecognitionGrammar(arrayOfLexemesWithUserExpression, i, start));
                                if(start.equals(temp)== false){
                                    start=temp;
                                    Integer[] ka1 = new Integer[2];
                                    ka1[0]=f;
                                    ka1[1]=j;
                                    view.add(ka1);
                                    sel=true;
                                    f=0;
                                    break toNext;
                                }else{
                                    System.err.println("Error(705)");
                                    analysisInfo.getArrayOfNumbersOfErrors().add(705);
                                    analysisInfo.setErrorFlag(true);
                                    analysisInfo.getArrayIndexesOfIncorrectLexeme().add(i);
                                    //break exitT;
                                    return;
                                }
                                //Rules are modified row start and moves to the next element of the array of tokens
                            }
                            else
                            {
                                start=RefactoringStrings.RefactoringStr(start, arrayOfGrammarRules.get(f)[j].zamena);
                                Integer[] ka1 = new Integer[2];
                                ka1[0]=f;
                                ka1[1]=j;
                                view.add(ka1);
                                sel=true;
                                f=0;
                                break;
                            }
                        }

                        if(arrayOfLexemesWithUserExpression[i].charAt(0)== arrayOfGrammarRules.get(f)[j].complect.charAt(0)&&
                                arrayOfGrammarRules.get(f)[j].shiftFlag ==false){
                            start=RefactoringStrings.RefactoringStr(start, arrayOfGrammarRules.get(f)[j].zamena);
                            Integer[] ka1 = new Integer[2];
                            ka1[0]=f;
                            ka1[1]=j;
                            view.add(ka1);
                            sel=true;
                            break toNext;
                        }
                    }
                    if(sel==false) {
                        System.err.println("Error(706)");
                        analysisInfo.getArrayOfNumbersOfErrors().add(706);
                        analysisInfo.setErrorFlag(true);
                        analysisInfo.getArrayIndexesOfIncorrectLexeme().add(i);
                        System.err.println(start);
                        return;
                    }
                }else
                    continue;
            }
        }
    }

    private String helpRecognitionGrammar(String[] aLexemeList, int indexLexemList, String aStart)
    {
        switch(aLexemeList[indexLexemList+1].charAt(0)){
            case 'm':
                return RefactoringStrings.RefactoringStr(aStart,"AM");
            case 's':
                return RefactoringStrings.RefactoringStr(aStart,"AK");
            case '^':
                return RefactoringStrings.RefactoringStr(aStart,"AK");
            case '0':
                return RefactoringStrings.RefactoringStr(aStart,"AN");
            case '1':
                return RefactoringStrings.RefactoringStr(aStart,"AN");
            case '2':
                return RefactoringStrings.RefactoringStr(aStart,"AN");
            case '3':
                return RefactoringStrings.RefactoringStr(aStart,"AN");
            case '4':
                return RefactoringStrings.RefactoringStr(aStart,"AN");
            case '5':
                return RefactoringStrings.RefactoringStr(aStart,"AN");
            case '6':
                return RefactoringStrings.RefactoringStr(aStart,"AN");
            case '7':
                return RefactoringStrings.RefactoringStr(aStart,"AN");
            case '8':
                return RefactoringStrings.RefactoringStr(aStart,"AN");
            case '9':
                return RefactoringStrings.RefactoringStr(aStart,"AN");
            case '-':
                return RefactoringStrings.RefactoringStr(aStart,"A");
            case '+':
                return RefactoringStrings.RefactoringStr(aStart,"A");
            case '/':
                return RefactoringStrings.RefactoringStr(aStart,"A");
            case '*':
                return RefactoringStrings.RefactoringStr(aStart,"A");
            case '(':
                return RefactoringStrings.RefactoringStr(aStart,"A(");
        }
        return aStart;
    }
    // Recognition grammar (End)

    // Checking the second level (Start)
    private void checkExpressionOnTheErrorsPartTwo(String[] inputExpression)
    {
        String[] modifiedInpExpr= helpCheckErrorsPartOne(inputExpression);
        for (int i=0;i<modifiedInpExpr.length-2;i++){

            if(modifiedInpExpr[i].equals("(") && modifiedInpExpr[i+2].equals("(")){
                if(checkCharacterFromSetOfOne(modifiedInpExpr[i + 1].charAt(0))){
                    System.err.println(modifiedInpExpr[i]+":"+modifiedInpExpr[i+1]+":"+modifiedInpExpr[i+2]);
                    System.err.println("Error(800).");
                    analysisInfo.getArrayOfNumbersOfErrors().add(800);
                    analysisInfo.setErrorFlag(true);
                    analysisInfo.getArrayIndexesOfIncorrectLexeme().add(i + 1);
                    return;
                }
            }

            if(modifiedInpExpr[i].equals(")") && modifiedInpExpr[i+2].equals(")") ){
                    if(checkCharacterFromSetOfFour(modifiedInpExpr[i + 1].charAt(0))){
                        if(!helpsToCheckTheExpressionOfOneCharacter(i + 2, modifiedInpExpr)){
                            System.err.println(modifiedInpExpr[i]+":"+modifiedInpExpr[i+1]+":"+modifiedInpExpr[i+2]);
                            System.err.println("Error(801).");
                            analysisInfo.getArrayOfNumbersOfErrors().add(801);
                            analysisInfo.setErrorFlag(true);
                            analysisInfo.getArrayIndexesOfIncorrectLexeme().add(i + 1);
                            return;
                        }
                    }

            }

            if(modifiedInpExpr[i].equals(")") && modifiedInpExpr[i+2].equals("(") ){
                    if(checkCharacterFromSetOfTwo(modifiedInpExpr[i + 1].charAt(0))){
                        System.err.println(modifiedInpExpr[i]+":"+modifiedInpExpr[i+1]+":"+modifiedInpExpr[i+2]);
                        System.err.println("Error(802).");
                        analysisInfo.getArrayOfNumbersOfErrors().add(802);
                        analysisInfo.setErrorFlag(true);
                        analysisInfo.getArrayIndexesOfIncorrectLexeme().add(i + 1);
                        return;
                    }
            }
        }

        for (int i=0;i<modifiedInpExpr.length-1;i++){
            if(modifiedInpExpr[i].equals(",") && checkCharacterFromSetOfThree(modifiedInpExpr[i + 1].charAt(0))){
                System.err.println(modifiedInpExpr[i]+":"+modifiedInpExpr[i+1].charAt(0));
                System.err.println("Error(803).");
                analysisInfo.getArrayOfNumbersOfErrors().add(803);
                analysisInfo.setErrorFlag(true);
                analysisInfo.getArrayIndexesOfIncorrectLexeme().add(i);
                return;
            }
        }

        for(int z=modifiedInpExpr.length-1;1<z;z--){
            if(modifiedInpExpr[z-1].equals(")") && checkCharacterFromSetOfTwo(modifiedInpExpr[z].charAt(0))){
                if(!helpsToCheckTheExpressionOfOneCharacter(z, modifiedInpExpr)){
                    System.err.println(modifiedInpExpr[z-1]+":"+modifiedInpExpr[z]);
                    System.err.println("Error(804).");
                    analysisInfo.getArrayOfNumbersOfErrors().add(804);
                    analysisInfo.setErrorFlag(true);
                    analysisInfo.getArrayIndexesOfIncorrectLexeme().add(modifiedInpExpr.length);
                    return;
                }
            }
        }

        for (int i=0;i<modifiedInpExpr.length-3;i++){

            if(modifiedInpExpr[i].equals("(") && modifiedInpExpr[i+3].equals("(")){
                if(checkCharacterFromSetOfThree(modifiedInpExpr[i + 1].charAt(0)) && checkCharacterFromSetOfTwo(modifiedInpExpr[i + 2].charAt(0))){
                    System.err.println(modifiedInpExpr[i]+":"+modifiedInpExpr[i+1]+":"+modifiedInpExpr[i+2]
                                                                                  +":"+modifiedInpExpr[i+3]);
                    System.err.println("Error(805).");
                    analysisInfo.getArrayOfNumbersOfErrors().add(805);
                    analysisInfo.setErrorFlag(true);
                    analysisInfo.getArrayIndexesOfIncorrectLexeme().add(i + 2);
                    return;
                }
            }
        }

        for (int i=0;i<modifiedInpExpr.length-3;i++){

            if(modifiedInpExpr[i].equals(")") && modifiedInpExpr[i+3].equals(")")){
                if(checkCharacterFromSetOfTwo(modifiedInpExpr[i + 1].charAt(0)) && checkCharacterFromSetOfSix(modifiedInpExpr[i + 2].charAt(0))){
                    System.err.println(modifiedInpExpr[i]+":"+modifiedInpExpr[i+1]+":"+modifiedInpExpr[i+2]
                            +":"+modifiedInpExpr[i+3]);
                    System.err.println("Error(806).");
                    analysisInfo.getArrayOfNumbersOfErrors().add(805);
                    analysisInfo.setErrorFlag(true);
                    analysisInfo.getArrayIndexesOfIncorrectLexeme().add(i + 2);
                    return;
                }
            }
        }
    }

    private boolean checkCharacterFromSetOfOne(char aStr){
        switch (aStr){
            case '0':
                return true ;
            case '1':
                return true ;
            case '2':
                return true ;
            case '3':
                return true ;
            case '4':
                return true ;
            case '5':
                return true ;
            case '6':
                return true ;
            case '7':
                return true ;
            case '8':
                return true ;
            case '9':
                return true ;
            case '-':
                return true ;
            case '+':
                return true ;
            case '/':
                return true ;
            case '*':
                return true ;
        }
        return false;
    }

    private boolean checkCharacterFromSetOfTwo(char aStr){
        switch (aStr){
            case '0':
                return true ;
            case '1':
                return true ;
            case '2':
                return true ;
            case '3':
                return true ;
            case '4':
                return true ;
            case '5':
                return true ;
            case '6':
                return true ;
            case '7':
                return true ;
            case '8':
                return true ;
            case '9':
                return true ;
            case 'm':
                return true ;
            case '^':
                return true ;
            case 's':
                return true ;
        }
        return false;
    }

    private boolean checkCharacterFromSetOfThree(char aStr){
        switch (aStr){

            case '+':
                return true ;
            case '-':
                return true ;
            case '/':
                return true ;
            case '*':
                return true ;
        }
        return false;
    }

    private boolean checkCharacterFromSetOfFour(char aStr){
        switch (aStr){
            case '0':
                return true ;
            case '1':
                return true ;
            case '2':
                return true ;
            case '3':
                return true ;
            case '4':
                return true ;
            case '5':
                return true ;
            case '6':
                return true ;
            case '7':
                return true ;
            case '8':
                return true ;
            case '9':
                return true ;
            case 'm':
                return true ;
            case '^':
                return true ;
            case 's':
                return true ;
            case '+':
                return true ;
            case '-':
                return true ;
            case '/':
                return true ;
            case '*':
                return true ;
        }
        return false;
    }

    private boolean checkCharacterFromSetOfFive(char aStr){
        switch (aStr){
            case '0':
                return true ;
            case '1':
                return true ;
            case '2':
                return true ;
            case '3':
                return true ;
            case '4':
                return true ;
            case '5':
                return true ;
            case '6':
                return true ;
            case '7':
                return true ;
            case '8':
                return true ;
            case '9':
                return true ;
        }
        return false;
    }

    private boolean checkCharacterFromSetOfSix(char aStr){
        switch (aStr){

            case '+':
                return true ;
            case '-':
                return true ;
            case '/':
                return true ;
            case '*':
                return true ;
            case 'm':
                return true ;
            case '^':
                return true ;
            case 's':
                return true ;
        }
        return false;
    }

    private boolean helpsToCheckTheExpressionOfOneCharacter(int start, String[] modifiedInpExpr){
        int counterBracketsL= 0;
        int counterBracketsR= 0;
        int flag2 =0;
        int flag3 =0;
        boolean flag = false;
        for (int i=start-1;i >= 0;i--){
            if(modifiedInpExpr[i].equals(")") && checkCharacterFromSetOfFive(modifiedInpExpr[i-1].charAt(0))){
                flag2++;
                flag=true;
            }

            if(modifiedInpExpr[i].equals("^")){
                flag3++;
            }

            if(modifiedInpExpr[i].equals(")"))
                counterBracketsL++;

            if(modifiedInpExpr[i].equals("("))
                counterBracketsR++;

            if(modifiedInpExpr[i].equals("^") && counterBracketsL==counterBracketsR && flag){
                if(flag2 != flag3){
                    System.err.println("Error(605)");
                    return false;
                }else
                return true;
            }

        }
        return false;
    }
    //  Checking the second level (End)
}




