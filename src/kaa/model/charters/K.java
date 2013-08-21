
package kaa.model.charters;
public class K extends Bukva{
    public K(String aComplect,String aZamena){
        complect=aComplect;
        zamena=aZamena;
        str="K";
    }
    public K(String aComplect,String aZamena, boolean aStur){
        complect=aComplect;
        zamena=aZamena;
        str="K";
        shiftFlag =aStur;
        ///shiftFlag=true;
    }
}
