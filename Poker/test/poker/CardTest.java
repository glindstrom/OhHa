/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public void RankAceMatchesConstructorUsingStringArgument()
    {
        Card c = new Card("Ad");
        assertEquals(Rank.ACE, c.getRank());
    }
    
    
    
//    @Test
//    public void constructorSetsRankKingUsingStringArgument()
//    {
//       Card c = new Card("Kd");
//       assertEquals(Rank.KING, c.getRank());
//        
//    }
    
}
