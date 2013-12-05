import java.util.*;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;

//Stores and manages the powerups on the field and takes care 
//of any other properties on the field
public class Field {
    ArrayList<Float> hungerDeltaPcrntArray = new ArrayList<Float>();
    ArrayList<Float> SpeedDeltaPcrntArray = new ArrayList<Float>();
    ArrayList<Integer> pointsDeltaArray = new ArrayList<Integer>();
    ArrayList<String> ImageLocationArray = new ArrayList<String>();
    
    //make all the powerup arrays
    void makeAvocado() {
        this.hungerDeltaPcrntArray.add(0.17f);
        this.SpeedDeltaPcrntArray.add(0.50f);
        this.pointsDeltaArray.add(600);
        this.ImageLocationArray.add("avocado.png");
    }
    
    /*void makeMeat() {
        this.hungerDeltaPcrntArray.add(0.55f);
        this.SpeedDeltaPcrntArray.add(0.10f);
        this.pointsDeltaArray.add(550);
        this.ImageLocationArray.add("meat.png");
    }
    
    void makeBroccoli() {
        this.hungerDeltaPcrntArray.add(0.07f);
        this.SpeedDeltaPcrntArray.add(0.05f);
        this.pointsDeltaArray.add(1400);
        this.ImageLocationArray.add("broccoli.png");
    }
    
    void makeBanana() {
        this.hungerDeltaPcrntArray.add(0.22f);
        this.SpeedDeltaPcrntArray.add(0.15f);
        this.pointsDeltaArray.add(250);
        this.ImageLocationArray.add("broccoli.png");
    }
    
    void makeGrape() {
        this.hungerDeltaPcrntArray.add(0.08f);
        this.SpeedDeltaPcrntArray.add(0.10f);
        this.pointsDeltaArray.add(75);
        this.ImageLocationArray.add("grape.png");
    }
    
    void makeCookie() {
        this.hungerDeltaPcrntArray.add(0.03f);
        this.SpeedDeltaPcrntArray.add(-0.10f);
        this.pointsDeltaArray.add(30);
        this.ImageLocationArray.add("cookie.png");
    }
    
    void makeDonut() {
        this.hungerDeltaPcrntArray.add(-0.15f);
        this.SpeedDeltaPcrntArray.add(-0.1f);
        this.pointsDeltaArray.add(10);
        this.ImageLocationArray.add("donut.png");
    }
    
    void makeFries() {
        this.hungerDeltaPcrntArray.add(0.11f);
        this.SpeedDeltaPcrntArray.add(-0.25f);
        this.pointsDeltaArray.add(20);
        this.ImageLocationArray.add("fries.png");
    }
    
    void makeChocolate() {
        this.hungerDeltaPcrntArray.add(0.05f);
        this.SpeedDeltaPcrntArray.add(-0.2f);
        this.pointsDeltaArray.add(5);
        this.ImageLocationArray.add("chocolate.png");
    }*/
    
    void makeTwinkie() {
        this.hungerDeltaPcrntArray.add(-0.1f);
        this.SpeedDeltaPcrntArray.add(-0.35f);
        this.pointsDeltaArray.add(-100);
        this.ImageLocationArray.add("twinkie.png");
    }
    
    ArrayList<Powerup> pUps;
    final int numPups = 2;
    
    //For testing purposes only
    Field(ArrayList<Powerup> pUps) {
        makeAvocado();
        /*makeMeat();
        makeBroccoli();
        makeBanana();
        makeGrape();
        makeCookie();
        makeDonut();
        makeFries();
        makeChocolate();*/
        makeTwinkie();
        this.pUps = pUps;
    }
    
    Field() {
        makeAvocado();
        /*makeMeat();
        makeBroccoli();
        makeBanana();
        makeGrape();
        makeCookie();
        makeDonut();
        makeFries();
        makeChocolate();*/
        makeTwinkie();
        this.pUps = new ArrayList<Powerup>();
        //this.generatePowerups(CollectTheFruit.powerupsPerScreen);
    }
    
    //Returns a random y position which determines if a 
    //powerup will be in the bottom row or on top
    int getYPos() {
        if (new Random().nextBoolean()) {
            return CollectTheFruit.height - 35;
        }
        else {
            return CollectTheFruit.height - 200;
        }
    }
    
    //Add a new powerup to the ArrayList pUps, 
    //which stores the current powerups on screen
    void addPowerup(int xPos, int yPos) {
        int powerupIndex = new Random().nextInt(this.numPups);
        Posn loc = new Posn(xPos, yPos);
        this.pUps.add(new Powerup(
                this.hungerDeltaPcrntArray.get(powerupIndex),
                this.SpeedDeltaPcrntArray.get(powerupIndex),
                this.pointsDeltaArray.get(powerupIndex),
                loc,
                new FromFileImage(
                        loc, this.ImageLocationArray.get(powerupIndex))));
    }
    
    //Whenever the screen gets refreshed, add a new set of powerups
    public void generatePowerups(int number) {
        for (int i = 0; i < number; i++) {
            int spaceBetweenPowerups = CollectTheFruit.width / number;
            if (i != 0) {
              //Don't add a powerup at the edge of the screen
              this.addPowerup(i * spaceBetweenPowerups, getYPos());
            }
        }
    }
    
}
