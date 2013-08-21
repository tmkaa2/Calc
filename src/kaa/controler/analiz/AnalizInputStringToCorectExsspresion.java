
package kaa.controler.analiz;



import kaa.model.charters.*;
import kaa.view.analysis.result.AnalizInfo;
import kaa.controler.analiz.str.RefactoringStrings;
import kaa.controler.analiz.str.SplitExpressionAtArrayList;
import kaa.model.SetGrammar;

import java.util.ArrayList;
import java.util.Arrays;

public class AnalizInputStringToCorectExsspresion {
    private ArrayList<Bukva> arrayOfGrammarRules;
    private ArrayList<Integer> arrayOfNumbersOfGrammarRules;
    private String[] arrayOflexemesWithUserExpression;
    private String userExpression;
    private String start= "I";
    private AnalizInfo analizInfo;
    
    public AnalizInputStringToCorectExsspresion(String aUserExpression){
        userExpression = aUserExpression;
        arrayOfNumbersOfGrammarRules = new ArrayList<Integer>();
        analizInfo = new AnalizInfo();
        analizInfo.start=aUserExpression;
    }
    
    public AnalizInfo runAnalizExpression(){
        init1();

        //Выделяет каждый элемент выражения в отдельную строку
        arrayOflexemesWithUserExpression = SplitExpressionAtArrayList.ParsStringPart(userExpression);
        for(String str: arrayOflexemesWithUserExpression) {
            System.out.print(str+" ");
        }


        // Проверка на коректность выражения + добавления информации о ошибках
        checkStr(arrayOflexemesWithUserExpression,new SetGrammar(new ArrayList<String>(Arrays.asList("+", "-", "/", "*", "0", "1", "2",
                                                                                   "3", "4", "5", "6","7", "8", "9",
                                                                                   "(", ")", "min", "max", "^", "sqrt",","
                                                                                                                   ))));
        // Добавление завершающих символов до для распознавания LL(1)-грамматики
        arrayOflexemesWithUserExpression = SplitExpressionAtArrayList.addToStrinArray$(getArrayOfLexemesWithUserExpression());


        if(analizInfo.errorFlag ==false){
            AnalitExpression2();
        }

        analizInfo.arrayOfNumbersOfGrammarRules = arrayOfNumbersOfGrammarRules;
        analizInfo.arrayOflexemesWithCustomExpression = arrayOflexemesWithUserExpression;



        return analizInfo;
    }
    
