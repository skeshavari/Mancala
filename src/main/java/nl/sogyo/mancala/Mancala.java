package nl.sogyo.mancala;

public class Mancala {
    
    public static void main( String[] args ) {
        System.out.println("This is the start of a Mancala Game!");
        GameState newGame = new GameState();
        newGame.createGUIFrame();
    }
    
}
