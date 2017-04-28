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
    
        
    public BoardMember findFirstPitOfActivePlayer(){
        BoardMember searchFromThisBoardMember = this;
        boolean inactiveKalahaReached = false;
        String BoardMemberType;
        while (inactiveKalahaReached == false){
            BoardMemberType = searchFromThisBoardMember.getClass().getSimpleName();
            
            if (BoardMemberType.equalsIgnoreCase("Kalaha") == true && searchFromThisBoardMember.getOwner().getIsActiveTurn() == false) {
                inactiveKalahaReached = true;
            } else {
                 searchFromThisBoardMember = searchFromThisBoardMember.getNeighbour();
            }
        }
        return searchFromThisBoardMember.getNeighbour();
    }
    
    /** all game play methods **/
    void takeAndPassStones(int stonesToPassOn) {
        if (stonesToPassOn == 1) {
            totalStones++;
            if (totalStones == 1 && getOwner().getIsActiveTurn() == true) {
                BoardMember foundTheActiveKalaha = getActiveKalaha();
                captureMe(foundTheActiveKalaha);
                getOpposingBoardMember().captureMe(foundTheActiveKalaha);
            }
            owner.switchIsActiveTurn();
            owner.getOpponent().switchIsActiveTurn();
            accessGame();

        } else {
            totalStones++;
            neighbour.takeAndPassStones((stonesToPassOn-1));
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

    
    void accessGame(){
        int TotalStonesOnPlayerInPits = 0;
        BoardMember loopUntilTheKalaha = findFirstPitOfActivePlayer();
        //BoardMember loopUntilTheKalaha = TheFirstPitOfActivePlayer;
        while (loopUntilTheKalaha.getClass().getSimpleName().equalsIgnoreCase("Kalaha") == false){
            TotalStonesOnPlayerInPits += loopUntilTheKalaha.getTotalStones();
            loopUntilTheKalaha = loopUntilTheKalaha.getNeighbour();
        }
        
        if (TotalStonesOnPlayerInPits == 0){
            sweepRemainingStonesToKalaha(loopUntilTheKalaha);
            System.out.println("YOU HAVE FINISHED THE GAME");  //NEEDS TO IMPLEMENT SWEEP
            //System.exit.(0);
        }

    }
    
    void sweepRemainingStonesToKalaha(BoardMember theActivePlayersKalaha){
        int TotalStonesOnPlayerInPits = 0;
        BoardMember loopUntilTheKalaha = theActivePlayersKalaha.getNeighbour();
        while (loopUntilTheKalaha.getClass().getSimpleName().equalsIgnoreCase("Kalaha") == false){
            TotalStonesOnPlayerInPits += loopUntilTheKalaha.getTotalStones();
            loopUntilTheKalaha.emptyStones();
            loopUntilTheKalaha = loopUntilTheKalaha.getNeighbour();
        }
        loopUntilTheKalaha.receiveStones(TotalStonesOnPlayerInPits);
        
    }
    
}