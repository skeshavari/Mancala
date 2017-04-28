package nl.sogyo.mancala;

public class Mancala {
    
    Pit pit1;
    Pit pit2;
    Pit pit3;
    Pit pit4;
    Pit pit5;
    Pit pit6;
    Kalaha kalaha1;
    Pit pit8;
    Pit pit9;
    Pit pit10;
    Pit pit11;
    Pit pit12;
    Pit pit13;
    Kalaha kalaha2;
    
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

                System.out.println("Pit1 Owner is active " + pit1.getOwner().getIsActiveTurn());
        System.out.println("Pit2 Owner is active " + pit2.getOwner().getIsActiveTurn());
        System.out.println("Pit3 Owner is active " + pit3.getOwner().getIsActiveTurn());
        System.out.println("Pit4 Owner is active " + pit4.getOwner().getIsActiveTurn());
        System.out.println("Pit5 Owner is active " + pit5.getOwner().getIsActiveTurn());
        System.out.println("Pit6 Owner is active " + pit6.getOwner().getIsActiveTurn());
        System.out.println("kalaha1 Owner is active " + kalaha1.getOwner().getIsActiveTurn());
        System.out.println("Pit8 Owner is active " + pit8.getOwner().getIsActiveTurn());
        System.out.println("Pit9 Owner is active " + pit9.getOwner().getIsActiveTurn());
        System.out.println("Pit10 Owner is active " + pit10.getOwner().getIsActiveTurn());
        System.out.println("Pit11 Owner is active " + pit11.getOwner().getIsActiveTurn());
        System.out.println("Pit12 Owner is active " + pit12.getOwner().getIsActiveTurn());
        System.out.println("Pit13 Owner is active " + pit13.getOwner().getIsActiveTurn());
        System.out.println("kalaha2 Owner is active " + kalaha2.getOwner().getIsActiveTurn());
        
        pit4.pickAndPlayPit();
        
                System.out.println("\n\n\nPit1 Owner is active " + pit1.getOwner().getIsActiveTurn());
        System.out.println("Pit2 Owner is active " + pit2.getOwner().getIsActiveTurn());
        System.out.println("Pit3 Owner is active " + pit3.getOwner().getIsActiveTurn());
        System.out.println("Pit4 Owner is active " + pit4.getOwner().getIsActiveTurn());
        System.out.println("Pit5 Owner is active " + pit5.getOwner().getIsActiveTurn());
        System.out.println("Pit6 Owner is active " + pit6.getOwner().getIsActiveTurn());
        System.out.println("kalaha1 Owner is active " + kalaha1.getOwner().getIsActiveTurn());
        System.out.println("Pit8 Owner is active " + pit8.getOwner().getIsActiveTurn());
        System.out.println("Pit9 Owner is active " + pit9.getOwner().getIsActiveTurn());
        System.out.println("Pit10 Owner is active " + pit10.getOwner().getIsActiveTurn());
        System.out.println("Pit11 Owner is active " + pit11.getOwner().getIsActiveTurn());
        System.out.println("Pit12 Owner is active " + pit12.getOwner().getIsActiveTurn());
        System.out.println("Pit13 Owner is active " + pit13.getOwner().getIsActiveTurn());
        System.out.println("kalaha2 Owner is active " + kalaha2.getOwner().getIsActiveTurn());
        
        pit10.pickAndPlayPit();

        pit10.pickAndPlayPit();
        //printAllStones();

        
        
    }
    
    
    
}
