package Spel;


import Stuk.SchaakStuk;
import java.awt.Point;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luukj
 */
public class Vak implements Serializable {
    private String naam;
    private SchaakStuk stuk;
    private boolean kleur;
    private Point positie;


    public Vak(String naam, SchaakStuk stuk, Point positie) {
        this.naam = naam;
        this.stuk = stuk;
        this.positie = positie;
    }

    public Point getPositie() {
        return positie;
    }
       

    public String getNaam() {
        return naam;
    }

    public SchaakStuk getStuk() {
        return stuk;
    }

    public void setStuk(SchaakStuk stuk) {
        this.stuk = stuk;
        
        this.stuk.setPositie(positie);
    }
    public void removeStuk(){
        this.stuk = null;
    }
    
}
