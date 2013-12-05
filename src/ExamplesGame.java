import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javalib.colors.Black;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;
import tester.*;

public class ExamplesGame {
    
    Player player = new Player();
    
    // Example Powerups
    Powerup avocado = new Powerup(.17f, .50f, 600, new Posn(0,0),
            new FromFileImage(new Posn(0,0),
            "avocado.png"));
    Powerup twinkie = new Powerup(-0.1f, -0.35f, -100, new Posn(0,0),
            new FromFileImage(new Posn(0,0),
            "twinkie.png"));
   
    // Example Fields
    Field field = new Field();
    
    // example CollectTheFruits
    CollectTheFruit ctf = new CollectTheFruit();

    // reset method for a new player
    void resetPlayer() {
        this.player = new Player();
    }

    // reset method for a new field
    void resetField() {
        this.field = new Field();
    }

    void resetCTF() {
        this.ctf = new CollectTheFruit();
    }

    /********************TEST THE PLAYER CLASS!!!*******************/
    //Test the IsDead() method
    void testIsDead(Tester t) {
        resetPlayer();
        t.checkExpect(player.isDead(), false);
        resetPlayer();
        player.speed = 0;
        t.checkExpect(player.isDead(), true);
        resetPlayer();
        player.hunger = 0;
        t.checkExpect(player.isDead(), true);
    }
    
    //Test the hasWon() method
    void testHasWon(Tester t) {
        resetPlayer();
        t.checkExpect(player.hasWon(), false);
        resetPlayer();
        player.score = 100000;
        t.checkExpect(player.hasWon(), true);
    }
    
    //test the changeHunger(int) and changeHunger(float) methods
    void testChangeHunger(Tester t) {
        resetPlayer();
        player.changeHunger(0.1f);
        t.checkExpect(player.hunger, 730f);
        resetPlayer();
        player.changeHunger(-0.1f);
        t.checkExpect(player.hunger, 630f);
        resetPlayer();
        player.changeHunger(1);
        t.checkExpect(player.hunger, 701f);
        resetPlayer();
        player.changeHunger(-1);
        t.checkExpect(player.hunger, 699f);
    }
    
  //test the changeSpeed method
    void testChangeSpeed(Tester t) {
        resetPlayer();
        player.changeSpeed(0.1f);
        t.checkExpect(player.speed, 6.4f);
        resetPlayer();
        player.changeSpeed(-0.1f);
        t.checkExpect(player.speed, 5.4f);
    }
    
    //test the changeScore method
    void testChangeScore(Tester t) {
        player.changeScore(100);
        t.checkExpect(player.score, 100);
        player.changeScore(-100);
        t.checkExpect(player.score, 0);
    }
    
    //test the updateStats method
    void testUpdateStats(Tester t) {
        player.updateStats(avocado);
        t.checkExpect(player, new Player(751f, 7.7f, 600, 
                new Posn(0, CollectTheFruit.height - 30)));
        resetPlayer();
        player.updateStats(twinkie);
        t.checkExpect(player, new Player(630f, 3.9f, 0, 
                new Posn(0, CollectTheFruit.height - 30)));
    }
    
    //test the move method
    void testMove(Tester t) {
        player.move(500, 30);
        t.checkExpect(player.loc, new Posn(500, 350));
    }
    
    /********************TEST THE FIELD CLASS!!!*******************/
    
    //test the getYPos method
    void testGetYPos(Tester t) {
        t.checkOneOf(field.getYPos(), 
                CollectTheFruit.height - 35, 
                CollectTheFruit.height - 200);
        
    }
    
    //test the addPowerup method
    @SuppressWarnings("unchecked")
    void testAddPowerup(Tester t) {
        resetField();
        field.addPowerup(0, 0);
        t.checkOneOf(field.pUps, 
                new ArrayList<Powerup>(Arrays.asList(avocado)), 
                new ArrayList<Powerup>(Arrays.asList(twinkie)));
    }
    
    //test the generatePowerups method
    /*void testGeneratePowerups(Tester t) {
        field.generatePowerups(0);
        t.checkExpect(field.pUps, new ArrayList<Powerup>());
        resetField();
        field.generatePowerups(1);
        t.checkOneOf(field, 
                new Field(new ArrayList<Powerup>(Arrays.asList(avocado))), 
                new Field(new ArrayList<Powerup>(Arrays.asList(twinkie))));
        resetField();
        field.generatePowerups(2);
        t.checkOneOf(field.pUps, 
                new ArrayList<Powerup>(Arrays.asList(avocado, avocado)), 
                new ArrayList<Powerup>(Arrays.asList(avocado, twinkie)),
                new ArrayList<Powerup>(Arrays.asList(twinkie, avocado)), 
                new ArrayList<Powerup>(Arrays.asList(twinkie, twinkie)));
    }*/
    
    /********************TEST THE COLLCTHEFRUIT CLASS!!!*******************/
    
    //test the hitPowerup method
    void testHitPowerup(Tester t) {
        t.checkExpect(ctf.hitPowerup(), -1);
        ctf.field.addPowerup(0, CollectTheFruit.height - 30);
        t.checkExpect(ctf.hitPowerup(), 0);
    }
    
    //test the removePowerups method
    void removePowerups(Tester t) {
        ctf.removePups(0);
        t.checkExpect(ctf.field.pUps, new ArrayList<Powerup>());
    }
    
    
    //test the onTick method
    void testOnTick(Tester t) {
        resetCTF();
        ctf.onTick();
        t.checkExpect(ctf.player.loc, 
                new Posn(6, CollectTheFruit.height - 30));
    }
    
    //test the onKeyEvent method
    void testOnKeyEvent(Tester t) {
        ctf.onKeyEvent(" ");
        t.checkExpect(ctf.isJumping, true);
    }
    
    //test the worldEnd method
    void testWorldEnd(Tester t) {
        t.checkExpect(ctf.worldEnds().worldEnds, false);
        resetCTF();
        ctf.player.score = 300000;
        t.checkExpect(ctf.worldEnds().worldEnds, true);
        resetCTF();
        ctf.player.hunger = 0;
        t.checkExpect(ctf.worldEnds().worldEnds, true);
        resetCTF();
        ctf.player.speed = 0;
        t.checkExpect(ctf.worldEnds().worldEnds, true);
    }
}