package calculator.test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 22.08.13
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
public class Kaso {
    public static void main(String[] arg){
        Integer[] i1= {2,3,4,6,7,9,9};
        Integer[] i2= {22,23,24,26,27,29,29};
        Integer[] i3= {322,323,324,326,327,329,329};

        ArrayList<Integer[]> ka = new ArrayList<Integer[]>();

        ka.add(i1);
        ka.add(i2);
        ka.add(i3);

        for(int i=0;i<ka.size();i++){
            for(int j=0;j<ka.get(i).length;j++){
                System.out.print(ka.get(i)[j]+" ");
            }
            System.out.println("");
        }

    }
}
