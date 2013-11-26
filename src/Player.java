import javalib.colors.Black;
import javalib.worldimages.CircleImage;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;


public class Player {
    int hunger; //from 0 to 100
    int speed; //from 0 to 10
    int score; //Get points for every fruit, loose points for every sweet
    int level; //Just based directly on your score
    Posn loc;
    CircleImage playerImage;
    
    final int maxHunger = 1000;
    final int maxSpeed = 10;
    
    Player(int hunger, int speed, int score, Posn loc) {
        this.hunger = hunger;
        this.speed = speed;
        this.score = score;
        this.loc = loc;
        this.playerImage = new CircleImage(this.loc, 50, new Black());
    }
    
    Player() {
        //starting values
        this.hunger = 700;
        this.speed = 6;
        this.score = 0;
        this.loc = new Posn(0, CollectTheFruit.height - 60);
        this.playerImage = new CircleImage(this.loc, 50, new Black());
    }
    
    //determine if the player is dead
    boolean isDead() {
        return this.hunger <= 0 || this.speed == 0;
    }
    
    //determine if the player has won
    boolean hasWon() {
        return this.score >= 10000;
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
    }
    
    //Update the stats of the player with the given powerup
    void updateStats(Powerup powerup) {
        this.changeHunger(powerup.hungerDeltaPrcnt);
        this.changeSpeed(powerup.speedDeltaPrcnt);
        this.changeScore(powerup.points);
    }
    
    //Compute the level based on the player's score
    int getLevel() {
        return this.score / 1000;
    }
    
    //Changes a players location
    void move(int deltaXLoc, int deltaYloc) {
        this.loc.x += deltaXLoc;
        this.loc.y += deltaYloc;
    }
    
    int getX() {
        return this.loc.x;
    }
    
}
