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
    public void constructorInitsRanksCorrectly()
    {
        Card c;
        for (Rank rank : Rank.values())
        {
            c = new Card(rank, Suit.HEARTS);
            assertEquals(rank, c.getRank());
        }                 
    }
            
    @Test
    public void constructorInitsSuitsCorrectly()
    {
        Card c;
        for (Suit suit : Suit.values())
        {
            c = new Card(Rank.ACE, suit);
            assertEquals(suit, c.getSuit());
        }
    }
    
    @Test
    public void constructorSetsRankAceUsingStringArgument()
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
