//import javalib.colors.Black;
//import javalib.worldimages.CircleImage;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
//import javalib.worldimages.RectangleImage;


public class Player {
    float hunger; //from 0 to 500
    float speed; //from 0 to 10
    int score; //Get points for every fruit, loose points for every sweet
    Posn loc;
    FromFileImage playerImage;
    
    final int maxHunger = 1000;
    final int maxSpeed = 10;
    
    Player(int hunger, int speed, int score, Posn loc) {
        this.hunger = hunger;
        this.speed = speed;
        this.score = score;
        this.loc = loc;
        this.playerImage = new FromFileImage(this.loc, "wizard_pengiun.png");
    }
    
    Player() {
        //starting values
        this.hunger = 700;
        this.speed = 6;
        this.score = 0;
        this.loc = new Posn(0, CollectTheFruit.height - 30);
        this.playerImage = new FromFileImage(this.loc, "wizard_penguin.png");
    }
    
    //determine if the player is dead
    boolean isDead() {
        return this.hunger <= 0 || this.speed == 0;
    }
    
    //determine if the player has won
    boolean hasWon() {
        return this.score >= 100000;
    }
    
    //changes the hunger of the player by the given amount
    //(posative values increase hunger, negative decresases)
    void changeHunger(float hungerDelta) {
        if(hungerDelta > 0) {
            this.hunger += (this.maxHunger - this.hunger) * hungerDelta;
        }
        else {
            this.hunger += this.hunger * hungerDelta;
        }
        if(this.hunger < 0) {
            this.hunger = 0;
        }
    }
    void changeHunger(int hungerDelta) {
        this.hunger += hungerDelta;
        if(this.hunger < 0) {
            this.hunger = 0;
        }
    }
    
    //changes the speed of the player by the given amount
    //(negative values decrease speed)
    void changeSpeed(float speedDelta) {
        if(speedDelta > 0) {
            this.speed += (this.maxSpeed - this.speed) * speedDelta;
        }
        else {
            this.speed += this.speed * speedDelta;
        }
    }
    
    //changes the score of the player by the given amount
    void changeScore(int scoreDelta) {
        this.score += scoreDelta;
        if(this.score < 0) {
            this.score = 0;
        }
    }
    
    //Update the stats of the player with the given powerup
    void updateStats(Powerup powerup) {
        this.changeHunger(powerup.hungerDeltaPrcnt);
        this.changeSpeed(powerup.speedDeltaPrcnt);
        this.changeScore(powerup.points);
    }
    
    //Changes a players location
    void move(int deltaXLoc, int deltaYloc) {
        this.loc.x += deltaXLoc;
        this.loc.y += deltaYloc;
        this.playerImage.pinhole = this.loc;
    }
    
}
