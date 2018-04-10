/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schaakappxml;

import Spel.Spel;
import Spel.Speler;

/**
 *
 * @author Luuk
 */
public class SchaakApp {
    private Speler loggedin;
    private Spel spel;

    public void setSpel(Spel spel) {
        this.spel = spel;
    }

    public Spel getSpel() {
        return spel;
    }
    
    public Speler getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(Speler loggedin) {
        this.loggedin = loggedin;
    }
}
