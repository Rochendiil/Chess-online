/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stuk;

import Spel.Spel;
import Spel.Vak;
import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author luukj
 */
public class Koning extends SchaakStuk{

    public Koning(Point positie, boolean kleur, Spel spel, int id, Image image) {
        super(positie, kleur, spel, id, image);
    }

    

    
    @Override
    public ArrayList<Vak> getBeschikbareZetten() {
   return null;
    }
    
}
