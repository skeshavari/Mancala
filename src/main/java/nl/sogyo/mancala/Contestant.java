package nl.sogyo.mancala;

public class Contestant {
    private Contestant opponent;
    private boolean isActiveTurn;
    
    public Contestant(){
        isActiveTurn = true;
        opponent = new Contestant(this);
    }
    
    public Contestant(Contestant firstOpponent){
        isActiveTurn = false;
        opponent = firstOpponent;
    }

    public Contestant getOpponent(){
        return opponent;
    }
    
    public boolean getIsActiveTurn(){
        return isActiveTurn;
    }
    
    public void switchTurnToOpponent(){
        isActiveTurn = !isActiveTurn;
        getOpponent().isActiveTurn = !getOpponent().isActiveTurn;
    }
}
