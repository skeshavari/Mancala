package nl.sogyo.mancala;

public class Mancala {

    public static void main( String[] args ) {
        System.out.println("This is the start of a Mancala Game!");

        

        
        Pit pit1 = new Pit();
        Pit pit2 = (Pit) pit1.getNeighbour();
        Pit pit3 = (Pit) pit2.getNeighbour();
        Pit pit4 = (Pit) pit3.getNeighbour();
        Pit pit5 = (Pit) pit4.getNeighbour();
        Pit pit6 = (Pit) pit5.getNeighbour();
        Kalaha kalaha1 = (Kalaha) pit6.getNeighbour();
        Pit pit8 = (Pit) kalaha1.getNeighbour();
        Pit pit9 = (Pit) pit8.getNeighbour();
        Pit pit10 = (Pit) pit9.getNeighbour();
        Pit pit11 = (Pit) pit10.getNeighbour();
        Pit pit12 = (Pit) pit11.getNeighbour();
        Pit pit13 = (Pit) pit12.getNeighbour();
        Kalaha kalaha2 = (Kalaha) pit13.getNeighbour();        
        
        pit6.receiveStones(12);
        pit6.pickAndPlayPit();
        
        System.out.println("pit1 stones    " + pit1.getTotalStones());
        System.out.println("pit2 stones    " + pit2.getTotalStones());
        System.out.println("pit3 stones    " + pit3.getTotalStones());
        System.out.println("pit4 stones    " + pit4.getTotalStones());
        System.out.println("pit5 stones    " + pit5.getTotalStones());
        System.out.println("pit6 stones    " + pit6.getTotalStones());
        System.out.println("Kalaha1 stones " + kalaha1.getTotalStones());
        System.out.println("pit8 stones    " + pit8.getTotalStones());
        System.out.println("pit9 stones    " + pit9.getTotalStones());
        System.out.println("pit10 stones   " + pit10.getTotalStones());
        System.out.println("pit11 stones   " + pit11.getTotalStones());
        System.out.println("pit12 stones   " + pit12.getTotalStones());
        System.out.println("pit13 stones   " + pit13.getTotalStones());
        System.out.println("kalaha2 stones " + kalaha2.getTotalStones());
        System.out.println(kalaha2.getClass().getSimpleName().equalsIgnoreCase("Kalaha"));
    }
}
