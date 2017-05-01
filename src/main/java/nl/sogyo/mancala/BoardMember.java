package nl.sogyo.mancala;

public abstract class BoardMember {
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
        } else if (instancesOfPitsCreated == TotalPitsPerPlayer+1 ) {
            //First Kalaha created. Creates new pits with opponent as their owner
            neighbour = new Pit(firstPit, instancesOfPitsCreated+1, firstPlayer.getOpponent());
        } else if (instancesOfPitsCreated == (TotalPitsPerPlayer+1)*2 ) {
             //Reached Second Kalaha, stop chain and link kalaha to first pit
            neighbour = firstPit;
        } else {
            neighbour = new Pit(firstPit, instancesOfPitsCreated+1, firstPlayer);
        }
    }
    
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
        BoardMember searchingForKalaha = getNeighbour();
        while (getMyClass(searchingForKalaha).equalsIgnoreCase("Kalaha") == false){
            searchingForKalaha = searchingForKalaha.getNeighbour();
        }
        return searchingForKalaha;
    }
     
    private String getMyClass(BoardMember toCheckForType){
        return toCheckForType.getClass().getSimpleName();
    }      
    
    public BoardMember findFirstPitOfActivePlayer(){
        BoardMember searchFromBoardMember = this;
        boolean inactiveKalahaReached = false;
        String BoardMemberType;
        while (inactiveKalahaReached == false){
            BoardMemberType = getMyClass(searchFromBoardMember);
            
            if (BoardMemberType.equalsIgnoreCase("Kalaha") == true && ownerIsNOTActive(searchFromBoardMember)) {
                inactiveKalahaReached = true;
            } else {
                 searchFromBoardMember = searchFromBoardMember.getNeighbour();
            }
        }
        return searchFromBoardMember.getNeighbour();
    }
         
    private boolean ownerIsNOTActive(BoardMember toCheckForActiveState){
        if (toCheckForActiveState.getOwner().getIsActiveTurn() == false) {
            return true;
        }
        return false;
    }

    void takeAndPassStones(int stonesToPassOn) {
        totalStones++;
        stonesToPassOn--;
        if (stonesToPassOn == 0) {
           endTurnSequence();
        } else {
            neighbour.takeAndPassStones(stonesToPassOn);
        }
    }
         
    void endTurnSequence(){
        if (wasEmpty() && ownerIsActive()) {
            BoardMember foundTheActiveKalaha = getActiveKalaha();
            captureMe(foundTheActiveKalaha);
            getOpposingBoardMember().captureMe(foundTheActiveKalaha);
        }
        
        owner.switchIsActiveTurn();
        owner.getOpponent().switchIsActiveTurn();
        accessGame();
    }
    
    private boolean wasEmpty(){
        if (totalStones == 1) {
        return true;
        }
        return false;
    }
    
    public boolean ownerIsActive(){
        if (getOwner().getIsActiveTurn() == true) {
            return true;
        }
        return false;
    }
    
    void captureMe(BoardMember activeKalaha){
        int stonesToGiveAway = totalStones;
        activeKalaha.receiveStones(stonesToGiveAway);
        emptyStones();
    }
    
    void receiveStones(int stonesReceived){
        totalStones += stonesReceived;
    }        
    void emptyStones(){
        totalStones = 0;
    }
    
    void accessGame(){
        int TotalStonesOfPlayerInPits = 0;
        BoardMember firstPitUntilKalahaOfActivePlayer = findFirstPitOfActivePlayer();
        while (getMyClass(firstPitUntilKalahaOfActivePlayer).equalsIgnoreCase("Kalaha") == false){
            TotalStonesOfPlayerInPits += firstPitUntilKalahaOfActivePlayer.getTotalStones();
            firstPitUntilKalahaOfActivePlayer = firstPitUntilKalahaOfActivePlayer.getNeighbour();
        }
        
        if (TotalStonesOfPlayerInPits == 0){
            sweepRemainingStonesToKalaha(firstPitUntilKalahaOfActivePlayer);
            System.out.println("THE GAME IS OVER");
        }
    }
    
    void sweepRemainingStonesToKalaha(BoardMember theActivePlayersKalaha){
        int TotalStonesLeft = 0;
        BoardMember loopUntilTheInactiveKalaha = theActivePlayersKalaha.getNeighbour();
        while (getMyClass(loopUntilTheInactiveKalaha).equalsIgnoreCase("Kalaha") == false){
            TotalStonesLeft += loopUntilTheInactiveKalaha.getTotalStones();
            loopUntilTheInactiveKalaha.emptyStones();
            loopUntilTheInactiveKalaha = loopUntilTheInactiveKalaha.getNeighbour();
        }
        
        loopUntilTheInactiveKalaha.receiveStones(TotalStonesLeft);
        decideVictoriousPlayer(loopUntilTheInactiveKalaha);
    }
    
    void decideVictoriousPlayer(BoardMember kalahaOfInactivePlayer){
        int totalStonesOfInactivePlayer = kalahaOfInactivePlayer.getTotalStones();
        BoardMember findingActivePlayersKalaha = kalahaOfInactivePlayer.getActiveKalaha();
        int totalStonesOfActivePlayer = findingActivePlayersKalaha.getTotalStones();
        String playerName;
        
        System.out.println(); //for new line 
        
        if (totalStonesOfInactivePlayer > totalStonesOfActivePlayer) {
            playerName = getThePlayerName(kalahaOfInactivePlayer);
            System.out.println(playerName + " has won the game with " + totalStonesOfInactivePlayer + " stones.");
        } else if (totalStonesOfInactivePlayer < totalStonesOfActivePlayer){
             playerName = getThePlayerName(findingActivePlayersKalaha);
            System.out.println(playerName + " has won the game with " + totalStonesOfActivePlayer + " stones.");
        } else {
            System.out.println("We have a draw! No winners or losers..");
        }
    }
    
    private String getThePlayerName (BoardMember whoseOwnerToCheck) {
        return whoseOwnerToCheck.getOwner().getPlayerName();
    }
     void pickAndPlayPit(){
    }
     

}