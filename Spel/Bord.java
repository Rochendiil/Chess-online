package Spel;


import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luukj
 */
public class Bord implements Serializable{
    public Vak[][]vakken;
    public static String vakstring = "abcdefgh";
    public Bord() {
        vakken =  new Vak[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Vak vak = new Vak(String.valueOf(vakstring.charAt(x)) + (y + 1) , null,new Point(x,y));
                vakken[x][y] = vak;
            }
        }
        
        
    }

    public Vak[][] getVakken() {
        return vakken;
    }
    
}
