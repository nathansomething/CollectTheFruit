/**
 * The class that runs the DrawFace game.
 * @author Viera K. Proulx
 * @version 11-12-2013
 *
 */
public class GameRunner {
    
    GameRunner() { };
    
    /** An instance of the <code>Examples</code> class that defines
     * the initial world
     */
    CollectTheFruit ctf = new CollectTheFruit();
    
    /** the initial world that invokes the <code>bigBang</code> method */
    
    /** 
     * Method that runs the game
     */
    void run() {
        this.ctf.bigBang(CollectTheFruit.width, CollectTheFruit.height, 0.05);
    }
    
    /**
     * Main method to run the game
     * @param argv unused
     */
    public static void main(String[] argv) {
        GameRunner gm = new GameRunner();
        gm.run();
    }
}