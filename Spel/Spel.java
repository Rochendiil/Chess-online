package Spel;

import Stuk.Koning;
import Stuk.Koningin;
import Stuk.Loper;
import Stuk.Paard;
import Stuk.Pion;
import Stuk.SchaakStuk;
import Stuk.Toren;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.image.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luukj
 */
public class Spel implements Serializable{
    private Bord bord;
    private Speler speler1;
    private Speler speler2;
    private int id;

    public int getId() {
        return id;
    }
    private ArrayList<SchaakStuk> stukken;

        public Spel(Speler speler1, Speler speler2, int id) {
        this.bord =  new Bord();
        this.speler1 = speler1;
        this.speler2 = speler2;
        this.id = id;
        initStukken();
    }

    public Spel(String decodestring, Speler speler){
        this.bord =  new Bord();
        String[] split = decodestring.split(",");
        id = Integer.decode(split[0]);
        if(split[1].equals("wit")){
            this.speler1 = speler;
            this.speler2 = new Speler(split[2]);
        }
        else{
            this.speler2 = speler;
            this.speler1 = new Speler(split[2]);
        }
        initStukken();
    }
    
    private void initStukken(){
        stukken = new ArrayList<SchaakStuk>();
        initStukkenKleur("wit");
        initStukkenKleur("zwart");
        for (int i = 0; i < stukken.size(); i++) {
            zetStukOpVak(stukken.get(i));
        }
    }
    private void initStukkenKleur(String kleur){

        
        int y = 0;
        boolean stuk = true;
        int id = 0;
        if(kleur.equals("wit")){
            y = 0;
            id = 0;
            stuk = true;
        }
        if(kleur.equals("zwart")){
            y = 7;
            id = 16;
            stuk = false;
        }
        Toren toren1 = new Toren(new Point(0,y), stuk, this,id++, new Image(kleur +"toren.png"));
        stukken.add(toren1);

        Paard paard1 = new Paard(new Point(1,y), stuk, this,id++, new Image(kleur +"paard.png"));
        stukken.add(paard1);

        Loper loper1 = new Loper(new Point(2, y), stuk, this,id++, new Image(kleur +"loper.png"));
        stukken.add(loper1);

        Koningin koningin1 = new Koningin(new Point(3, y), stuk, this,id++,new Image(kleur +"koningin.png"));
        stukken.add(koningin1);
        
        Koning koning1 = new Koning(new Point(4, y), stuk, this,id++,new Image(kleur +"koning.png"));
        stukken.add(koning1);


        
        Loper loper2 = new Loper(new Point(5, y), stuk, this,id++,new Image(kleur +"loper.png"));
        stukken.add(loper2);
        
        Paard paard2 = new Paard(new Point(6,y), stuk, this,id++,new Image(kleur +"paard.png"));
        stukken.add(paard2);
        
        Toren toren2 = new Toren(new Point(7,y), stuk, this,id++,new Image(kleur +"toren.png"));
        stukken.add(toren2);

        for (int i = 0; i < 8; i++) {
            if(y == 0){
                y = 1;
            }
            else if(y == 7){
                y = 6;
            }
            Pion pion1 = new Pion(new Point(i,y), stuk, this,id++,new Image(kleur +"pion.png"));
            stukken.add(pion1);
        }
        


        System.out.println("done");
    }
    private void zetStukOpVak(SchaakStuk stuk){
        bord.getVakken()[stuk.getPositie().x][stuk.getPositie().y].setStuk(stuk); 
    }
    public ArrayList<SchaakStuk> getStukken() {
        return stukken;
    }
    
    public Bord getBord() {
        return bord;
    }

    public Speler getSpeler1() {
        return speler1;
    }


    public Speler getSpeler2() {
        return speler2;
    }

    
    
    
}
