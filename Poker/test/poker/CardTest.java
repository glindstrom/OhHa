
package poker;


import static org.junit.Assert.*;
import org.junit.Test;


public class CardTest
{        
    @Test
    public void rankMatchesConstructor()
    {
        Card c;
        for (Rank rank : Rank.values())
        {
            c = new Card(rank, Suit.HEARTS);
            assertEquals(rank, c.getRank());
        }                 
    }
            
    @Test
    public void suitMatchesConstructor()
    {
        Card c;
        for (Suit suit : Suit.values())
        {
            c = new Card(Rank.ACE, suit);
            assertEquals(suit, c.getSuit());
        }
    }
    
    @Test
    public void rankAceMatchesConstructorUsingStringArgument()
    {
        Card c = new Card("Ad");
        assertEquals(Rank.ACE, c.getRank());
    }
            
    @Test
    public void rankKingMatchesConstructorUsingStringArgument()
    {
       Card c = new Card("Kd");
       assertEquals(Rank.KING, c.getRank());        
    }
    
    @Test
    public void suitHeartMatchesConstructorUsingStringArgument()
    {
        Card c = new Card("5H");
        assertEquals(Suit.HEARTS, c.getSuit());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionWhenArgumentLongerThanTwo()
    {
        Card c = new Card("TCC");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionWhenArgumentShorterThanTwo()
    {
        Card c = new Card("6");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionWhenIncorrectArgument()
    {
        Card c = new Card("W6");
    }
    
    
}
