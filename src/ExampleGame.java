import java.util.ArrayList;
import java.util.Arrays;
import javalib.colors.Black;
import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import tester.*;

public class ExampleGame {
    
    //Example Players
    Player player = new Player();
    Player deadPlayer = new Player(0, 3, 20000, new Posn(500, 0));
    
    //Example positions
    Posn p1 = new Posn(100, 100);
    Posn p2 = new Posn(800, 20);
    Posn p3 = new Posn(400, 50);
    Posn p4 = new Posn(300, 75);
    Posn p5 = new Posn(500, 25);
    Posn p6 = new Posn(600, 20);
    
    //Example Powerups
    /*Powerup a1 = new Avocado(p1);
    Powerup b1 = new Banana(p2);
    Powerup c1 = new Cookie(p3);
    Powerup d1 = new Donut(p4);
    Powerup t1 = new Twinkie(p5);*/
    
    //Example ArrayList<Powerup>s 
    /*ArrayList<Powerup> list1 = new ArrayList<Powerup>(Arrays.asList(a1, b1, c1)); 
    ArrayList<Powerup> list2 = new ArrayList<Powerup>(Arrays.asList(b1, c1, t1)); 
    ArrayList<Powerup> list3 = new ArrayList<Powerup>(Arrays.asList(d1, c1, a1, b1, t1)); */
    
    //Example Fields
    /*Field field0 = new Field();
    Field field1 = new Field(list1);
    Field field2 = new Field(list2);
    Field field3 = new Field(list3);*/
    
    //Example Players
    Player player1 = new Player();
    Player player2 = new Player(91, 7, 1000, new Posn(0, 0));
    Player player3 = new Player(73, 4, 1, new Posn(0, 0));
    Player player4 = new Player(73, 4, 1, p2);
    Player player5 = new Player(73, 4, 1, p5);
    Player player6 = new Player(73, 4, 1, p2);


    //Example CollectTheFruit
    /*CollectTheFruit ctf1 = new CollectTheFruit(player1, field1);
    CollectTheFruit ctf2 = new CollectTheFruit();
    CollectTheFruit ctf3 = new CollectTheFruit();
    CollectTheFruit ctf4 = new CollectTheFruit(player3, field1);
    CollectTheFruit ctf5 = new CollectTheFruit(player4, field1);
    CollectTheFruit ctf6 = new CollectTheFruit(player5, field3);
    CollectTheFruit ctf7 = new CollectTheFruit(player6, field2);*/
    //Game Runner Class
    GameRunner gameRunner = new GameRunner();
    
  //Example WorldEnd
    WorldEnd end = new WorldEnd(true, new TextImage(
            new Posn(800 / 2, 250 / 2),
            "YOU LOSE",
            new Black()));
    
    Posn defaultPos = new Posn(0, 290);
    
    //test the method worldEnds
    /*void testWorldsEnds(Tester t){
      t.checkExpect(this.ctf1.worldEnds(), new WorldEnd(false, ctf1.makeImage()));
      player1.changeHunger(-100);
      t.checkExpect(player1.isDead(), true);
      t.checkExpect(this.ctf1.worldEnds(), end);
    }*/
    
    void resetPlayer() {
        this.player = new Player();
    }
    //Test the isDead method
    void testIsDead(Tester t) {
        this.resetPlayer();
        t.checkExpect(this.player.isDead(), false);
        t.checkExpect(this.deadPlayer.isDead(), true);
    }
    
    //Change the hunger level of a player
    void testChangeHunger(Tester t) {
        this.resetPlayer();
        this.player.changeHunger(0.1f);
        t.checkExpect(this.player, new Player(73, 6, 0, defaultPos));
        this.resetPlayer();
        this.player.changeHunger(-0.1f);
        t.checkExpect(this.player, new Player(63, 6, 0, defaultPos));
        this.resetPlayer();
        this.player.changeHunger(10);
        t.checkExpect(this.player, new Player(80, 6, 0, defaultPos));
    }
    
    //Test the changeSpeed method
    void testChangeSpeed(Tester t) {
        this.resetPlayer();
        this.player.changeSpeed(0.3f);
        t.checkExpect(this.player, new Player(70, 7, 0, defaultPos));
        this.resetPlayer();
        this.player.changeSpeed(-0.2f);
        t.checkExpect(this.player, new Player(70, 4, 0, defaultPos));
    }
    
    //Test the updateStatus method
    /*void testUpdateStatus(Tester t) {
        this.resetPlayer();
        player.updateStats(new Avocado(defaultPos));
        t.checkExpect(player, new Player(91, 7, 1000, defaultPos));
        this.resetPlayer();
        player.updateStats(new Twinkie(defaultPos));
        t.checkExpect(player, new Player(73, 3, 1, defaultPos));
    }*/
    
    //Test the move method
    void testMove(Tester t) {
        this.resetPlayer();
        this.player.move(100, 0);
        t.checkExpect(this.player, new Player(70, 6, 0, new Posn(100, 290)));
        this.resetPlayer();
        this.player.move(50, 10);
        t.checkExpect(this.player, new Player(70, 6, 0, new Posn(50, 300)));
    }
    
    //Test the offScreen method
    /*void testOffScreen(Tester t) {
        t.checkExpect(new Default(new Posn(-50, 0)).isOffScreen(), true);
        t.checkExpect(new Default(new Posn(1200, 0)).isOffScreen(), true);
        t.checkExpect(new Default(new Posn(0, -20)).isOffScreen(), true);
        t.checkExpect(new Default(new Posn(0, 500)).isOffScreen(), true);
        t.checkExpect(new Default(new Posn(50, 50)).isOffScreen(), false);
    }*/
    
    //Test the nearX method
    void testNearX(Tester t) {
        //t.checkExpect()
    }
}
