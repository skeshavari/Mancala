package nl.sogyo.mancala;

public abstract class BoardMember {
    private int TotalPitsPerPlayer = 6;
    private int totalStones = 4;
    private BoardMember neighbour;
    private BoardMember oppositeBoardMember;
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
        } else if (instancesOfPitsCreated == TotalPitsPerPlayer+1 ) {
            neighbour = new Pit(firstPit, instancesOfPitsCreated+1, firstPlayer.getOpponent());//First Kalaha created. Creates new pits with opponent as their owner
        } else if (instancesOfPitsCreated == (TotalPitsPerPlayer+1)*2 ) {
            neighbour = firstPit; //Reached Second Kalaha, stop chain and link kalaha to first pit
        } else {
            neighbour = new Pit(firstPit, instancesOfPitsCreated+1, firstPlayer);
        }
    }
    

    /** Getters **/
    public int getTotalStones(){
        return totalStones;
    }
    public Contestant getOwner(){
        return owner;
    }     
    
    public BoardMember getNeighbour(){
        return neighbour; 
    }
    
    public BoardMember getOpposingBoardMember(){
        BoardMember myNeighboursOpposingBoardMember = neighbour.getOpposingBoardMember();
        return myNeighboursOpposingBoardMember.getNeighbour();
    }
    
    public BoardMember getActiveKalaha(){
        BoardMember isThisAKalaha = getNeighbour();
        while (isThisAKalaha.getClass().getSimpleName().equalsIgnoreCase("Kalaha") == false){
            isThisAKalaha = isThisAKalaha.getNeighbour();
        }
        return isThisAKalaha;
    }
    
    /** all game play methods **/
    void takeAndPassStones(int stones) {
        if (stones == 1) {
            totalStones++; //ends move
            if (totalStones == 1) {
                BoardMember foundTheActiveKalaha = getActiveKalaha();
                captureMe(foundTheActiveKalaha);
                getOpposingBoardMember().captureMe(foundTheActiveKalaha);
            }
        } else {
            totalStones++;
            neighbour.takeAndPassStones((stones-1));
        }
    }
    
    void emptyStones(){
        totalStones = 0;
    }
    
    void captureMe(BoardMember activeKalaha){
        int stonesToGiveAway = totalStones;
        activeKalaha.receiveStones(stonesToGiveAway);
        emptyStones();
    }
    
    void receiveStones(int stonesReceived){
        totalStones += stonesReceived;
    }
    
}