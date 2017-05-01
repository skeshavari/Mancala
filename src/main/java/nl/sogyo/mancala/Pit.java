package nl.sogyo.mancala;


public class Pit extends BoardMember {
    
    public Pit(){
        super();
    }
    
    public Pit(BoardMember firstPit, int totalInstancesCreated, Contestant firstPlayer){
        super(firstPit, totalInstancesCreated, firstPlayer);
    }
    
    void pickAndPlayPit(){
        if (super.getOwner().getIsActiveTurn() == true){
            if (super.getTotalStones() == 0) {
                System.out.println("There are no stones in here. Pick again..");
            } else {
                int stonesToPassOn=getTotalStones();
                emptyStones();
                getNeighbour().takeAndPassStones(stonesToPassOn);
                accessGame();
            }
        } else {
            System.out.println("You cannot choose the opponents side..");
        }
    }
    
    @Override
    void receiveStones(int stonesReceived){
    }
        
    void subtractStones(int stonesReceived){
        super.receiveStones(-stonesReceived);
    }
}
