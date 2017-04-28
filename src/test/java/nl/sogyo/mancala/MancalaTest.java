package nl.sogyo.mancala;
import org.junit.*;

public class MancalaTest {
    Pit pit1;
    Pit pit2;
    Pit pit3;
    Pit pit4;
    Pit pit5;
    Pit pit6;
    Kalaha kalaha1;
    Pit pit8;
    Pit pit9;
    Pit pit10;
    Pit pit11;
    Pit pit12;
    Pit pit13;
    Kalaha kalaha2;

    @Before
    public void SetUp()
    {
        pit1 = new Pit();
        pit2 = (Pit) pit1.getNeighbour();
        pit3 = (Pit) pit2.getNeighbour();
        pit4 = (Pit) pit3.getNeighbour();
        pit5 = (Pit) pit4.getNeighbour();
        pit6 = (Pit) pit5.getNeighbour();
        kalaha1 = (Kalaha) pit6.getNeighbour();
        pit8 = (Pit) kalaha1.getNeighbour();
        pit9 = (Pit) pit8.getNeighbour();
        pit10 = (Pit) pit9.getNeighbour();
        pit11 = (Pit) pit10.getNeighbour();
        pit12 = (Pit) pit11.getNeighbour();
        pit13 = (Pit) pit12.getNeighbour();
        kalaha2 = (Kalaha) pit13.getNeighbour();
    }
    
    @Test
    public void testIfNewPitHasFourStones() {
        int result = pit1.getTotalStones();
        int expResult = 4;
        Assert.assertEquals(expResult, result);
    }
    
    @Test
    public void testIfPitPassesStonesOnAndSetsOwnStonesOnZero() {
        pit1.pickAndPlayPit();
        int result = pit1.getTotalStones();
        int expResult = 0;
        Assert.assertEquals(expResult, result);
    }
    
    @Test
    public void testIfNeighbourReceivesStonesAfterMethodpickAndPlayPit() {
        pit1.pickAndPlayPit();
        int result = pit2.getTotalStones();
        int expResult = 5;
        Assert.assertEquals(expResult, result);
    }
    
    @Test
    public void testIfLastFirstKalahaIsEmpty() {
        pit1.pickAndPlayPit();
        int stonesInKalahaShouldBeOne = kalaha1.getTotalStones();
        Assert.assertEquals(0, stonesInKalahaShouldBeOne);
    }
     
    @Test
    public void testIfPitKnowsItsOwner() {
        Contestant playerOfFirstPit = pit1.getOwner();
        Assert.assertNotNull(playerOfFirstPit);
    }    
    
    @Test
    public void testIfPitOwnerKnowsItsOpponent() {
        Contestant opponentOfFirstOneOwner = pit1.getOwner().getOpponent();
        Assert.assertNotNull(opponentOfFirstOneOwner);
    }
    
    @Test
    public void testIfSecondKalahaIsEmpty() {
        pit1.pickAndPlayPit();
        int stonesInKalahaShouldBeNone = kalaha2.getTotalStones();
        Assert.assertEquals(0, stonesInKalahaShouldBeNone);
    }
    
    @Test
    public void testIfFirstPitIs14thPit() {
        Assert.assertEquals(pit1, kalaha2.getNeighbour());
    }
    
    @Test
    public void testIfFirstSIxPitsAndKalahaHaveSameOwner() {
        Assert.assertEquals(pit1.getOwner(), pit2.getOwner());
        Assert.assertEquals(pit2.getOwner(), pit3.getOwner());
        Assert.assertEquals(pit3.getOwner(), pit4.getOwner());
        Assert.assertEquals(pit4.getOwner(), pit5.getOwner());
        Assert.assertEquals(pit5.getOwner(), pit6.getOwner());
        Assert.assertEquals(pit6.getOwner(), kalaha1.getOwner());
        Assert.assertNotEquals(kalaha1.getOwner(), kalaha1.getNeighbour().getOwner());
        
    }
    
    @Test
    public void testIfSecondSIxPitsAndKalahaHaveSameOwner() {
        Assert.assertEquals(pit8.getOwner(), pit9.getOwner());
        Assert.assertEquals(pit9.getOwner(), pit10.getOwner());
        Assert.assertEquals(pit10.getOwner(), pit11.getOwner());
        Assert.assertEquals(pit11.getOwner(), pit12.getOwner());
        Assert.assertEquals(pit12.getOwner(), pit13.getOwner());
        Assert.assertEquals(pit13.getOwner(), kalaha2.getOwner());
        Assert.assertNotEquals(kalaha2.getOwner(), kalaha2.getNeighbour().getOwner());
    }
    
    @Test
    public void testIfKalahaGetsOneStoneAfterPlayingPitFive() {
        pit5.pickAndPlayPit();
        Assert.assertEquals(1, kalaha1.getTotalStones());
    }
    
    @Test
    public void testIfPitTwoCanFindOppositeField() {
        Assert.assertEquals(pit2.getOpposingBoardMember(), pit12);
        Assert.assertEquals(pit1.getOpposingBoardMember(), pit13);
        Assert.assertEquals(kalaha2.getOpposingBoardMember(), kalaha2);
    }
    
    @Test
    public void testIfPitFourCanCaptureOpposingBoardMember() {
        pit5.emptyStones();
        pit1.pickAndPlayPit();
        Assert.assertEquals(0, pit5.getOpposingBoardMember().getTotalStones());
    }
    
    @Test
    public void testIfKalahaOneReceivesStonesFromCapturedMembers() {
        int stonesReceivedByKalahaOneShouldBe = 5;
        pit5.emptyStones();
        pit1.pickAndPlayPit();
        Assert.assertEquals(stonesReceivedByKalahaOneShouldBe, kalaha1.getTotalStones());
    }
    
    @Test
    public void testIfKalahaTwoReceivesNoStonesWhenPlayerOneIsActive() {
        int stonesHeldByKalahaTwoShouldBe = 0;
        pit6.receiveStones(10);
        pit6.pickAndPlayPit();
        Assert.assertEquals(stonesHeldByKalahaTwoShouldBe, kalaha2.getTotalStones());
    }
}
