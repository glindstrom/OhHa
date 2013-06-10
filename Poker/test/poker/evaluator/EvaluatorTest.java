package poker.evaluator;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import poker.deck.Card;
import poker.handCategories.*;

public class EvaluatorTest
{
    private Evaluator e;
    @Before
    public void setUp()
    {
        e = new Evaluator();
    }   
    
    @Test
    public void handCategoryRankingIsCorrect()
    {
        Hand straightFlush = new Hand(new Card("7h"), new Card("Th"), new Card("9h"), new Card("8h"), new Card("Jh"));
        Hand fourOfAKind = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("Qs"), new Card("2h"));
        Hand fullHouse = new Hand(new Card("7s"), new Card("7h"), new Card("7c"), new Card("5c"), new Card("5d"));
        Hand flush = new Hand(new Card("Ah"), new Card("Kh"), new Card("Th"), new Card("8h"), new Card("2h"));
        Hand straight = new Hand(new Card("7h"), new Card("Ts"), new Card("9h"), new Card("8h"), new Card("Jh"));
        Hand threeOfAKind = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("5s"), new Card("2h"));
        Hand twoPair = new Hand(new Card("Qh"), new Card("Qd"), new Card("5c"), new Card("5s"), new Card("2h"));
        Hand onePair = new Hand(new Card("Qh"), new Card("Qd"), new Card("5c"), new Card("7s"), new Card("2h"));
        Hand highCard = new Hand(new Card("7h"), new Card("Th"), new Card("2c"), new Card("8h"), new Card("Jh"));
        assertEquals("Straight flush beats Four of a kind", 1, e.compareHands(straightFlush, fourOfAKind));
        assertEquals("Four of a kind beats Full house", 1, e.compareHands(fourOfAKind, fullHouse));
        assertEquals("Full house beats Flush", 1, e.compareHands(fullHouse, flush));
        assertEquals("Flush beats Straight", 1, e.compareHands(flush, straight));
        assertEquals("Straight beats Three of a kind", 1, e.compareHands(straight, threeOfAKind));
        assertEquals("Three of a kind beats Two pair", 1, e.compareHands(threeOfAKind, twoPair));
        assertEquals("Two pair beats one pair", 1, e.compareHands(twoPair, onePair));
        assertEquals("High card loses to one pair", 1, e.compareHands(onePair, highCard));
    }
    
    @Test
    public void higherStraightFlushBeatslowerStraightFlush()
    {
        Hand h1 = new Hand(new Card("7h"), new Card("Th"), new Card("9h"), new Card("8h"), new Card("Jh"));
        Hand h2 = new Hand(new Card("7h"), new Card("Th"), new Card("9h"), new Card("8h"), new Card("6h"));
        assertEquals(1, e.compareHands(h1, h2));
        assertEquals(2, e.compareHands(h2, h1));
    }
    
    @Test
    public void higherFourOfAKindBeatsLowerFourOfAKind()
    {
        Hand h1 = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("Qs"), new Card("2h"));
        Hand h2 = new Hand(new Card("Th"), new Card("Td"), new Card("Tc"), new Card("Ts"), new Card("2h"));
        assertEquals(1, e.compareHands(h1, h2));
        assertEquals(2, e.compareHands(h2, h1));
    }
    
    @Test
    public void higherKickerWinsWhenQuadsOfEqualStrength()
    {
        Hand h1 = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("Qs"), new Card("5h"));
        Hand h2 = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("Qs"), new Card("9c"));
        assertEquals(2, e.compareHands(h1, h2));
        assertEquals(1, e.compareHands(h2, h1));
    }
    
    @Test
    public void fullHouseVsFullHouseHigherTripsWins()
    {
        Hand h1 = new Hand(new Card("7s"), new Card("7h"), new Card("7c"), new Card("5c"), new Card("5d"));
        Hand h2 = new Hand(new Card("8s"), new Card("8h"), new Card("8c"), new Card("5c"), new Card("5d"));
        assertEquals(2, e.compareHands(h1, h2));
        assertEquals(1, e.compareHands(h2, h1));
    }
    
    @Test
    public void fullHouseVsFullHouseHigherPairWinsWhenTripsTie()
    {
        Hand h1 = new Hand(new Card("8s"), new Card("8h"), new Card("8c"), new Card("5c"), new Card("5d"));
        Hand h2 = new Hand(new Card("8s"), new Card("8h"), new Card("8c"), new Card("6c"), new Card("6d"));
        assertEquals(2, e.compareHands(h1, h2));
        assertEquals(1, e.compareHands(h2, h1));
    }
    
    @Test
    public void higherFlushBeatsLowerFlush()
    {
         Hand h1 = new Hand(new Card("Th"), new Card("Kh"), new Card("Ah"), new Card("5h"), new Card("2h")); 
         Hand h2 = new Hand(new Card("7h"), new Card("Kh"), new Card("Th"), new Card("Ah"), new Card("2h")); 
         assertEquals(2, e.compareHands(h1, h2));
         assertEquals(1, e.compareHands(h2, h1));
    }
    
    @Test
    public void higherStraightBeatsLowerStraight()
    {
        Hand h1 = new Hand(new Card("7h"), new Card("Ts"), new Card("9h"), new Card("8h"), new Card("Jh"));
        Hand h2 = new Hand(new Card("Qh"), new Card("Ts"), new Card("9h"), new Card("8h"), new Card("Jh"));
        assertEquals(2, e.compareHands(h1, h2));
        assertEquals(1, e.compareHands(h2, h1));
    }
    
    @Test
    public void aceHighStraightBeatsFiveHighStraight()
    {
        Hand h1 = new Hand(new Card("3h"), new Card("2s"), new Card("Ah"), new Card("5h"), new Card("4h"));
        Hand h2 = new Hand(new Card("Qh"), new Card("Ts"), new Card("Kh"), new Card("Ah"), new Card("Jh"));
        assertEquals(2, e.compareHands(h1, h2));
        assertEquals(1, e.compareHands(h2, h1));        
    }
    
    @Test
    public void higherTripsBeatsLowerTrips()
    {
        Hand h1 = new Hand(new Card("7h"), new Card("7d"), new Card("7c"), new Card("5s"), new Card("2h"));
        Hand h2 = new Hand(new Card("Jh"), new Card("Jd"), new Card("Jc"), new Card("5s"), new Card("2h"));
        assertEquals(2, e.compareHands(h1, h2));
        assertEquals(1, e.compareHands(h2, h1));
    }
    
    @Test
    public void kickerBreaksTieTripsVsTrips()
    {
        Hand h1 = new Hand(new Card("9h"), new Card("9d"), new Card("9c"), new Card("5s"), new Card("2h"));
        Hand h2 = new Hand(new Card("9h"), new Card("9d"), new Card("9c"), new Card("5s"), new Card("3h"));
        assertEquals(2, e.compareHands(h1, h2));
        assertEquals(1, e.compareHands(h2, h1));
    }
    
    @Test
    public void twoPairVsTwoPairHigherPairWins()
    {
        Hand h1 = new Hand(new Card("Qh"), new Card("Qd"), new Card("5c"), new Card("5s"), new Card("2h"));
        Hand h2 = new Hand(new Card("Kh"), new Card("Kd"), new Card("4c"), new Card("4s"), new Card("2h"));
        assertEquals(2, e.compareHands(h1, h2));
        assertEquals(1, e.compareHands(h2, h1));
    }
    
    @Test
    public void twoPairVsTwoPairSecondPairBreaksTie()
    {
        Hand h1 = new Hand(new Card("Kh"), new Card("Kd"), new Card("3c"), new Card("3s"), new Card("2h"));
        Hand h2 = new Hand(new Card("Kh"), new Card("Kd"), new Card("4c"), new Card("4s"), new Card("2h"));
        assertEquals(2, e.compareHands(h1, h2));
        assertEquals(1, e.compareHands(h2, h1));
    }
    
    @Test
    public void twoPairVsTwoPairKickerBreaksTie()
    {
        Hand h1 = new Hand(new Card("Kh"), new Card("Kd"), new Card("3c"), new Card("3s"), new Card("2h"));
        Hand h2 = new Hand(new Card("Kh"), new Card("Ks"), new Card("3c"), new Card("3h"), new Card("8d"));
        assertEquals(2, e.compareHands(h1, h2));
        assertEquals(1, e.compareHands(h2, h1));
    }
    
    @Test
    public void onePairVsOnePairHigherPairWins()
    {
         Hand h1 = new Hand(new Card("Qh"), new Card("Qd"), new Card("5c"), new Card("7s"), new Card("2h"));
         Hand h2 = new Hand(new Card("Kh"), new Card("Kd"), new Card("5c"), new Card("7s"), new Card("2h"));
         assertEquals(2, e.compareHands(h1, h2));
         assertEquals(1, e.compareHands(h2, h1));
    }
    
    @Test 
    public void onePairVsOnePairKickerBreaksTie()
    {
         Hand h1 = new Hand(new Card("Kh"), new Card("Kd"), new Card("5c"), new Card("9s"), new Card("2h"));
         Hand h2 = new Hand(new Card("Kh"), new Card("Kd"), new Card("5c"), new Card("8s"), new Card("2h"));
         assertEquals(1, e.compareHands(h1, h2));
         assertEquals(2, e.compareHands(h2, h1)); 
    }
    
    @Test
    public void betterHighCardWins()
    {
       Hand h1 =  new Hand(new Card("7h"), new Card("Th"), new Card("2c"), new Card("8h"), new Card("Jh"));
       Hand h2 = new Hand(new Card("7h"), new Card("Th"), new Card("Qc"), new Card("8h"), new Card("Jh"));
       assertEquals(2, e.compareHands(h1, h2));
       assertEquals(1, e.compareHands(h2, h1));
    }
    
    @Test
    public void bestFiveCardHandIsExtractedFromSevenCardHand()
    {
        Hand h = new Hand(new Card("Ah"), new Card("Th"), new Card("Js"), new Card("Kc"), new Card("Jh"), new Card("4h"), new Card("Qh"));
        Hand bestHand = e.best5CardHand(h);
        assertEquals(Flush.class, HandCategory.extractHandCategory(bestHand).getClass());
    }
    
    @Test (expected = NullPointerException.class)
    public void best5CardHandThrowsNullPointerException()
    {
        e.best5CardHand(null);
    }
}
