package nl.sogyo.mancala;


public class Pit extends BoardMember {
    
    public Pit(){
        super();
    }
    
    public Pit(BoardMember firstPit, int totalInstancesCreated, Contestant firstPlayer){
        super(firstPit, totalInstancesCreated, firstPlayer);
    }
    
    @Override
    void pickThisBoardMember(){
        if (super.ownerIsActive() == true){
            playThisPit();
        } else {
            System.out.println("You cannot choose the opponents side..");
        }
    }
    
    void playThisPit(){
        if (super.getTotalStones() == 0) {
            System.out.println("There are no stones in here. Pick again..");
        } else {
            int stonesToPassOn=getTotalStones();
            emptyStones();
            getNeighbour().takeAndPassStones(stonesToPassOn);
            accessGame();
        }
    }
    
    @Override
    void receiveStones(int stonesReceived){
    }
        
    void subtractStones(int stonesReceived){
        super.receiveStones(-stonesReceived);
    }
}
