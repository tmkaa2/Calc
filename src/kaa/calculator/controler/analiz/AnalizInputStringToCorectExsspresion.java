
package kaa.calculator.controler.analiz;



import kaa.calculator.model.charters.*;
import kaa.calculator.view.analysis.result.AnalizInfo;
import kaa.calculator.controler.analiz.str.RefactoringStrings;
import kaa.calculator.controler.analiz.str.SplitExpressionAtArrayList;
import kaa.calculator.model.SetGrammar;

import java.util.ArrayList;
import java.util.Arrays;

public class AnalizInputStringToCorectExsspresion {

    ArrayList<Bukva[]> arrayOfGrammarRules2;

    ArrayList<Integer[]> view = new ArrayList<Integer[]>();

    private ArrayList<Bukva> arrayOfGrammarRules;
    private ArrayList<Integer> arrayOfNumbersOfGrammarRules;
    private String[] arrayOflexemesWithUserExpression;
    private String userExpression;
    private String start= "I";
    private AnalizInfo analizInfo;
    
    public AnalizInputStringToCorectExsspresion(String aUserExpression)
    {
        userExpression = aUserExpression;
        arrayOfNumbersOfGrammarRules = new ArrayList<Integer>();
        analizInfo = new AnalizInfo();
        analizInfo.start=aUserExpression;
    }

    public String[] getArrayOfLexemesWithUserExpression()
    {
        return arrayOflexemesWithUserExpression;
    }


    public AnalizInfo runAnalizExpression()
    {
        init1();

        //Выделяет каждый элемент выражения в отдельную строку
        arrayOflexemesWithUserExpression = SplitExpressionAtArrayList.ParsStringPart(userExpression);
        // Проверка на коректность выражения + добавления информации о ошибках
        /*checkStr(arrayOflexemesWithUserExpression,new SetGrammar(new ArrayList<String>(Arrays.asList("+", "-", "/", "*", "0", "1", "2",
                                                                                   "3", "4", "5", "6","7", "8", "9",
                                                                                   "(", ")", "min", "max", "^", "sqrt",","
                                                                                                                   ))));*/
        //Исключительный ситуации   ok
        //checkStrHELPER(arrayOflexemesWithUserExpression);
        // Добавление завершающих символов до для распознавания LL(1)-грамматики
        arrayOflexemesWithUserExpression = SplitExpressionAtArrayList.addToStrinArray$(getArrayOfLexemesWithUserExpression());
        System.out.println("Line start:["+start+"]");

        if(analizInfo.errorFlag ==false){
            AnalitExpression2();
            System.out.println("Отработало");
        }

        System.out.println("Line end:["+start+"]");
        analizInfo.arrayOfNumbersOfGrammarRules = arrayOfNumbersOfGrammarRules;
        analizInfo.arrayOflexemesWithCustomExpression = arrayOflexemesWithUserExpression;
        return analizInfo;
    }


