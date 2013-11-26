//import java.awt.Color;

//import javalib.colors.*;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
//import javalib.worldimages.RectangleImage;
//import javalib.worldimages.WorldImage;


public abstract class Powerup {
    float hungerDeltaPrcnt;
    float speedDeltaPrcnt;
    int points;
    Posn loc;
    FromFileImage image;
    
    Powerup() { }
    
    boolean isOffScreen() {
        return 
                this.loc.x < 0 ||
                this.loc.x > CollectTheFruit.width ||
                this.loc.y < 0 ||
                this.loc.y > CollectTheFruit.height;
    }
    
    void move(int deltaXLoc, int deltaYLoc) {
        this.loc.x += deltaXLoc;
        this.loc.y += deltaYLoc;
    }
}

class Avocado extends Powerup {
    
    Avocado(Posn loc) {
        super();
        this.loc = loc; 
        this.hungerDeltaPrcnt = 0.7f;
        this.speedDeltaPrcnt = 0.4f;
        this.points = 1000;
        this.image = new FromFileImage(loc, "avocado.png");
        //this.image = new RectangleImage(this.loc, 50, 50, Color.green);
    }
    
}

class Banana extends Powerup {
    
    Banana(Posn loc) {
        super();
        this.loc = loc; 
        this.hungerDeltaPrcnt = 0.4f;
        this.speedDeltaPrcnt = 0.25f;
        this.points = 200;
        this.image = new FromFileImage(loc, "banana.png");
        //this.image = new RectangleImage(this.loc, 50, 50, Color.yellow);
    }
    
}

class Grape extends Powerup {
    
    Grape(Posn loc) {
        super();
        this.loc = loc; 
        this.hungerDeltaPrcnt = 0.15f;
        this.speedDeltaPrcnt = 0.05f;
        this.points = 50;
        this.image = new FromFileImage(loc, "grapes.png");
        //this.image = new RectangleImage(this.loc, 50, 50, Color.pink);
    }
    
}

class Cookie extends Powerup {
    
    Cookie(Posn loc) {
        super();
        this.loc = loc;
        this.hungerDeltaPrcnt = 0.05f;
        this.speedDeltaPrcnt = -0.1f;
        this.points = 10;
        this.image = new FromFileImage(loc, "cookie.png");
        //this.image = new RectangleImage(this.loc, 50, 50, Color.blue);
    }
    
}

class Donut extends Powerup {
    
    Donut(Posn loc) {
        super();
        this.loc = loc; 
        this.hungerDeltaPrcnt = 0.1f;
        this.speedDeltaPrcnt = -0.2f;
        this.points = 5;
        this.image = new FromFileImage(loc, "donut.png");
        //this.image = new RectangleImage(this.loc, 50, 50, Color.red);
    }
    
}

class Twinkie extends Powerup {
    
    Twinkie(Posn loc) {
        super();
        this.loc = loc; 
        this.hungerDeltaPrcnt = 0.1f;
        this.speedDeltaPrcnt = -0.4f;
        this.points = 1;
        this.image = new FromFileImage(loc, "twinkie.png");
        //this.image = new RectangleImage(this.loc, 50, 50, Color.black);
    }
    
}

//For testing purposes
class Default extends Powerup {
    
    Default(Posn loc) {
        super();
        this.loc = loc; 
        this.hungerDeltaPrcnt = 0.1f;
        this.speedDeltaPrcnt = -0.4f;
        this.points = 1;
    }
}


