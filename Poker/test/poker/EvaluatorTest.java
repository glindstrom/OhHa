package poker;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
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
    public void handValueIsFourOfAKind()
    {
        Hand h = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("Qs"), new Card("2h"));
        HandCategory hc = e.handCategory(h);
        assertEquals(Quads.class, hc.getClass());
    }
    
    @Test
    public void handValueIsFullHouse()
    {
        Hand h = new Hand(new Card("7s"), new Card("7h"), new Card("7c"), new Card("5c"), new Card("5d"));
        HandCategory hc = e.handCategory(h);
        assertEquals(FullHouse.class, hc.getClass());
    }

    @Test
    public void handValueIsThreeOfAKind()
    {
        Hand h = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("5s"), new Card("2h"));
        HandCategory hc = e.handCategory(h);
        assertEquals(ThreeOfAKind.class, hc.getClass());
    }

    @Test
    public void handValueIsTwoPair()
    {
        Hand h = new Hand(new Card("Qh"), new Card("Qd"), new Card("5c"), new Card("5s"), new Card("2h"));
        HandCategory hc = e.handCategory(h);
        assertEquals(TwoPair.class, hc.getClass());
    }

    @Test
    public void handValueIsOnePair()
    {
        Hand h = new Hand(new Card("Qh"), new Card("Qd"), new Card("5c"), new Card("7s"), new Card("2h"));
        HandCategory hc = e.handCategory(h);
        assertEquals(OnePair.class, hc.getClass());
    }

    @Test
    public void handValueIsFlush()
    {
        Hand h = new Hand(new Card("Ah"), new Card("Kh"), new Card("Th"), new Card("8h"), new Card("2h"));
        HandCategory hc = e.handCategory(h);
        assertEquals(Flush.class, hc.getClass());
    }

    @Test
    public void handValueIsStraight()
    {
        Hand h = new Hand(new Card("7h"), new Card("Ts"), new Card("9h"), new Card("8h"), new Card("Jh"));
        HandCategory hc = e.handCategory(h);
        assertEquals(Straight.class, hc.getClass());
    }
    
    @Test
    public void handValueIsStraightWhenAceIsLowestCard()
    {
        Hand h = new Hand(new Card("Ad"), new Card("4s"), new Card("5h"), new Card("3h"), new Card("2c"));
        HandCategory hc = e.handCategory(h);
        assertEquals(Straight.class, hc.getClass());
    }

    @Test
    public void handValueIsStraightFlush()
    {
        Hand h = new Hand(new Card("7h"), new Card("Th"), new Card("9h"), new Card("8h"), new Card("Jh"));
        HandCategory hc = e.handCategory(h);
        assertEquals(StraightFlush.class, hc.getClass());
    }

    @Test
    public void handValueIsHighCard()
    {
        Hand h = new Hand(new Card("7h"), new Card("Th"), new Card("2c"), new Card("8h"), new Card("Jh"));
        HandCategory hc = e.handCategory(h);
        assertEquals(HighCard.class, hc.getClass());
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
        assertEquals(2, e.compareHands(h2, h1));
    }
    
    @Test
    public void higherFourOfAKindBeatsLowerFourOfAKind()
    {
        Hand h1 = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("Qs"), new Card("2h"));
        Hand h2 = new Hand(new Card("Th"), new Card("Td"), new Card("Tc"), new Card("Ts"), new Card("2h"));
        assertEquals(1, e.compareHands(h1, h2));
    }
    

}
