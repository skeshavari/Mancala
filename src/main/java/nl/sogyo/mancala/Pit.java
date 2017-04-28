package nl.sogyo.mancala;


public class Pit extends BoardMember {
    
    public Pit(){
        super();
    }
    
    public Pit(BoardMember firstPit, int totalInstancesCreated, Contestant firstPlayer){
        super(firstPit, totalInstancesCreated, firstPlayer);

    }
    
    void pickAndPlayPit(){
        int stonesToPassOn=getTotalStones();
        emptyStones();
        getNeighbour().takeAndPassStones(stonesToPassOn);
    }
/*
    @Override
    void receiveStones(int stonesReceived){
        
    } */
    
}
