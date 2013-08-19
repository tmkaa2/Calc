/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaa.controler.analiz.charters;

/**
 *
 * @author Mr.Green
 */
public class Bukva {
    public Bukva(String aStr,String aComplect,String aZamena){
        str=aStr;
        complect=aComplect;
        zamena=aZamena;
        stur=false;
    }
    public Bukva(){
        str="KAA";
        complect="";
        zamena="";
        stur=false;
    }
    public String str;
    public String complect;
    public String zamena;
    public boolean stur;
}
