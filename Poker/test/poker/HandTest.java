
package poker;

import static org.junit.Assert.*;
import org.junit.Test;

public class HandTest
{    
    @Test
    public void addingCardSuccessfullyReturnsTrue()
    {
        Hand h = new Hand();
        assertTrue(h.addCard(new Card("5d")));
    }
    
    @Test
    public void addingMoreThanFiveCardsReturnsFalse()
    {
        Hand h = new Hand();
        addFiveCardsToHand(h);
        Card c6 = new Card("As");
        assertFalse(h.addCard(c6));
    }

    private void addFiveCardsToHand(Hand h)
    {
        Card c1 = new Card("7h");
        Card c2 = new Card("3c");
        Card c3 = new Card("Jh");
        Card c4 = new Card("Qs");
        Card c5 = new Card("Kc");        
        h.addCard(c1);
        h.addCard(c2);
        h.addCard(c3);
        h.addCard(c4);
        h.addCard(c5);
    }
}
