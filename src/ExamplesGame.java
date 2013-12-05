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

    // Game Runner Class
    GameRunner gameRunner = new GameRunner();
    // Example WorldEnd
    //WorldEnd loose = new WorldEnd(true, new TextImage(new Posn(800 / 2, 250 / 2),
    //        "YOU LOSE", new Black()));
    //WorldEnd win = new WorldEnd(true, new TextImage(new Posn(800 / 2, 250 / 2),
    //        "YOU LOSE", new Black()));
    
    // example starting position for a new player
    Posn defaultPos = new Posn(0, 320);

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
        t.checkExpect(player, new Player(751f, 7.7f, 600, new Posn(0, CollectTheFruit.height - 30)));
        resetPlayer();
        player.updateStats(twinkie);
        t.checkExpect(player, new Player(630f, 3.9f, 0, new Posn(0, CollectTheFruit.height - 30)));
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
    void testGeneratePowerups(Tester t) {
        field.generatePowerups(0);
        t.checkExpect(field.pUps, new ArrayList<Powerup>());
    }
    
    
    //test the 
    // test for method onTick
    // test for method removePups
    /*void testRemovePups(Tester t) {
        this.ctf7.removePups(0);
        t.checkExpect(this.pups4, this.pups4);
        this.ctf7.removePups(350);
        t.checkExpect(this.pups4, this.pups4a);
        this.ctf8.removePups(800);
        t.checkExpect(this.pups5, this.pups4b);
    }

    // test for the method hitPowerup
    void testHitPowerup(Tester t) {
        t.checkExpect(this.ctf1.hitPowerup(), -1);
        t.checkExpect(this.ctf3.hitPowerup(), 3);
        t.checkExpect(this.ctf4.hitPowerup(), 4);
        t.checkExpect(this.ctf5.hitPowerup(), 5);
    }

    // test for the method makeImage
    void testMakeImage(Tester t) {
        WorldImage worldImage = new FromFileImage(new Posn(
                CollectTheFruit.width / 2, CollectTheFruit.height / 2),
                "sky.png");
        worldImage = worldImage.overlayImages(this.player2.playerImage);
        worldImage = worldImage.overlayImages(new TextImage(new Posn(
                CollectTheFruit.width - 100, 50), "Hunger: "
                + (int) this.player2.hunger, Color.black));
        worldImage = worldImage.overlayImages(new TextImage(new Posn(
                CollectTheFruit.width - 100, 70), "Speed: "
                + (int) this.player2.speed, Color.black));
        worldImage = worldImage.overlayImages(new TextImage(new Posn(
                CollectTheFruit.width - 100, 90), "Score: "
                + (int) this.player2.score, Color.black));
        t.checkExpect(this.ctf6.makeImage(), worldImage);
        // WorldImage worldImage2 = new FromFileImage(new Posn(
        // CollectTheFruit.width / 2,
        // CollectTheFruit.height / 2), "sky.png");
        // worldImage2 = worldImage2.overlayImages(this.player4.playerImage);
        // worldImage2 = worldImage2.overlayImages(
        // new TextImage(new Posn(CollectTheFruit.width - 100, 50),
        // "Hunger: " + (int)this.player4.hunger, Color.black));
        // worldImage2 = worldImage2.overlayImages(
        // new TextImage(new Posn(CollectTheFruit.width - 100, 70),
        // "Speed: " + (int)this.player4.speed, Color.black));
        // worldImage2 = worldImage2.overlayImages(
        // new TextImage(new Posn(CollectTheFruit.width - 100, 90),
        // "Score: " + (int)this.player4.score, Color.black));
        // t.checkExpect(this.ctf3.makeImage(), worldImage2);
    }

    // test for method worldEnds
    void testWorldEnds(Tester t) {
        t.checkExpect(this.ctf1.worldEnds(),
                new WorldEnd(false, ctf1.makeImage()));
        this.player5.changeHunger(-700);
        WorldImage endImage = new FromFileImage(new Posn(
                CollectTheFruit.width / 2, CollectTheFruit.height / 2),
                "sky.png");
        t.checkExpect(
                this.ctf4.worldEnds(),
                new WorldEnd(true, endImage.overlayImages(new TextImage(
                        new Posn(CollectTheFruit.width / 2,
                                CollectTheFruit.height / 2), "YOU LOSE",
                        new Black()))));
        this.player4.changeScore(100001);
        t.checkExpect(
                this.ctf3.worldEnds(),
                new WorldEnd(true, endImage.overlayImages(new TextImage(
                        new Posn(CollectTheFruit.width / 2,
                                CollectTheFruit.height / 2), "YOU WIN!!!",
                        new Black()))));

    }

    // test the generatePowerups method
    void testGeneratePowerups(Tester t) {
    }

    // test the addPowerup method
    void testAddPowerup(Tester t) {
        this.field3.addPowerup(100, 325);
        t.checkOneOf(this.field3.pUps,
                new ArrayList<Powerup>(Arrays.asList(pup1a)),
                new ArrayList<Powerup>(Arrays.asList(pup2a)),
                new ArrayList<Powerup>(Arrays.asList(pup3a)),
                new ArrayList<Powerup>(Arrays.asList(pup4a)),
                new ArrayList<Powerup>(Arrays.asList(pup5a)),
                new ArrayList<Powerup>(Arrays.asList(pup6a)),
                new ArrayList<Powerup>(Arrays.asList(pup7a)),
                new ArrayList<Powerup>(Arrays.asList(pup8a)),
                new ArrayList<Powerup>(Arrays.asList(pup9a)),
                new ArrayList<Powerup>(Arrays.asList(pup10a)));
    }

    // test the getYPos method
    void testGetYPos(Tester t) {
        this.resetPlayer();
        this.resetField();
        t.checkOneOf(this.field.getYPos(), 315, 150);
        t.checkOneOf(this.field.getYPos(), 315, 150);
        t.checkOneOf(this.field.getYPos(), 315, 150);
    }

    // Test the isDead method
    void testIsDead(Tester t) {
        this.resetPlayer();
        t.checkExpect(this.player.isDead(), false);
        t.checkExpect(this.deadPlayer.isDead(), true);
    }

    // Change the hunger level of a player
    void testChangeHunger(Tester t) {
        this.resetPlayer();
        this.player.changeHunger(0.1f);
        t.checkExpect(this.player, new Player(730, 6, 0, defaultPos));
        this.resetPlayer();
        this.player.changeHunger(-0.1f);
        t.checkExpect(this.player, new Player(630, 6, 0, defaultPos));
        this.resetPlayer();
        this.player.changeHunger(10);
        t.checkExpect(this.player, new Player(710, 6, 0, defaultPos));
    }

    // Test the changeSpeed method
    void testChangeSpeed(Tester t) {
        this.resetPlayer();
        this.player.changeSpeed(0.3f);
        t.checkExpect(this.player, new Player(700, 7.2f, 0, defaultPos));
        this.resetPlayer();
        this.player.changeSpeed(-0.2f);
        t.checkExpect(this.player, new Player(700, 4.8f, 0, defaultPos));
    }

    // Test the updateStatus method
    void testUpdateStats(Tester t) {
        this.resetPlayer();
        player.updateStats(pup1);
        t.checkExpect(player, new Player(751, 8.0f, 600, defaultPos));
        this.resetPlayer();
        player.updateStats(pup2);
        t.checkExpect(player, new Player(865, 6.4f, 550, defaultPos));
        player.updateStats(pup7);
        t.checkExpect(player, new Player(735.25f, 5.76f, 560, defaultPos));
    }

    // Test the move method
    void testMove(Tester t) {
        this.resetPlayer();
        this.player.move(100, 0);
        t.checkExpect(this.player, new Player(700, 6, 0, new Posn(100, 320)));
        this.resetPlayer();
        this.player.move(50, 10);
        t.checkExpect(this.player, new Player(700, 6, 0, new Posn(50, 330)));
        this.player.move(-10, -100);
        t.checkExpect(this.player, new Player(700, 6, 0, new Posn(40, 230)));
        this.player.move(20, -230);
        t.checkExpect(this.player, new Player(700, 6, 0, new Posn(60, 0)));

    }
    // //Test the offScreen method
    // void testOffScreen(Tester t) {
    // t.checkExpect(new Default(new Posn(-50, 0)).isOffscreen(), true);
    // t.checkExpect(new Default(new Posn(1200, 0)).isOffScreen(), true);
    // t.checkExpect(new Default(new Posn(0, -20)).isOffScreen(), true);
    // t.checkExpect(new Default(new Posn(0, 500)).isOffScreen(), true);
    // t.checkExpect(new Default(new Posn(50, 50)).isOffScreen(), false);
    // }
    // //
    // //Test the nearX method
    // void testNearX(Tester t) {
    // //t.checkExpect()
    // }*/
}