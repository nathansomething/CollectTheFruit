import java.awt.Color;
import javalib.appletworld.World;
import javalib.colors.*;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
//import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;

/*Represent an instance of the entire game. Controls high level 
 * functions (interactions between the player and the field) 
 * as well as world functions (update the game on tick, 
 * draw the game, end the game, etc)
 */
public class CollectTheFruit extends World {
    Player player;
    Field field;
    static final int width = 1000;
    static final int height = 350;
    int totalJumpTicks;
    int currentJumpTicks;
    int pushForce;
    boolean isJumping;
    int numTicks; //Resets at 10000
    static final int powerupsPerScreen = 8;
    
    CollectTheFruit(Player player, Field field) {
        this.player = player;
        this.field = field;
        this.totalJumpTicks = 0;
        this.currentJumpTicks = 0;
        this.pushForce = 0;
        this.isJumping = false;
        this.numTicks = 0;
    }
    
    CollectTheFruit() {
        //Default game settings
        this.player = new Player();
        this.field = new Field();
        this.totalJumpTicks = 0;
        this.currentJumpTicks = 0;
        this.pushForce = 0;
        this.isJumping = false;
        this.numTicks = 0;
    }
    
    //Returns the index of the powerup the player hit.
    //If no powerup is hit, then returns -1
    int hitPowerup() {
        for (int i = 0; i < this.field.pUps.size(); i++) {
            if (Math.abs(
                    this.player.loc.x - this.field.pUps.get(i).loc.x) < 15 &&
               Math.abs(
                       this.player.loc.y - this.field.pUps.get(i).loc.y) < 15)
            {
                return i;
            }
        }
        return -1;
    }
    
    //Set the player into a jumping motion
    void jump() {
        if (this.currentJumpTicks > 0) {
                if (this.currentJumpTicks > this.totalJumpTicks / 2) {
                    this.player.move(0, -this.pushForce);
                }
                else {
                    this.player.move(0, this.pushForce);
                }
                this.currentJumpTicks--;
        }
        else {
            this.totalJumpTicks = 0;
            this.isJumping = false;
        }
    }
    
    //Remove all powerups from the screen
    void removePups(int xBounds) {
        for (int i = 0; i < this.field.pUps.size(); i++) {
            if (this.field.pUps.get(i).loc.x < xBounds) {
                this.field.pUps.remove(i);
            }
        }
    }
    
    //Execute onTick functions. 
    //Move player, check to see if powerup has been it, update stats
    public void onTick() {
        numTicks++;
        //Check to see if player hit a powerup
        int powerupIndex = hitPowerup();
        if (powerupIndex != -1) {
            player.updateStats(this.field.pUps.get(powerupIndex));
            this.field.pUps.remove(powerupIndex);
        }
       
        //Move player at a constant speed. 
        //Make sure he doesn't fall off the screen
        if (numTicks % 2 == 0) {
            player.move((int)this.player.speed, 0);
            if (this.player.loc.x > CollectTheFruit.width) {
                this.player.loc.x %= CollectTheFruit.width;
                this.field.pUps.clear();
                this.field.generatePowerups(CollectTheFruit.powerupsPerScreen);
            }
        }
        
        //If Player has been set to jump, jump!
        if (this.isJumping) {
            this.jump();
        }
        
        //Decrees Hunger Bar
        this.player.changeHunger(-1);
        
        //Reset the tick counter
        if (numTicks > 10000) {
            numTicks = 0;
        }
    }
    
    //Change the game on key event
    public void onKeyEvent(String ke) {
        if (ke.equals(" ")) {
            if(!this.isJumping) {
                this.totalJumpTicks = 20;
                this.pushForce = 20;
                this.currentJumpTicks = this.totalJumpTicks;
                this.isJumping = true;
            }
        }
    }
    
    //Make the background image of the game
    public WorldImage makeImage() {
        WorldImage worldImage = new FromFileImage(
                new Posn(CollectTheFruit.width / 2, 
                        CollectTheFruit.height / 2), 
                        "sky.png");
        worldImage = worldImage.overlayImages(this.player.playerImage);
        for (Powerup p : this.field.pUps) {
            worldImage = worldImage.overlayImages(p.image);
        }
        worldImage = worldImage.overlayImages(
                new TextImage(
                        new Posn(CollectTheFruit.width - 100, 50),
                        "Hunger: " + (int)this.player.hunger, Color.black));
        worldImage = worldImage.overlayImages(
                new TextImage(
                        new Posn(CollectTheFruit.width - 100, 70),
                        "Speed: " + (int)this.player.speed, Color.black));
        worldImage = worldImage.overlayImages(
                new TextImage(
                        new Posn(CollectTheFruit.width - 100, 90),
                        "Score: " + (int)this.player.score, Color.black));
        return worldImage;
    }
    
    //Determine the conditions to end the game
    public WorldEnd worldEnds() {
        WorldImage endImage = new FromFileImage(
                new Posn(
                        CollectTheFruit.width / 2, 
                        CollectTheFruit.height / 2),
                "sky.png");
        if (this.player.isDead()) {
            endImage = endImage.overlayImages(
                    new TextImage(
                            new Posn(CollectTheFruit.width / 2,
                                    CollectTheFruit.height / 2),
                            "YOU LOOSE",
                            new Black()));
            return new WorldEnd(true, endImage);
        }
        if (this.player.hasWon()) {
            endImage = endImage.overlayImages(
                    new TextImage(
                            new Posn(CollectTheFruit.width / 2,
                                    CollectTheFruit.height / 2),
                            "YOU WIN!!!",
                            new Black()));
            return new WorldEnd(true, endImage);
        }
        return new WorldEnd(false, makeImage());
    }
}