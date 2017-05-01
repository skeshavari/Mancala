package nl.sogyo.mancala;


public class Kalaha extends BoardMember {
    

       
    public Kalaha (BoardMember firstPit, int totalInstancesCreated, Contestant firstPlayer){
        super(firstPit, totalInstancesCreated, firstPlayer);
        super.emptyStones();

    }
    
    @Override
    public BoardMember getOpposingBoardMember(){
        BoardMember iHaveNoOpposingPitMember = this;
        return iHaveNoOpposingPitMember;
    } 
    
    void takeAndPassStones(int stonesToPassOn) {
        if (super.getOwner().getIsActiveTurn() == true) {
            if (stonesToPassOn == 1){
                super.receiveStones(1);
                super.accessGame();
            } else {
                super.receiveStones(1);
                super.getNeighbour().takeAndPassStones((stonesToPassOn-1));
            }
        } else {
            super.getNeighbour().takeAndPassStones(stonesToPassOn);
        }
    }

    
    @Override
    public void captureMe(BoardMember activeKalaha){
    }
    
    
}
