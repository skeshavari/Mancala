package nl.sogyo.mancala;


public class Kalaha extends BoardMember {
       
    public Kalaha (BoardMember firstPit, int totalInstancesCreated, Contestant firstPlayer){
        super(firstPit, totalInstancesCreated, firstPlayer);
        emptyStones();
    }
    
    @Override
    public BoardMember getOpposingBoardMember(){
        BoardMember iAmMyOwnOpposingMember = this;
        return iAmMyOwnOpposingMember;
    } 
    
    @Override
    void takeAndPassStones(int stonesToPassOn) {
        if (ownerIsActive()) {
            receiveStones(1);
            stonesToPassOn--;
        }
        if (stonesToPassOn > 0){
                getNeighbour().takeAndPassStones(stonesToPassOn);
        }
    }
    
    @Override
    public void captureMe(BoardMember activeKalaha){
    }
    

}
