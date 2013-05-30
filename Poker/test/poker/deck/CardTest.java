
package poker.deck;


import poker.deck.Suit;
import poker.deck.Rank;
import poker.deck.Card;
import java.util.ArrayList;
import java.util.Collections;
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
    
    @Test
    public void cardsSortFromHighestRankToLowest()
    {   
        Card c1 = new Card("Kd");
        Card c2 = new Card("Ac");
        Card c3 = new Card("Qs");
        Card c4 = new Card("Kc") ;  
        ArrayList<Card> cards = new ArrayList();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        Collections.sort(cards);
        assertEquals(c2, cards.get(0));
        assertEquals(c1, cards.get(1));
        assertEquals(c4, cards.get(2));
        assertEquals(c3, cards.get(3));
    }
    
    @Test
    public void cardsWithSameRankAndSuitAreEqual()
    {
        Card c1 = new Card("Kd");
        Card c2 = new Card("Kd");
        assertEquals(c1, c2);
    }
}
