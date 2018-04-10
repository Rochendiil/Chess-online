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
public class Loper extends SchaakStuk {

    private boolean finished;

    public Loper(Point positie, boolean kleur, Spel spel, int id, Image image) {
        super(positie, kleur, spel, id, image);
    }

    @Override
    public ArrayList<Vak> getBeschikbareZetten() {
        ArrayList<Vak> vakList = new ArrayList<Vak>();
        int x = positie.x;
        int y = positie.y;
        finished = false;
        while (y < 7 && x < 7 && !finished) {
            y++;
            x++;
            loopmethod(x, y, vakList);

        }
        x = positie.x;
        y = positie.y;
        finished = false;
        while (y < 7 && x > 0 && !finished) {
            y++;
            x--;
            loopmethod(x, y, vakList);

        }

        x = positie.x;
        y = positie.y;
        finished = false;
        while (x < 7 && y > 0 && !finished) {
            y--;
            x++;
            loopmethod(x, y, vakList);

        }
        x = positie.x;
        y = positie.y;
        finished = false;
        while (x > 0 && y > 0 && !finished) {
            x--;
            y--;
            loopmethod(x, y, vakList);

        }
        return vakList;

    }

    private void loopmethod(int posx, int posy, ArrayList<Vak> vakList) {
        if (getBeschikbaarvak(posx, posy) != null) {
            vakList.add(getBeschikbaarvak(posx, posy));
            if (getBeschikbaarvak(posx, posy).getStuk() != null) {
                finished = true;
            }
        } else {
            finished = true;
        }
    }
}
