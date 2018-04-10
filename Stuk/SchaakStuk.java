/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stuk;

import Spel.Spel;
import Spel.Vak;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author luukj
 */
public abstract class SchaakStuk implements Serializable {
    protected Point positie;
    protected boolean kleur;
    protected Spel spel;
    protected int id;
    protected Image image;

    public Image getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public void setPositie(Point positie) {
        this.positie = positie;
    }
    
    public SchaakStuk(Point positie, boolean kleur, Spel spel, int id, Image image) {
        this.positie = positie;
        this.kleur = kleur;
        this.spel = spel;
        this.id = id;
        this.image = image;
    }

    public Point getPositie() {
        return positie;
    }
    

    public boolean getKleur() {
        return kleur;
    }
    protected Vak getBeschikbaarvak(int x, int y){
        if(x >= 0 && y >= 0 && x <= 7 && y <= 7){
            if(spel.getBord().vakken[x][y].getStuk() == null || spel.getBord().vakken[x][y].getStuk().getKleur() != kleur){
                return spel.getBord().vakken[x][y];
            }
        }

        return null;
    }
    public abstract ArrayList<Vak> getBeschikbareZetten();

    @Override
    public String toString() {
        return this.getClass() + " positie=" + positie.toString() + ", kleur=" + kleur + ", spel=" + spel + ", id=" + id + '}';
        
    }
    
    
}
