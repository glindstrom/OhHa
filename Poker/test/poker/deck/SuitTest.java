
package poker.deck;

import poker.deck.Suit;
import static org.junit.Assert.*;
import org.junit.Test;

public class SuitTest
{
    @Test
    public void ranksCorrespondToChars()
    {
        assertEquals(Suit.CLUBS, Suit.charToSuit('C'));
        assertEquals(Suit.DIAMONDS, Suit.charToSuit('D'));
        assertEquals(Suit.HEARTS, Suit.charToSuit('H'));
        assertEquals(Suit.SPADES, Suit.charToSuit('S'));
    }
    
    @Test
    public void charToSuitWorksWithLowercaseLetter()
    {
        assertEquals(Suit.HEARTS, Suit.charToSuit('h'));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void chartToSuitThrowsException()
    {
        Suit.charToSuit('w');
    }

}
