
package kaa.controler.analiz;



import kaa.controler.analiz.rez.AnalizRez;
import kaa.controler.analiz.charters.A;
import kaa.controler.analiz.charters.Bukva;
import kaa.controler.analiz.charters.I;
import kaa.controler.analiz.charters.K;
import kaa.controler.analiz.charters.M;
import kaa.controler.analiz.charters.N;
import kaa.controler.analiz.charters.S;
import kaa.controler.analiz.charters.Y;
import kaa.controler.analiz.str.RefactoringStrings;
import kaa.controler.analiz.str.SplitExpressionAtArrayList;
import kaa.model.SetGrammar;

import java.util.ArrayList;
import java.util.Arrays;

public class AnalizInputStringToCorectExsspresion {
    private ArrayList<Bukva> listRulse ;
    private ArrayList<Integer> ListUsedRules;
    private String[] inPutLexemList;
    private String inPutStringExpression;
    private String start= "I";
    private AnalizRez returnObj;
    
    public AnalizInputStringToCorectExsspresion(String aInPutStringExpression){
        inPutStringExpression = aInPutStringExpression;
        returnObj= new AnalizRez();

    }
    
    public AnalizRez runAnalizExpression(){
        init();

        //Выделяет каждый элемент выражения в отдельную строку
        inPutLexemList = SplitExpressionAtArrayList.ParsStringPart(inPutStringExpression);
        // Проверка на коректность выражения + добавления информации о ошибках
        checkStr(inPutLexemList,new SetGrammar(new ArrayList<String>(Arrays.asList("+", "-", "/", "*", "0", "1", "2",
                                                                                   "3", "4", "5", "6","7", "8", "9",
                                                                                   "(", ")", "min", "max", "^", "sqrt"
                                                                                                                   ))));
        // Добавление завершающих символов до для распознавания LL(1)-грамматики
        inPutLexemList= SplitExpressionAtArrayList.addToStrinArray$(getInPutLexemList());

        ListUsedRules = new ArrayList<Integer>();
        AnalitExpression();
        returnObj.rules=ListUsedRules;
        returnObj.start=start;
        returnObj.lexem=inPutLexemList;

        return returnObj;
    }
    
    public void AnalitExpression(){
        boolean zamenaSimbola=false;
      /*exit:*/for (int i = 0; i < getInPutLexemList().length; i++) {
            for (int j = 0; j < listRulse.size(); j++) {                
                //Правила со звездочкой заменяют модифицируют строку start 
                if(getInPutLexemList()[i].charAt(0)== listRulse.get(j).complect.charAt(0)
                                && RefactoringStrings.getLastCharacter(start) == listRulse.get(j).str.charAt(0) 
                                                   && listRulse.get(j).stur == true){    
                       //Для случая со знаком 
                       if((getInPutLexemList()[i].charAt(0) == '*' 
                               || getInPutLexemList()[i].charAt(0) == '/'
                                    || getInPutLexemList()[i].charAt(0) == '-'
                                        || getInPutLexemList()[i].charAt(0) == '+')
                                            &&RefactoringStrings.getLastCharacter(start)=='A'){
                                            String temp= new String(remuvArgument(getInPutLexemList(),i,start));
                                            if(start.equals(temp)== false){
                                                start=temp;
                                                ListUsedRules.add(j);
                                                j=0;
                                            }else{
                                                    System.err.println("Error. Don't true expression!(2)");
                                                    returnObj.error=true;
                                                    zamenaSimbola=false;
                                                    break;
                                                   // break exit;
                                            }
                       //Правила модифицируют строку start и переходять на 
                       // следующий элемент массива лексем
                       }else{
                            start=RefactoringStrings.RefactoringStr(start,
                                                            listRulse.get(j).zamena);
                           ListUsedRules.add(j);
                           j=0;
                       }             
                }
                
                if(getInPutLexemList()[i].charAt(0)== listRulse.get(j).complect.charAt(0)
                                 && RefactoringStrings.getLastCharacter(start) == listRulse.get(j).str.charAt(0) 
                                                  && listRulse.get(j).stur == false){       
                    start=RefactoringStrings.RefactoringStr(start,
                                                            listRulse.get(j).zamena);
                    ///отладка ошибок
                    ListUsedRules.add(j);
                    zamenaSimbola=true;
                    break;
                }
            }     
            if(zamenaSimbola==false){
                returnObj.error=true;
                System.err.println("Error. Don't true expression!(1)");
                break;
            }
        }
    }
    
