
package poker.deck;

import poker.deck.Deck;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DeckTest
{
    private Deck deck;
    
    @Before
    public void setUp()
    {
        deck = new Deck();
    }
    @Test
    public void newDeckHas52cards()
    {
        assertEquals(52, deck.size());
    }
    
    @Test
    public void deckSizeIs51afterDealingOneCard()
    {
        deck.dealCard();
        assertEquals(51, deck.size());
    }
    
    @Test
    public void successfullyRemovingACardReturnsTrue()            
    {
        Card c = new Card("As");
        assertTrue(deck.remove(c));      
    }
    
    @Test
    public void attemptingToRemoveACardNotInDeckReturnsFalse()
    {
        Card c = new Card("Kh");
        deck.remove(c);
        assertFalse(deck.remove(c));
    }

    @Test
    public void deckNotInExactSameOrderAfterShuffling()
    {
        Deck deck2 = new Deck();
        deck.shuffle();
        int cardsInSamePosition = 0;
        for (int i = 0; i < 52; i++)
        {
            if (deck.dealCard().equals(deck2.dealCard()))
            {
                cardsInSamePosition++;
            }
        }
        assertFalse(cardsInSamePosition == 52);
    }
    
    @Test
    public void deckHas52CardsAfterReset()
    {
        deck.dealCard();
        deck.reset();
        assertEquals(52, deck.size());
    }
}