    // Start (Проверка на коректность грамматики и скобок)
    public void checkStr(String[] inputExpression,SetGrammar grammarElements)
    {
        boolean counterErrors=true;
        int  counterErrorsInt=0;
        int BracersFlag=0;
        String[] modifiedInpExpr=checkStrHelp(inputExpression) ;
        int counterBreaks=0;

        //Checking brackets at the expression
        for (int i=0;i<modifiedInpExpr.length;i++){
            // Amount brackets count up in the expression
            if(modifiedInpExpr[i].equals("("))
                counterBreaks++;
            if(modifiedInpExpr[i].equals(")"))
                counterBreaks--;
        }
        //[ok]
        for (int i=0;i<modifiedInpExpr.length-1;i++){

            if(counterErrors==false){
                System.err.println("Error(701)::нет элемента в грамматике");
                analizInfo.arrayOfNumbersOfErrors.add(701);
                analizInfo.errorFlag =true;
                analizInfo.arrayIndexesOfIncorrectLexeme.add(i);
                BracersFlag=1;
                break;
            }else{
                //  Checking the expression elements at the include elements of grammar.
                for (int j=0;j<grammarElements.getSet().size();j++){

                    // Do are brackets enter correct?
                    if(modifiedInpExpr[i].equals(")") && modifiedInpExpr[i+1].equals("(") ||modifiedInpExpr[i].equals("(") && modifiedInpExpr[i+1].equals(")") ){
                        System.err.println("Error(702)::конфликт скобок");
                        analizInfo.errorFlag =true;
                        analizInfo.arrayOfNumbersOfErrors.add(702);
                        analizInfo.arrayIndexesOfIncorrectLexeme.add(i);
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
            System.err.println("Error(703)::последнего  элемента нет в грамматике");
            analizInfo.arrayOfNumbersOfErrors.add(703);
            analizInfo.errorFlag =true;
            analizInfo.arrayIndexesOfIncorrectLexeme.add(inputExpression.length - 1);
            BracersFlag=1;
        }



        if(counterBreaks != 0 && BracersFlag==0){
            System.err.println("Error(704)::Нехватает скобок(и)");
            analizInfo.arrayOfNumbersOfErrors.add(704);
            analizInfo.errorFlag =true;
            analizInfo.arrayIndexesOfIncorrectLexeme.add(0);
        }

    }

    public static String[] checkStrHelp(String[] inputExpression)
    {
        for (int j=0;j<inputExpression.length;j++){
            if(((int)inputExpression[j].charAt(0)>47) && ((int)inputExpression[j].charAt(0)<58)){
                inputExpression[j]=inputExpression[j].charAt(0)+"";
            }
        }
        return inputExpression;
    }
    // end (Проверка на коректность грамматики и скобок)

      //-------------------------------------------------------------------------------


    private String remuvArgument2(String[] aLexemList,int indexLexemList,String aStart)
    {
        switch(aLexemList[indexLexemList+1].charAt(0)){
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

    private void init1()
    {
        arrayOfGrammarRules2 = new ArrayList<Bukva[]>();

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

        Bukva[] t1 = { new Bukva(",",",","")};
        Bukva[] t2 = { new Bukva("(","(","")};
        Bukva[] t3 = { new Bukva(")",")","")};

        Bukva[] t4 = { new Bukva("m","m","")};
        Bukva[] t5 = { new Bukva("^","^","")};
        Bukva[] t6 = { new Bukva("s","s","")};

        arrayOfGrammarRules2.add(iArray);
        arrayOfGrammarRules2.add(aArray);
        arrayOfGrammarRules2.add(nArray);
        arrayOfGrammarRules2.add(mArray);
        arrayOfGrammarRules2.add(kArray);
        arrayOfGrammarRules2.add(yArray);
        arrayOfGrammarRules2.add(gArray);
        arrayOfGrammarRules2.add(jArray);
        arrayOfGrammarRules2.add(zArray);
        arrayOfGrammarRules2.add(t1);
        arrayOfGrammarRules2.add(t2);
        arrayOfGrammarRules2.add(t3);
        arrayOfGrammarRules2.add(t4);
        arrayOfGrammarRules2.add(t5);
        arrayOfGrammarRules2.add(t6);
    }

    // Area of experements

    public void AnalitExpression2()
    {
        boolean sel=false;
        //cs:: st-1
        //Для первой замены
        exit1: for (int f = 0; f < arrayOfGrammarRules2.size(); f++) {
                if('I' == arrayOfGrammarRules2.get(f)[0].str.charAt(0)){
                    for (int j = 0; j < arrayOfGrammarRules2.get(f).length; j++) {
                        sel=false;
                        if(getArrayOfLexemesWithUserExpression()[0].charAt(0)== arrayOfGrammarRules2.get(f)[j].complect.charAt(0)
                                && arrayOfGrammarRules2.get(f)[j].shiftFlag == true){

                            start=RefactoringStrings.RefactoringStr(start, arrayOfGrammarRules2.get(f)[j].zamena);
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
            System.err.println("Error(7061):: Первый символ находится не на свое месте!");
            analizInfo.arrayOfNumbersOfErrors.add(7061);
            analizInfo.arrayIndexesOfIncorrectLexeme.add(0);
            return;
        }
        //Последующие замены
exitT:   for (int i = 0; i < getArrayOfLexemesWithUserExpression().length; i++) { // по пользлвательским символам

    toNext:for (int f = 0; f < arrayOfGrammarRules2.size(); f++) {
                if(RefactoringStrings.getLastCharacter(start) == arrayOfGrammarRules2.get(f)[0].str.charAt(0) &&
                        arrayOfGrammarRules2.get(f)[0].str.charAt(0) != 'I'){
                    for (int j = 0; j < arrayOfGrammarRules2.get(f).length; j++) {
                        sel=false;
                        //cs:: end-1
                        //Правила со звездочкой заменяют модифицируют строку start
                        if(getArrayOfLexemesWithUserExpression()[i].charAt(0)== arrayOfGrammarRules2.get(f)[j].complect.charAt(0)
                                && arrayOfGrammarRules2.get(f)[j].shiftFlag == true){
                            //Для случая со знаком
                            if((getArrayOfLexemesWithUserExpression()[i].charAt(0) == '*'
                                    || getArrayOfLexemesWithUserExpression()[i].charAt(0) == '/'
                                    || getArrayOfLexemesWithUserExpression()[i].charAt(0) == '-'
                                    || getArrayOfLexemesWithUserExpression()[i].charAt(0) == '+')
                                    &&RefactoringStrings.getLastCharacter(start)=='A')
                            {
                                String temp= new String(remuvArgument2(getArrayOfLexemesWithUserExpression(),i,start));
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
                                    System.err.println("Error(705)::Нехватает аргумента после знака");
                                    analizInfo.arrayOfNumbersOfErrors.add(705);
                                    analizInfo.errorFlag =true;
                                    analizInfo.arrayIndexesOfIncorrectLexeme.add(i);
                                    //break exitT;
                                    return;
                                }
                                //Правила модифицируют строку start и переходять на
                                // следующий элемент массива лексем
                            }
                            else
                            {
                                start=RefactoringStrings.RefactoringStr(start, arrayOfGrammarRules2.get(f)[j].zamena);
                                Integer[] ka1 = new Integer[2];
                                ka1[0]=f;
                                ka1[1]=j;
                                view.add(ka1);
                                sel=true;
                                f=0;
                                break;
                            }
                        }

                        if(getArrayOfLexemesWithUserExpression()[i].charAt(0)== arrayOfGrammarRules2.get(f)[j].complect.charAt(0)&&
                                arrayOfGrammarRules2.get(f)[j].shiftFlag ==false){

                            start=RefactoringStrings.RefactoringStr(start, arrayOfGrammarRules2.get(f)[j].zamena);
                            ///отладка ошибок
                            Integer[] ka1 = new Integer[2];
                            ka1[0]=f;
                            ka1[1]=j;
                            view.add(ka1);
                            sel=true;
                            break toNext;
                        }
                        // System.out.println(attayOfCharterRuless.length+" : "+attayOfCharterRuless[0].str);
                        //cs:: st-2
                    }
                    if(sel==false) {
                        System.err.println("Error(706):: Не верное расположения элемента выражения");
                        analizInfo.arrayOfNumbersOfErrors.add(706);
                        analizInfo.errorFlag =true;
                        analizInfo.arrayIndexesOfIncorrectLexeme.add(i);
                        //break exitT;
                        return;
                    }
                }else
                    continue;
            }
        }
        //cs:: end-2
    }
    public void showListUsedReules2(){
            for(int i=0;i<view.size(); i++){
                  System.out.println( (i+1)+". "+
                       arrayOfGrammarRules2.get(view.get(i)[0])[view.get(i)[1]].str+", "
                       + arrayOfGrammarRules2.get(view.get(i)[0])[view.get(i)[1]].complect+" :: "
                       + arrayOfGrammarRules2.get(view.get(i)[0])[view.get(i)[1]].zamena+" *:"
                       + arrayOfGrammarRules2.get(view.get(i)[0])[view.get(i)[1]].shiftFlag);
            }
    }

  // Исключительная ситуации Начало
    public void checkStrHELPER(String[] inputExpression)
    {
        String[] modifiedInpExpr=checkStrHelp(inputExpression);
        for (int i=0;i<modifiedInpExpr.length-2;i++){

            if(modifiedInpExpr[i].equals("(") && modifiedInpExpr[i+2].equals("(")){
                if(kaa1(modifiedInpExpr[i+1].charAt(0))){
                    System.err.println(modifiedInpExpr[i]+":"+modifiedInpExpr[i+1]+":"+modifiedInpExpr[i+2]);
                    System.err.println("Error(800).");
                    return;
                }
            }

            if(modifiedInpExpr[i].equals(")") && modifiedInpExpr[i+2].equals(")") ){
                    if(kaa1(modifiedInpExpr[i+1].charAt(0))){
                        System.err.println(modifiedInpExpr[i]+":"+modifiedInpExpr[i+1]+":"+modifiedInpExpr[i+2]);
                        System.err.println("Error(800).");
                        return;
                    }

            }

            if(modifiedInpExpr[i].equals(")") && modifiedInpExpr[i+2].equals("(") ){
                    if(kaa2(modifiedInpExpr[i+1].charAt(0))){
                        System.err.println(modifiedInpExpr[i]+":"+modifiedInpExpr[i+1]+":"+modifiedInpExpr[i+2]);
                        System.err.println("Error(802).");
                        return;
                    }
            }
        }

        for (int i=0;i<modifiedInpExpr.length-1;i++){
            if(modifiedInpExpr[i].equals(",") && kaa3(modifiedInpExpr[i+1].charAt(0))){
                System.err.println(modifiedInpExpr[i]+":"+modifiedInpExpr[i+1].charAt(0));
                System.err.println("Error(803).");
            }
        }

        if(modifiedInpExpr.length > 2){
            if(modifiedInpExpr[modifiedInpExpr.length-2].equals(")") && kaa4(modifiedInpExpr[modifiedInpExpr.length-1].charAt(0))){
                System.err.println(modifiedInpExpr[modifiedInpExpr.length-2]+":"+modifiedInpExpr[modifiedInpExpr.length-1]);
                System.err.println("Error(804).");
            }
        }
    }

    public boolean kaa1(char aStr){
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

    public boolean kaa2(char aStr){
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

    public boolean kaa3(char aStr){
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

    public boolean kaa4(char aStr){
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
    // Исключительная ситуации Конец
}




