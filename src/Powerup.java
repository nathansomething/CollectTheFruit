//import java.awt.Color;

//import javalib.colors.*;
//import java.util.ArrayList;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
//import javalib.worldimages.RectangleImage;
//import javalib.worldimages.WorldImage;


public class Powerup {
    float hungerDeltaPrcnt;
    float speedDeltaPrcnt;
    int points;
    Posn loc;
    FromFileImage image;
    
    Powerup(float hungerDeltaPrcnt, float speedDeltaPrcnt, int points, Posn loc, FromFileImage image) { 
       this.hungerDeltaPrcnt = hungerDeltaPrcnt;
       this.speedDeltaPrcnt = speedDeltaPrcnt;
       this.points = points;
       this.loc = loc;
       this.image = image;
    }
    
}

