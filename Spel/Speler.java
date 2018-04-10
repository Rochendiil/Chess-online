package Spel;

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
public class Speler implements Serializable {
    private String naam;

    public String getNaam() {
        return naam;
    }

    public Speler(String naam) {
        this.naam = naam;
    }

}
