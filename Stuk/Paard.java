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
public class Paard extends SchaakStuk {

    public Paard(Point positie, boolean kleur, Spel spel, int id, Image image) {
        super(positie, kleur, spel, id, image);
    }

    
    
    @Override
    public ArrayList<Vak> getBeschikbareZetten() {
        ArrayList<Vak> vakList = new ArrayList<Vak>();

        if(getBeschikbaarvak(positie.x + 2,positie.y + 1) != null){
            vakList.add(getBeschikbaarvak(positie.x + 2,positie.y + 1));
        }
        if(getBeschikbaarvak(positie.x + 2,positie.y -1) != null){
            vakList.add(getBeschikbaarvak(positie.x + 2,positie.y -1));
        }
        if(getBeschikbaarvak(positie.x + 1,positie.y +2) != null){
            vakList.add(getBeschikbaarvak(positie.x + 1,positie.y + 2));
        }
        if(getBeschikbaarvak(positie.x + 1,positie.y -2) != null){
            vakList.add(getBeschikbaarvak(positie.x + 1,positie.y -2));
        }
        
        if(getBeschikbaarvak(positie.x - 2,positie.y + 1) != null){
            vakList.add(getBeschikbaarvak(positie.x - 2,positie.y + 1));
        }
        if(getBeschikbaarvak(positie.x - 2,positie.y -1) != null){
            vakList.add(getBeschikbaarvak(positie.x - 2,positie.y -1));
        }
        if(getBeschikbaarvak(positie.x - 1,positie.y +2) != null){
            vakList.add(getBeschikbaarvak(positie.x - 1,positie.y + 2));
        }
        if(getBeschikbaarvak(positie.x - 1,positie.y -2) != null){
            vakList.add(getBeschikbaarvak(positie.x - 1,positie.y -2));
        }
        return vakList;
    }
    
    
}
