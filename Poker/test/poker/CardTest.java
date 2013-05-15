/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class CardTest
{
    
    @Before
    public void setUp()
    {
    }
    
    @Test
    public void constructorWorks()
    {
        Card c = new Card("Ad");
        assertEquals(c.getRank(), Rank.ACE);
    }
    
}
