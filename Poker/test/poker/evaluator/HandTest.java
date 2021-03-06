package poker.evaluator;

import poker.evaluator.Hand;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.internal.builders.NullBuilder;
import poker.deck.Card;

public class HandTest
{
    @Test
    public void addingCardSuccessfullyReturnsTrue()
    {
        Hand h = new Hand();
        assertTrue(h.addCard(new Card("5d")));
    }

    @Test
    public void addingMoreThanSevenCardsReturnsFalse()
    {
        Hand h = new Hand(new Card("7h"), new Card("7d"), new Card("7c"), new Card("5s"), new Card("2h"), new Card("5h"), new Card("9c"));
        Card c = new Card("As");
        assertFalse(h.addCard(c));
    }
    
    @Test
    public void addedCardIsInHand()
    {
        Hand h = new Hand();
        Card c = new Card("4h");
        h.addCard(c);
        assertTrue(h.getCards().contains(c));
    }

    @Test
    public void cardInHandMatchesConstructor()
    {
        Card c = new Card("9c");
        Hand h = new Hand(c);
        assertTrue(h.getCards().contains(c));
    }
    
    @Test
    public void cardInHandMatchesConstructorUsingStringParameter()
    {
        Hand h = new Hand("3cAs3d5h6s");
        Card c = new Card("3c");
        assertTrue(h.getCards().contains(c));        
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void moreThanSevenCardsInConstructorCausesException()
    {
        Hand h = new Hand(new Card("7h"), new Card("7d"), new Card("7c"), new Card("5s"), new Card("2h"), new Card("5h"), new Card("9c"), new Card("Qh"));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void moreThanSevenCardsInStringConstructorCausesException()
    {
        Hand h = new Hand("7sAc6h3dKc9d8sJh");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void addingSameCardTwiceCausesException()
    {
        Hand h = new Hand("6sKc4dKc5s");
    }
    
    @Test (expected = NullPointerException.class)
    public void constructorThrowsNullPointerException()
    {
        Card c = null;
        Hand h = new Hand(c);
    }
    
    @Test
    public void handContainsAllCardsFromBothHandAfterMerging()
    {
        Hand h1 = new Hand("Ac5d3sKdJh");
        Hand h2 = new Hand("KcTh");
        Hand h3 = Hand.mergeHands(h1, h2);
        for (Card c : h1)
        {
            assertTrue(h3.getCards().contains(c));
        }
        for (Card c : h2)
        {
            assertTrue(h3.getCards().contains(c));
        }
    }
    
    @Test (expected = NullPointerException.class)
    public void mergeHandsThrowsNullPointerException()
    {
        Hand.mergeHands(null, null);
    }
}
