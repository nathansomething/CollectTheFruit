import java.util.*;
import javalib.worldimages.Posn;

public class Field {
    ArrayList<Powerup> pUps;
    final int numPups = 6;
    
    Field(ArrayList<Powerup> pUps) {
        this.pUps = pUps;
    }
    
    Field() {
        this.pUps = new ArrayList<Powerup>();
        this.generatePowerups(10);
    }
    
    public Posn randStart(int xPos) {
        int yPos;
        if(new Random().nextBoolean()) {
            yPos = CollectTheFruit.height - 35;
        }
        else {
            yPos = CollectTheFruit.height - 200;
        }
        return new Posn (
            xPos,
            yPos);
      }
    
    public void generatePowerups(int number) {
        for(int i = 0; i < number; i++) {
            this.addPowerup(i * 200);
        }
    }
    
    void addPowerup(int xPos) {
        Random rand = new Random();
        switch(rand.nextInt(5)) {
        case 0:
            this.pUps.add(new Avocado(randStart(xPos)));
            break;
        case 1:
            this.pUps.add(new Banana(randStart(xPos)));
            break;
        case 2:
            this.pUps.add(new Grape(randStart(xPos)));
            break;
        case 3:
            this.pUps.add(new Cookie(randStart(xPos)));
            break;
        case 4:
            this.pUps.add(new Donut(randStart(xPos)));
            break;
        case 5:
            this.pUps.add(new Twinkie(randStart(xPos)));
            break;
        default:
            throw new RuntimeException("Powerup does not exist!!!");
        }
    }
    
}
