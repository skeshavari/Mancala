package nl.sogyo.mancala;
import org.junit.*;

public class MancalaTest {
    
    @Test
    public void testIfNewPitHasFourStones() {
        Pit firstOne = new Pit();
        int result = firstOne.getTotalStones();
        int expResult = 4;
        Assert.assertEquals(expResult, result);
    }
        @Test
    public void testIfPitPassesStonesOnAndSetsOwnStonesOnZero() {
        Pit firstOne = new Pit();
        firstOne.pickAndPlayPit();
        int result = firstOne.getTotalStones();
        int expResult = 0;
        Assert.assertEquals(expResult, result);
    }
    
    @Test
    public void testIfNeighbourReceivesStonesAfterMethodpickAndPlayPit() {
        Pit firstOne = new Pit();
        firstOne.pickAndPlayPit();
        int result = firstOne.getNeighbour().getTotalStones();
        int expResult = 5;
        Assert.assertEquals(expResult, result);
    }
    
    @Test
    public void testIfLastFirstKalahaIsEmpty() {
        Pit firstOne = new Pit();
        firstOne.pickAndPlayPit();
        int stonesInKalahaShouldBeOne = firstOne.getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getTotalStones();
        Assert.assertEquals(0, stonesInKalahaShouldBeOne);
    }
     
    @Test
    public void testIfPitKnowsItsOwner() {
        Pit firstOne = new Pit();
        //firstOne.pickAndPlayPit();
        Contestant playerOfFirstOne = firstOne.getOwner();
        Assert.assertNotNull(playerOfFirstOne);
    }    
    
    @Test
    public void testIfPitOwnerKnowsItsOpponent() {
        Pit firstOne = new Pit();
        //firstOne.pickAndPlayPit();
        Contestant opponentOfFirstOneOwner = firstOne.getOwner().getOpponent();
        Assert.assertNotNull(opponentOfFirstOneOwner);
    }
    
    @Test
    public void testIfSecondKalahaIsEmpty() {
        Pit firstOne = new Pit();
        firstOne.pickAndPlayPit();
        int stonesInKalahaShouldBeOne = firstOne.getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getTotalStones();
        Assert.assertEquals(0, stonesInKalahaShouldBeOne);
    }
    
}
