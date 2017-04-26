package nl.sogyo.mancala;

public abstract class BoardMember {
    //int startingStones = 4;
    private int TotalPitsPerPlayer = 6;
    private int totalStones = 4;
    private BoardMember neighbour;
    private Contestant owner;
    
    
    public BoardMember(){
        
        BoardMember firstPit = this;
        Contestant firstPlayer = new Contestant();
        owner = firstPlayer;
        neighbour = new Pit(firstPit, 2, firstPlayer);
    }

    public BoardMember(BoardMember firstPit, int instancesOfPitsCreated, Contestant firstPlayer){
        
        owner = firstPlayer;
        if (instancesOfPitsCreated == TotalPitsPerPlayer  || instancesOfPitsCreated == (TotalPitsPerPlayer*2)+1 ) {
            
            neighbour = new Kalaha(firstPit, instancesOfPitsCreated+1, firstPlayer);
            //neighbour = firstPit;
        } else if (instancesOfPitsCreated == TotalPitsPerPlayer+1 ) {
            //Creates new pits with opponent as their owner
            neighbour = new Pit(firstPit, instancesOfPitsCreated+1, firstPlayer.getOpponent());
        } else if (instancesOfPitsCreated == (TotalPitsPerPlayer+1)*2 ) {
            //Reached Second Kalaha, so should stop the chain by linking kalaha to first pit ever created
            neighbour = firstPit; 
            
        } else {
            neighbour = new Pit(firstPit, instancesOfPitsCreated+1, firstPlayer);
        }
        
    }
    
    void takeAndPassStones(int stones) {
        if (stones == 1) {
            totalStones++; //ends move
        } else {
            totalStones++;
            neighbour.takeAndPassStones((stones-1));
        }
    }
    
    public int getTotalStones(){
        return totalStones;
    }
    
    void emptyStones(){
        totalStones = 0;
    }
    
    public BoardMember getNeighbour(){
        return neighbour; 
    }
    
    public Contestant getOwner(){
        return owner;
    } 
    
}