package calculator.test;

import kaa.controler.analiz.str.SplitExpressionAtArrayList;
import kaa.controler.analiz.charters.Bukva;
import kaa.controler.analiz.charters.I;
import kaa.controler.analiz.charters.A;
import kaa.controler.analiz.charters.K;
import kaa.controler.analiz.charters.M;
import kaa.controler.analiz.charters.N;
import kaa.controler.analiz.charters.S;
import kaa.controler.analiz.charters.Y;
import kaa.controler.analiz.str.RefactoringStrings;
import java.util.ArrayList;

public class MainTest {
    ArrayList<Bukva> listRulse ;
            
    public static void main(String[] args) {
       MainTest obj = new MainTest();
       obj.init();
       
       String[] inPutLexemList = SplitExpressionAtArrayList.ParsStringPart("(min(33,75)-((min(25,98)+(((((sqrt(23652+68+68+98)+89)+(96+63/^(5*8+625-54/55)5))*sqrt(95)))))))");
       //Add for add$ to String arrays simbol "$"
       inPutLexemList=SplitExpressionAtArrayList.addToStrinArray$(inPutLexemList); 
       for (String string : inPutLexemList) {
            System.out.print(string);
        }
       
       
       String start= "I";
       ArrayList<Bukva> list = obj.getList();
       //тестовый
       ArrayList<Integer> ListUsedRules = new ArrayList<Integer>();
       
        for (int i = 0; i < inPutLexemList.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                //Правила со звездочкой заменяют модифицируют строку start 
                if(inPutLexemList[i].charAt(0)== list.get(j).complect.charAt(0)
                                && RefactoringStrings.getLastCharacter(start) == list.get(j).str.charAt(0) 
                                                   && list.get(j).stur == true){
    
                    //Для случая со знаком 
                       if((inPutLexemList[i].charAt(0) == '*' 
                               || inPutLexemList[i].charAt(0) == '/'
                                    || inPutLexemList[i].charAt(0) == '-'
                                        || inPutLexemList[i].charAt(0) == '+')
                                            &&RefactoringStrings.getLastCharacter(start)=='A'){
                           start=remuvArgument(inPutLexemList,i,start);
                           ListUsedRules.add(j);
                           j=0;
                       //Правила модифицируют строку start и переходять на 
                       // следующий элемент массива лексем
                       }else{
                            start=RefactoringStrings.RefactoringStr(start,
                                                            list.get(j).zamena);
                           ListUsedRules.add(j);
                           j=0;
                       }                  
                }
                
                if(inPutLexemList[i].charAt(0)== list.get(j).complect.charAt(0)
                                 && RefactoringStrings.getLastCharacter(start) == list.get(j).str.charAt(0) 
                                                  && list.get(j).stur == false){       
                    start=RefactoringStrings.RefactoringStr(start,
                                                            list.get(j).zamena);
                    ListUsedRules.add(j);
                    break;
                }                       
            }            
        }   
             /*
             * Диагностика
             */
            System.out.println("\n-------------------------------\nLenght::"+start.length()+" Value::"+start);
            for (Integer integer : ListUsedRules) {
                System.out.println(integer+". "+list.get(integer).str+", "+list.get(integer).complect+" :: "+list.get(integer).zamena+" *:"+list.get(integer).stur);
            }
    }
       // определяет следующию лексему после бинарного арифметического оператора и возращает соответствующие правила
      public static String remuvArgument(String[] aLexemList,int indexLexemList,String aStart){
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
    public ArrayList<Bukva> getList(){
        return listRulse;
    }
    public void init(){
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
        // true =>A
        listRulse.add(new A("(","AS",true));      //45
        listRulse.add(new A(")","AS",true));      //46
        listRulse.add(new A("min","AM",true));    //47
        listRulse.add(new A("max","AM",true));    //48
        listRulse.add(new A("^","AK",true));      //49
        listRulse.add(new A("sqrt","AK",true));   //50
        
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
        listRulse.add(new A("$",""));   //72
        /*
        for (Bukva ls : list) {
            System.out.print(ls.str+" ");
        }
        System.out.println("");
        for (Bukva ls : list) {
            System.out.print(ls.complect+" ");
        }
        System.out.println("");
        for (Bukva ls : list) {
            System.out.print(ls.zamena+" ");
    
    }*/
   }
}
