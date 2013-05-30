/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.handCategories;

import static org.junit.Assert.*;
import org.junit.Test;
import poker.deck.Card;
import poker.Hand;

/**
 *
 * @author Gabriel
 */
public class HandCategoryTest
{
    
    public HandCategoryTest()
    {
    }
    
    @Test
    public void handValueIsFourOfAKind()
    {
        Hand h = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("Qs"), new Card("2h"));
        HandCategory hc = HandCategory.extractHandCategory(h);
        assertEquals(Quads.class, hc.getClass());
    }
    
    @Test
    public void handValueIsFullHouse()
    {
        Hand h = new Hand(new Card("7s"), new Card("7h"), new Card("7c"), new Card("5c"), new Card("5d"));
        HandCategory hc = HandCategory.extractHandCategory(h);
        assertEquals(FullHouse.class, hc.getClass());
    }

    @Test
    public void handValueIsThreeOfAKind()
    {
        Hand h = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("5s"), new Card("2h"));
        HandCategory hc = HandCategory.extractHandCategory(h);
        assertEquals(Trips.class, hc.getClass());
    }

    @Test
    public void handValueIsTwoPair()
    {
        Hand h = new Hand(new Card("Qh"), new Card("Qd"), new Card("5c"), new Card("5s"), new Card("2h"));
        HandCategory hc = HandCategory.extractHandCategory(h);
        assertEquals(TwoPair.class, hc.getClass());
    }

    @Test
    public void handValueIsOnePair()
    {
        Hand h = new Hand(new Card("Qh"), new Card("Qd"), new Card("5c"), new Card("7s"), new Card("2h"));
        HandCategory hc = HandCategory.extractHandCategory(h);
        assertEquals(OnePair.class, hc.getClass());
    }

    @Test
    public void handValueIsFlush()
    {
        Hand h = new Hand(new Card("Ah"), new Card("Kh"), new Card("Th"), new Card("8h"), new Card("2h"));
        HandCategory hc = HandCategory.extractHandCategory(h);
        assertEquals(Flush.class, hc.getClass());
    }

    @Test
    public void handValueIsStraight()
    {
        Hand h = new Hand(new Card("7h"), new Card("Ts"), new Card("9h"), new Card("8h"), new Card("Jh"));
        HandCategory hc = HandCategory.extractHandCategory(h);
        assertEquals(Straight.class, hc.getClass());
    }
    
    @Test
    public void handValueIsStraightWhenAceIsLowestCard()
    {
        Hand h = new Hand(new Card("Ad"), new Card("4s"), new Card("5h"), new Card("3h"), new Card("2c"));
        HandCategory hc = HandCategory.extractHandCategory(h);
        assertEquals(Straight.class, hc.getClass());
    }

    @Test
    public void handValueIsStraightFlush()
    {
        Hand h = new Hand(new Card("7h"), new Card("Th"), new Card("9h"), new Card("8h"), new Card("Jh"));
        HandCategory hc = HandCategory.extractHandCategory(h);
        assertEquals(StraightFlush.class, hc.getClass());
    }

    @Test
    public void handValueIsHighCard()
    {
        Hand h = new Hand(new Card("7h"), new Card("Th"), new Card("2c"), new Card("8h"), new Card("Jh"));
        HandCategory hc = HandCategory.extractHandCategory(h);
        assertEquals(HighCard.class, hc.getClass());
    }
}