    private void init(){
        listRulse = new ArrayList<Bukva>();
        listRulse.add(new I("(","AS"));      //0
        listRulse.add(new I("min","AM"));    //1
        listRulse.add(new I("max","AM"));    //2
        listRulse.add(new I("^","AK"));      //3
        listRulse.add(new I("sqrt","AK"));   //4
            
        listRulse.add(new I("0","AN"));      //5
        listRulse.add(new I("1","AN"));      //6
        listRulse.add(new I("2","AN"));      //7
        listRulse.add(new I("3","AN"));      //8
        listRulse.add(new I("4","AN"));      //9
        listRulse.add(new I("5","AN"));      //10
        listRulse.add(new I("6","AN"));      //11
        listRulse.add(new I("7","AN"));      //12
        listRulse.add(new I("8","AN"));      //13
        listRulse.add(new I("9","AN"));      //14
        
        listRulse.add(new A("+","ANY",true));//15
        listRulse.add(new A("+","AMY",true));//16
        listRulse.add(new A("+","AKY",true));//17
        listRulse.add(new A("+","ASY",true));//18
        listRulse.add(new A("+","AY",true)); //19
        listRulse.add(new A("-","ANY",true));//20
        listRulse.add(new A("-","AMY",true));//21
        listRulse.add(new A("-","AKY",true));//22
        listRulse.add(new A("-","ASY",true));//23
        listRulse.add(new A("-","AY",true));//24
        
        listRulse.add(new A("*","ANY",true));//25
        listRulse.add(new A("*","AMY",true));//26
        listRulse.add(new A("*","AKY",true));//27
        listRulse.add(new A("*","ASY",true));//28
        listRulse.add(new A("*","AY",true)); //29
        
        listRulse.add(new A("/","ANY",true));//30
        listRulse.add(new A("/","AMY",true));//31
        listRulse.add(new A("/","AKY",true));//32
        listRulse.add(new A("/","ASY",true));//33
        listRulse.add(new A("/","AY",true)); //34
       
        listRulse.add(new A("0","AN",true));//35
        listRulse.add(new A("1","AN",true));//36
        listRulse.add(new A("2","AN",true));//37
        listRulse.add(new A("3","AN",true));//38
        listRulse.add(new A("4","AN",true));//39
        listRulse.add(new A("5","AN",true));//40
        listRulse.add(new A("6","AN",true));//41
        listRulse.add(new A("7","AN",true));//42
        listRulse.add(new A("8","AN",true));//43
        listRulse.add(new A("9","AN",true));//44
   
        listRulse.add(new A("(","AS",true));//45
        listRulse.add(new A(")","AS",true));//46
        listRulse.add(new A("min","AM",true));//47
        listRulse.add(new A("max","AM",true));//48
        listRulse.add(new A("^","AK",true));  //49
        listRulse.add(new A("sqrt","AK",true));//50
        
        listRulse.add(new S(")",""));        //51
        listRulse.add(new S("(",""));        //52
        
        listRulse.add(new N("0",""));        //53
        listRulse.add(new N("1",""));        //54
        listRulse.add(new N("2",""));        //55
        listRulse.add(new N("3",""));        //56
        listRulse.add(new N("4",""));        //57
        listRulse.add(new N("5",""));        //58
        listRulse.add(new N("6",""));        //59
        listRulse.add(new N("7",""));        //60
        listRulse.add(new N("8",""));        //61
        listRulse.add(new N("9",""));        //62
        
        listRulse.add(new K("sqrt","ASAS")); //63
        listRulse.add(new K("^","ANSNS"));   //64
        
        listRulse.add(new M("min","ASN,NS"));//65
        listRulse.add(new M("max","ASN,NS"));//66
        
        listRulse.add(new Y("+",""));        //67
        listRulse.add(new Y("-",""));        //68
        listRulse.add(new Y("/",""));        //69
        listRulse.add(new Y("*",""));        //70
        listRulse.add(new Bukva(",",",",""));//71
        listRulse.add(new A("$",""));        //72

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
         if(ListUsedRules != null){
                int iteration=1;
                System.out.println("Input string:: "+inPutStringExpression);
                 System.out.println("----------------------\n"
                                   +"Start: \n"
                                   +"----------------------");
                   for (Integer integer : ListUsedRules) {
                       System.out.println(iteration+"-> ["+integer+"] "
                                         +listRulse.get(integer).str+", "
                                         +listRulse.get(integer).complect+" :: "
                                         +listRulse.get(integer).zamena+" *:"
                                         +listRulse.get(integer).stur);
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
     * @return the inPutLexemList
     */
    public String[] getInPutLexemList() {
        return inPutLexemList;
    }


    // Start (Проверка на коректность грамматики и скобок)
    public static void checkStr(String[] inputExpression,SetGrammar grammarElements){
        boolean counterErrors=false;
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

        for (int i=0;i<modifiedInpExpr.length-1;i++){
            counterErrors=false;

            //  Checking the expression elements at the include elements of grammar.
            for (int j=0;j<grammarElements.getSet().size();j++){
                // Do are brackets enter correct?
                if(modifiedInpExpr[i].equals(")") && modifiedInpExpr[i+1].equals("(") ||modifiedInpExpr[i].equals("(") && modifiedInpExpr[i+1].equals(")") ){
                    break;
                }
                if(grammarElements.getSet().get(j).equals(modifiedInpExpr[i])== true){
                    counterErrors=true;
                }
            }

            if(counterErrors == false){
                System.err.println("\nWe have a problem! Expression is not correct!");
                System.err.println("\nWith this symbol: {"+modifiedInpExpr[i]+"}");
                i=modifiedInpExpr.length;
                break;
            }
        }

        counterErrors = false;
        //  Checking the expression elements at the include elements of grammar for last element expression.
        for (int j=0;j<grammarElements.getSet().size();j++){
            if(grammarElements.getSet().get(j).equals(inputExpression[inputExpression.length-1])== true){
                counterErrors=true;
            }
        }


        if(counterErrors == false){
            System.err.println("\nWe have a problem! Expression is not correct!\nLast element expression!");
        }

        if(counterBreaks != 0){
            System.err.println("\nBrackets are placed incorrectly!\n");
        }

    }

    public static String[] checkStrHelp(String[] inputExpression){
        for (int j=0;j<inputExpression.length;j++){
            if(inputExpression[j].charAt(0)>47 || inputExpression[j].charAt(0)<58){
                inputExpression[j]=inputExpression[j].charAt(0)+"";
            }
        }
        return inputExpression;
    }
    // end (Проверка на коректность грамматики и скобок)



}