    public void AnalitExpression(){
        boolean zamenaSimbola=false;
/*exit:*/for (int i = 0; i < getArrayOfLexemesWithUserExpression().length; i++) {
            for (int j = 0; j < arrayOfGrammarRules.size(); j++) {
                //Правила со звездочкой заменяют модифицируют строку start 
                if(getArrayOfLexemesWithUserExpression()[i].charAt(0)== arrayOfGrammarRules.get(j).complect.charAt(0)
                                && RefactoringStrings.getLastCharacter(start) == arrayOfGrammarRules.get(j).str.charAt(0)
                                                   && arrayOfGrammarRules.get(j).shiftFlag == true){
                       //Для случая со знаком
                       if((getArrayOfLexemesWithUserExpression()[i].charAt(0) == '*'
                               || getArrayOfLexemesWithUserExpression()[i].charAt(0) == '/'
                                    || getArrayOfLexemesWithUserExpression()[i].charAt(0) == '-'
                                        || getArrayOfLexemesWithUserExpression()[i].charAt(0) == '+')
                                            &&RefactoringStrings.getLastCharacter(start)=='A'){
                                            String temp= new String(remuvArgument(getArrayOfLexemesWithUserExpression(),i,start));
                                            if(start.equals(temp)== false){
                                                start=temp;
                                                arrayOfNumbersOfGrammarRules.add(j);
                                                j=0;
                                            }else{
                                                    System.err.println("Error(1). Для случая со знаком");
                                                    analizInfo.errorFlag =true;
                                                    analizInfo.arrayIndexesOfIncorrectLexeme.add(i);
                                                    zamenaSimbola=false;
                                                    break;
                                                   // break exit;
                                            }
                       //Правила модифицируют строку start и переходять на 
                       // следующий элемент массива лексем
                       }else{
                           start=RefactoringStrings.RefactoringStr(start, arrayOfGrammarRules.get(j).zamena);
                           arrayOfNumbersOfGrammarRules.add(j);
                           j=0;
                       }             
                }
                
                if(getArrayOfLexemesWithUserExpression()[i].charAt(0)== arrayOfGrammarRules.get(j).complect.charAt(0)&&
                   RefactoringStrings.getLastCharacter(start)== arrayOfGrammarRules.get(j).str.charAt(0)&&
                                                                   arrayOfGrammarRules.get(j).shiftFlag ==false){

                    start=RefactoringStrings.RefactoringStr(start, arrayOfGrammarRules.get(j).zamena);
                    ///отладка ошибок
                    arrayOfNumbersOfGrammarRules.add(j);
                    zamenaSimbola=true;
                    break;
                }
            }
            if(zamenaSimbola==false){
                analizInfo.errorFlag =true;
                System.err.println("Error. Don't true expression!(1)");
                break;
            }
        }
    }
/// Area of experements
/////
/////
/////
    public void AnalitExpression2(){
        boolean zamenaSimbola=false;
/*exit:*/for (int i = 0; i < getArrayOfLexemesWithUserExpression().length; i++) {
            for (int j = 0; j < arrayOfGrammarRules.size(); j++) {
                //Правила со звездочкой заменяют модифицируют строку start
                if(getArrayOfLexemesWithUserExpression()[i].charAt(0)== arrayOfGrammarRules.get(j).complect.charAt(0)
                        && RefactoringStrings.getLastCharacter(start) == arrayOfGrammarRules.get(j).str.charAt(0)
                        && arrayOfGrammarRules.get(j).shiftFlag == true){
                    //Для случая со знаком
                    if((getArrayOfLexemesWithUserExpression()[i].charAt(0) == '*'
                            || getArrayOfLexemesWithUserExpression()[i].charAt(0) == '/'
                            || getArrayOfLexemesWithUserExpression()[i].charAt(0) == '-'
                            || getArrayOfLexemesWithUserExpression()[i].charAt(0) == '+')
                            &&RefactoringStrings.getLastCharacter(start)=='A'){
                        String temp= new String(remuvArgument(getArrayOfLexemesWithUserExpression(),i,start));
                        if(start.equals(temp)== false){
                            start=temp;
                            arrayOfNumbersOfGrammarRules.add(j);
                            j=0;
                        }else{
                            System.err.println("Error(1). Для случая со знаком");
                            analizInfo.errorFlag =true;
                            analizInfo.arrayIndexesOfIncorrectLexeme.add(i);
                            zamenaSimbola=false;
                            break;
                            // break exit;
                        }
                        //Правила модифицируют строку start и переходять на
                        // следующий элемент массива лексем
                    }else{
                        start=RefactoringStrings.RefactoringStr(start, arrayOfGrammarRules.get(j).zamena);
                        arrayOfNumbersOfGrammarRules.add(j);
                        j=0;
                    }
                }

                if(getArrayOfLexemesWithUserExpression()[i].charAt(0)== arrayOfGrammarRules.get(j).complect.charAt(0)&&
                        RefactoringStrings.getLastCharacter(start)== arrayOfGrammarRules.get(j).str.charAt(0)&&
                        arrayOfGrammarRules.get(j).shiftFlag ==false){

                    start=RefactoringStrings.RefactoringStr(start, arrayOfGrammarRules.get(j).zamena);
                    ///отладка ошибок
                    arrayOfNumbersOfGrammarRules.add(j);
                    zamenaSimbola=true;
                    break;
                }
            }
            if(zamenaSimbola==false){
                analizInfo.errorFlag =true;
                System.err.println("Error. Don't true expression!(1)");
                break;
            }
        }
    }
    /////
/////
/////
    private void init(){
        arrayOfGrammarRules = new ArrayList<Bukva>(2);
        arrayOfGrammarRules.add(new I("(","AS"));      //0
        arrayOfGrammarRules.add(new I("min","AM"));    //1
        arrayOfGrammarRules.add(new I("max","AM"));    //2
        arrayOfGrammarRules.add(new I("^","AK"));      //3
        arrayOfGrammarRules.add(new I("sqrt","AK"));   //4
            
        arrayOfGrammarRules.add(new I("0","AN"));      //5
        arrayOfGrammarRules.add(new I("1","AN"));      //6
        arrayOfGrammarRules.add(new I("2","AN"));      //7
        arrayOfGrammarRules.add(new I("3","AN"));      //8
        arrayOfGrammarRules.add(new I("4","AN"));      //9
        arrayOfGrammarRules.add(new I("5","AN"));      //10
        arrayOfGrammarRules.add(new I("6","AN"));      //11
        arrayOfGrammarRules.add(new I("7","AN"));      //12
        arrayOfGrammarRules.add(new I("8","AN"));      //13
        arrayOfGrammarRules.add(new I("9","AN"));      //14

        arrayOfGrammarRules.add(new A("+","ANY",true));//15
        arrayOfGrammarRules.add(new A("+","AMY",true));//16
        arrayOfGrammarRules.add(new A("+","AKY",true));//17
        arrayOfGrammarRules.add(new A("+","ASY",true));//18
        arrayOfGrammarRules.add(new A("+","AY",true)); //19
        arrayOfGrammarRules.add(new A("-","ANY",true));//20
        arrayOfGrammarRules.add(new A("-","AMY",true));//21
        arrayOfGrammarRules.add(new A("-","AKY",true));//22
        arrayOfGrammarRules.add(new A("-","ASY",true));//23
        arrayOfGrammarRules.add(new A("-","AY",true));//24
        
        arrayOfGrammarRules.add(new A("*","ANY",true));//25
        arrayOfGrammarRules.add(new A("*","AMY",true));//26
        arrayOfGrammarRules.add(new A("*","AKY",true));//27
        arrayOfGrammarRules.add(new A("*","ASY",true));//28
        arrayOfGrammarRules.add(new A("*","AY",true)); //29
        
        arrayOfGrammarRules.add(new A("/","ANY",true));//30
        arrayOfGrammarRules.add(new A("/","AMY",true));//31
        arrayOfGrammarRules.add(new A("/","AKY",true));//32
        arrayOfGrammarRules.add(new A("/","ASY",true));//33
        arrayOfGrammarRules.add(new A("/","AY",true)); //34

        arrayOfGrammarRules.add(new A("0","AN",true));//35
        arrayOfGrammarRules.add(new A("1","AN",true));//36
        arrayOfGrammarRules.add(new A("2","AN",true));//37
        arrayOfGrammarRules.add(new A("3","AN",true));//38
        arrayOfGrammarRules.add(new A("4","AN",true));//39
        arrayOfGrammarRules.add(new A("5","AN",true));//40
        arrayOfGrammarRules.add(new A("6","AN",true));//41
        arrayOfGrammarRules.add(new A("7","AN",true));//42
        arrayOfGrammarRules.add(new A("8","AN",true));//43
        arrayOfGrammarRules.add(new A("9","AN",true));//44
   
        arrayOfGrammarRules.add(new A("(","AS",true));//45
        arrayOfGrammarRules.add(new A(")","AS",true));//46
        arrayOfGrammarRules.add(new A("min","AM",true));//47
        arrayOfGrammarRules.add(new A("max","AM",true));//48
        arrayOfGrammarRules.add(new A("^","AK",true));  //49
        arrayOfGrammarRules.add(new A("sqrt","AK",true));//50
        
        arrayOfGrammarRules.add(new S(")",""));        //51
        arrayOfGrammarRules.add(new S("(",""));        //52
        
        arrayOfGrammarRules.add(new N("0",""));        //53
        arrayOfGrammarRules.add(new N("1",""));        //54
        arrayOfGrammarRules.add(new N("2",""));        //55
        arrayOfGrammarRules.add(new N("3",""));        //56
        arrayOfGrammarRules.add(new N("4",""));        //57
        arrayOfGrammarRules.add(new N("5",""));        //58
        arrayOfGrammarRules.add(new N("6",""));        //59
        arrayOfGrammarRules.add(new N("7",""));        //60
        arrayOfGrammarRules.add(new N("8",""));        //61
        arrayOfGrammarRules.add(new N("9",""));        //62
        
        arrayOfGrammarRules.add(new K("sqrt","ASAS")); //63
        arrayOfGrammarRules.add(new K("^","ANSNS"));   //64
        
        arrayOfGrammarRules.add(new M("min","ASN,NS"));//65
        arrayOfGrammarRules.add(new M("max","ASN,NS"));//66

        arrayOfGrammarRules.add(new Y("+",""));        //67
        arrayOfGrammarRules.add(new Y("-",""));        //68
        arrayOfGrammarRules.add(new Y("/",""));        //69
        arrayOfGrammarRules.add(new Y("*",""));        //70
        arrayOfGrammarRules.add(new Bukva(",",",",""));//71
        arrayOfGrammarRules.add(new A("$",""));        //72

   }

