/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spel;

import java.io.Serializable;

/**
 *
 * @author Luuk
 */
public class ZetEvent implements Serializable{
    String zetString;

    public ZetEvent(String zetString) {
        this.zetString = zetString;
    }

    public String getZetString() {
        return zetString;
    }
    
    
}
