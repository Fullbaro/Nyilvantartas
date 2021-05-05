package nyilvantartas;

import Fullbaro.flbr;
import java.awt.Color;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

public class Nyilvantartas {

    // VÁLTOZÓK
    
    public static final Color hatterSzin= new Color(98,157,213);
    public static final Color kekSzin = new Color(11, 10, 216);
    public static final Color zoldSzin = new Color(20, 139, 23);
    public static final Color vilagosHatterSzin = new Color(185,214,212);
    public static final Color pirosSzin = new Color(226, 0, 7);
    public static final Color sargaSzin = new Color(247,197,1);
    
    // VŰLTOZÓK VÉGE
    
    Nyilvantartas(){
        try{                        
            flbr.kapcsolodik("assets/adatbazis.db");
            new Foablak().setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
            flbr.hiba("Végzetes hiba lépett fel a program futása során!\nKérlek indítsd újra a programot", "Hiba");
        }
    }// konstruktor vége.
    
    // METÓDUSOK
    
    void exit(){
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                flbr.kapcsolatBont();
                flbr.uzen("Ez a leállítás", "Szerbusz");
            }
        }, "Shutdown-thread")); 
    }       
    
    // METÓDUSOK VÉGE
    
    
    public static void main(String[] args) {
        new Nyilvantartas();
    }// main vége.
    
}// Nyilvantartas osztály vége.