    private void init1(){
        arrayOfGrammarRules = new ArrayList<Bukva>(2);


        arrayOfGrammarRules.add(new I("0","AN"));      //5
        arrayOfGrammarRules.add(new I("1","AN"));      //6
        arrayOfGrammarRules.add(new I("2","AN"));      //7
        arrayOfGrammarRules.add(new I("3","AN"));      //8
        arrayOfGrammarRules.add(new I("4","AN"));      //9
        arrayOfGrammarRules.add(new I("5","AN"));      //10
        arrayOfGrammarRules.add(new I("6","AN"));      //11
        arrayOfGrammarRules.add(new I("7","AN"));      //12
        arrayOfGrammarRules.add(new I("8","AN"));      //13
        arrayOfGrammarRules.add(new I("9","AN"));      //14
        arrayOfGrammarRules.add(new I("min","AM"));    //1
        arrayOfGrammarRules.add(new I("max","AM"));    //2
        arrayOfGrammarRules.add(new I("^","AK"));      //3
        arrayOfGrammarRules.add(new I("sqrt","AK"));   //4
        arrayOfGrammarRules.add(new I("(","G"));      //0

        arrayOfGrammarRules.add(new A("+","AY",true)); //19
        arrayOfGrammarRules.add(new A("-","AY",true));//24
        arrayOfGrammarRules.add(new A("*","AY",true)); //29
        arrayOfGrammarRules.add(new A("/","AY",true)); //34
        arrayOfGrammarRules.add(new A("0","AN",true));//35
        arrayOfGrammarRules.add(new A("1","AN",true));//36
        arrayOfGrammarRules.add(new A("2","AN",true));//37
        arrayOfGrammarRules.add(new A("3","AN",true));//38
        arrayOfGrammarRules.add(new A("4","AN",true));//39
        arrayOfGrammarRules.add(new A("5","AN",true));//40
        arrayOfGrammarRules.add(new A("6","AN",true));//41
        arrayOfGrammarRules.add(new A("7","AN",true));//42
        arrayOfGrammarRules.add(new A("8","AN",true));//43
        arrayOfGrammarRules.add(new A("9","AN",true));//44  OK

        arrayOfGrammarRules.add(new A("min","AM",true));//47
        arrayOfGrammarRules.add(new A("max","AM",true));//48
        arrayOfGrammarRules.add(new A("sqrt","AK",true));//50
        arrayOfGrammarRules.add(new A("^","AK",true));  //49

        arrayOfGrammarRules.add(new A("+","Y",true));        //67
        arrayOfGrammarRules.add(new A("-","Y",true));        //68
        arrayOfGrammarRules.add(new A("/","Y",true));        //69
        arrayOfGrammarRules.add(new A("*","Y",true));        //70

        arrayOfGrammarRules.add(new A("0","N",true));        //53
        arrayOfGrammarRules.add(new A("1","N",true));        //54
        arrayOfGrammarRules.add(new A("2","N",true));        //55
        arrayOfGrammarRules.add(new A("3","N",true));        //56
        arrayOfGrammarRules.add(new A("4","N",true));        //57
        arrayOfGrammarRules.add(new A("5","N",true));        //58
        arrayOfGrammarRules.add(new A("6","N",true));        //59
        arrayOfGrammarRules.add(new A("7","N",true));        //60
        arrayOfGrammarRules.add(new A("8","N",true));        //61
        arrayOfGrammarRules.add(new A("9","N",true));        //62

        arrayOfGrammarRules.add(new A("min","M",true));//47
        arrayOfGrammarRules.add(new A("max","M",true));//48
        arrayOfGrammarRules.add(new A("sqrt","K",true));//50
        arrayOfGrammarRules.add(new A("^","K",true));  //49
        arrayOfGrammarRules.add(new A("(","G",true));        //72
        arrayOfGrammarRules.add(new A("$",""));        //72

        arrayOfGrammarRules.add(new N("0",""));        //53
        arrayOfGrammarRules.add(new N("1",""));        //54
        arrayOfGrammarRules.add(new N("2",""));        //55
        arrayOfGrammarRules.add(new N("3",""));        //56
        arrayOfGrammarRules.add(new N("4",""));        //57
        arrayOfGrammarRules.add(new N("5",""));        //58
        arrayOfGrammarRules.add(new N("6",""));        //59
        arrayOfGrammarRules.add(new N("7",""));        //60
        arrayOfGrammarRules.add(new N("8",""));        //61
        arrayOfGrammarRules.add(new N("9",""));        //62

        arrayOfGrammarRules.add(new M("min","Zmin",true));//65
        arrayOfGrammarRules.add(new M("max","Zmax",true));//66

        arrayOfGrammarRules.add(new K("sqrt","Jsqrt",true)); //63
        arrayOfGrammarRules.add(new K("^","NJ^",true));   //64

        arrayOfGrammarRules.add(new Y("+",""));        //67
        arrayOfGrammarRules.add(new Y("-",""));        //68
        arrayOfGrammarRules.add(new Y("/",""));        //69
        arrayOfGrammarRules.add(new Y("*",""));        //70

        arrayOfGrammarRules.add(new G("(","A)A("));        //67
        arrayOfGrammarRules.add(new J("(",")A("));        //68
        arrayOfGrammarRules.add(new Z("(",")N,N("));        //69


        arrayOfGrammarRules.add(new Bukva(",",",",""));//71
        arrayOfGrammarRules.add(new Bukva("(","(",""));//71
        arrayOfGrammarRules.add(new Bukva(")",")",""));//71


    }

    
    private String remuvArgument(String[] aLexemList,int indexLexemList,String aStart){
          switch(aLexemList[indexLexemList+1].charAt(0)){
              case 'm': 
                  return RefactoringStrings.RefactoringStr(aStart,"AMY");
                
              case 's':
                  return RefactoringStrings.RefactoringStr(aStart,"AKY");
              case '^': 
                  return RefactoringStrings.RefactoringStr(aStart,"AKY");
              case '0': 
                  return RefactoringStrings.RefactoringStr(aStart,"ANY");
              case '1': 
                  return RefactoringStrings.RefactoringStr(aStart,"ANY");
              case '2': 
                  return RefactoringStrings.RefactoringStr(aStart,"ANY");
              case '3': 
                  return RefactoringStrings.RefactoringStr(aStart,"ANY");
              case '4': 
                  return RefactoringStrings.RefactoringStr(aStart,"ANY");
              case '5': 
                  return RefactoringStrings.RefactoringStr(aStart,"ANY");
              case '6': 
                  return RefactoringStrings.RefactoringStr(aStart,"ANY");
              case '7': 
                  return RefactoringStrings.RefactoringStr(aStart,"ANY");
              case '8': 
                  return RefactoringStrings.RefactoringStr(aStart,"ANY");
              case '9': 
                  return RefactoringStrings.RefactoringStr(aStart,"ANY");
              case ')': 
                  return RefactoringStrings.RefactoringStr(aStart,"ASY");
              case '(': 
                  return RefactoringStrings.RefactoringStr(aStart,"ASY");
          }
          
          return aStart;
      }
     
