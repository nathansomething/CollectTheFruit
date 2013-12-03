import java.util.*;

import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;

public class Field {
    ArrayList<Float> hungerDeltaPcrntArray = new ArrayList<Float>();
    ArrayList<Float> SpeedDeltaPcrntArray = new ArrayList<Float>();
    ArrayList<Integer> pointsDeltaArray = new ArrayList<Integer>();
    ArrayList<String> ImageLocationArray = new ArrayList<String>();
    
    void makeAvocado() {
        this.hungerDeltaPcrntArray.add(0.7f);
        this.SpeedDeltaPcrntArray.add(0.4f);
        this.pointsDeltaArray.add(1000);
        this.ImageLocationArray.add("avocado.png");
    }
    
    void makeBanana() {
        this.hungerDeltaPcrntArray.add(0.4f);
        this.SpeedDeltaPcrntArray.add(0.25f);
        this.pointsDeltaArray.add(200);
        this.ImageLocationArray.add("banana.png");
    }
    
    void makeGrape() {
        this.hungerDeltaPcrntArray.add(0.15f);
        this.SpeedDeltaPcrntArray.add(0.05f);
        this.pointsDeltaArray.add(50);
        this.ImageLocationArray.add("grape.png");
    }
    
    void makeCookie() {
        this.hungerDeltaPcrntArray.add(0.01f);
        this.SpeedDeltaPcrntArray.add(-0.1f);
        this.pointsDeltaArray.add(10);
        this.ImageLocationArray.add("cookie.png");
    }
    
    void makeDonut() {
        this.hungerDeltaPcrntArray.add(0.01f);
        this.SpeedDeltaPcrntArray.add(-0.2f);
        this.pointsDeltaArray.add(5);
        this.ImageLocationArray.add("donut.png");
    }
    
    void makeTwinkie() {
        this.hungerDeltaPcrntArray.add(0.01f);
        this.SpeedDeltaPcrntArray.add(-0.35f);
        this.pointsDeltaArray.add(1);
        this.ImageLocationArray.add("twinkie.png");
    }
    
    ArrayList<Powerup> pUps;
    final int numPups = 6;
    
    //For testing purposes only
    Field(ArrayList<Powerup> pUps) {
        makeAvocado();
        makeBanana();
        makeGrape();
        makeCookie();
        makeDonut();
        makeTwinkie();
        this.pUps = pUps;
    }
    
    Field() {
        makeAvocado();
        makeBanana();
        makeGrape();
        makeCookie();
        makeDonut();
        makeTwinkie();
        this.pUps = new ArrayList<Powerup>();
        this.generatePowerups(CollectTheFruit.powerupsPerScreen);
    }
    
    int getYPos() {
        if(new Random().nextBoolean()) {
            return CollectTheFruit.height - 35;
        }
        else {
            return CollectTheFruit.height - 200;
        }
    }
    
    public void generatePowerups(int number) {
        for(int i = 0; i < number; i++) {
            int spaceBetweenPowerups = CollectTheFruit.width / number;
            if(i != 0) {
              //Don't add a powerup at the edge of the screen
              this.addPowerup(i * spaceBetweenPowerups, getYPos());
            }
        }
    }
    
    void addPowerup(int xPos, int yPos) {
        int powerupIndex = new Random().nextInt(this.numPups);
        Posn loc = new Posn(xPos, yPos);
        this.pUps.add(new Powerup(
                this.hungerDeltaPcrntArray.get(powerupIndex),
                this.SpeedDeltaPcrntArray.get(powerupIndex),
                this.pointsDeltaArray.get(powerupIndex),
                loc,
                new FromFileImage(loc, this.ImageLocationArray.get(powerupIndex))));
    }
    
}
