package nl.sogyo.mancala;


public class Pit extends BoardMember {
    
    public Pit(){
        super();
    }
    
    public Pit(BoardMember firstPit, int totalInstancesCreated, Contestant firstPlayer){
        super(firstPit, totalInstancesCreated, firstPlayer);

    }
    
    void pickAndPlayPit(){
        if (super.getOwner().getIsActiveTurn() == true) {
        int stonesToPassOn=getTotalStones();
        emptyStones();
        getNeighbour().takeAndPassStones(stonesToPassOn);
        } else if (super.getOwner().getIsActiveTurn() == false) {
            System.out.println("You Can\'t choose the opponents side..");
        }
    }

    @Override
    void receiveStones(int stonesReceived){
    }
    
}
