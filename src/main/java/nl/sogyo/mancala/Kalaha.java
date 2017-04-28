package nl.sogyo.mancala;


public class Kalaha extends BoardMember {


       
    public Kalaha (BoardMember firstPit, int totalInstancesCreated, Contestant firstPlayer){
        super(firstPit, totalInstancesCreated, firstPlayer);
        super.emptyStones();

    }
    
    @Override
    public BoardMember getOpposingBoardMember(){
        BoardMember iHaveNoOpposingMember = this;
        return iHaveNoOpposingMember;
    } 
    
    @Override
    public void captureMe(BoardMember activeKalaha){
    }
}
