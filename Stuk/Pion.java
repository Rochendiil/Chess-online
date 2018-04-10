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
public class Pion extends SchaakStuk {

    private boolean eerstezet;

    public Pion(Point positie, boolean kleur, Spel spel, int id, Image image) {
        super(positie, kleur, spel, id, image);
        eerstezet = true;
    }

    @Override
    public ArrayList<Vak> getBeschikbareZetten() {
        ArrayList<Vak> vakList = new ArrayList<Vak>();
        int y = positie.y;
        int x = positie.x;

        if (kleur) {
            y++;
        } else {
            y--;
        }

        if (getBeschikbaarvak(x, y) != null && y < 8 && y >= 0 && x < 8 && x >= 0) {
            if (spel.getBord().vakken[x][y].getStuk() == null) {
                vakList.add(getBeschikbaarvak(positie.x, y));
            }
            
        }
        if (getBeschikbaarvak(x++, y) != null && y < 8 && y >= 0 && x < 8 && x >= 0) {
            if (spel.getBord().vakken[x][y].getStuk() != null && spel.getBord().vakken[x][y].getStuk().getKleur() != kleur) {
                vakList.add(spel.getBord().vakken[x][y]);
            }
        }
        x -= 2;
        if (getBeschikbaarvak(x, y) != null && y < 8 && y >= 0 && x < 8 && x >= 0) {
            if (spel.getBord().vakken[x][y].getStuk() != null && spel.getBord().vakken[x][y].getStuk().getKleur() != kleur) {
                vakList.add(spel.getBord().vakken[x][y]);
            }
        }
        if (eerstezet) {
            if (kleur) {
                y++;
            } else {
                y--;
            }
            if (getBeschikbaarvak(positie.x, y) != null) {
                vakList.add(getBeschikbaarvak(positie.x, y));
            }
            eerstezet = false;
        }
        return vakList;
    }

}
