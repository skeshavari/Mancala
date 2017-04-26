package nl.sogyo.mancala;

public class Contestant {
    private Contestant opponent;
    
    public Contestant(){
        opponent = new Contestant(this);
    }
    
    public Contestant(Contestant firstOpponent){
        opponent = firstOpponent;
    }

    public Contestant getOpponent(){
        return opponent;
    } 
}