     public void showListUsedReules(){
         if(arrayOfNumbersOfGrammarRules != null){
                int iteration=1;
                System.out.println("Input string:: "+ userExpression);
                 System.out.println("----------------------\n"
                                   +"Start: \n"
                                   +"----------------------");
                   for (Integer integer : arrayOfNumbersOfGrammarRules) {
                       System.out.println(iteration+"-> ["+integer+"] "
                                         + arrayOfGrammarRules.get(integer).str+", "
                                         + arrayOfGrammarRules.get(integer).complect+" :: "
                                         + arrayOfGrammarRules.get(integer).zamena+" *:"
                                         + arrayOfGrammarRules.get(integer).shiftFlag);
                       iteration++;
                   }
                   System.out.println("----------------------\n"
                                   +"Lenght::'"+start.length()+"' Value::'"
                                   + start+"'\n----------------------");
            }else
             System.out.println("ATTENTION!\nCall method 'AnalitExpression()'"
                     + " and thereafter call this method 'showListUsedReules()"
                     + "'!");
     }

    /**
     * @return the arrayOflexemesWithUserExpression
     */
    public String[] getArrayOfLexemesWithUserExpression() {
        return arrayOflexemesWithUserExpression;
    }


    // Start (Проверка на коректность грамматики и скобок)
    public void checkStr(String[] inputExpression,SetGrammar grammarElements){
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

    public static String[] checkStrHelp(String[] inputExpression){
        for (int j=0;j<inputExpression.length;j++){
            if(((int)inputExpression[j].charAt(0)>47) && ((int)inputExpression[j].charAt(0)<58)){
                inputExpression[j]=inputExpression[j].charAt(0)+"";
            }
        }
        return inputExpression;
    }
    // end (Проверка на коректность грамматики и скобок)



}




