//import java.awt.Color;

//import javalib.colors.*;
//import java.util.ArrayList;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
//import javalib.worldimages.RectangleImage;
//import javalib.worldimages.WorldImage;

//Represent a powerup
public class Powerup {
    float hungerDeltaPrcnt;
    float speedDeltaPrcnt;
    int points;
    Posn loc;
    FromFileImage image;

    // Create a new powerup with the given stats
    Powerup(float hungerDeltaPrcnt, float speedDeltaPrcnt, int points,
            Posn loc, FromFileImage image) {
        this.hungerDeltaPrcnt = hungerDeltaPrcnt;
        this.speedDeltaPrcnt = speedDeltaPrcnt;
        this.points = points;
        this.loc = loc;
        this.image = image;
    }

}
