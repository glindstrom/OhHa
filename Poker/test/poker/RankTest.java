/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import static org.junit.Assert.*;
import org.junit.Test;

public class RankTest
{    
    
    @Test
    public void ranksCorrespondToChars()
    {
        assertEquals(Rank.TWO, Rank.charToRank('2'));
        assertEquals(Rank.THREE, Rank.charToRank('3'));
        assertEquals(Rank.FOUR, Rank.charToRank('4'));
        assertEquals(Rank.FIVE, Rank.charToRank('5'));
        assertEquals(Rank.SIX, Rank.charToRank('6'));
        assertEquals(Rank.SEVEN, Rank.charToRank('7'));
        assertEquals(Rank.EIGHT, Rank.charToRank('8'));
        assertEquals(Rank.NINE, Rank.charToRank('9'));
        assertEquals(Rank.TEN, Rank.charToRank('T'));
        assertEquals(Rank.JACK, Rank.charToRank('J'));
        assertEquals(Rank.QUEEN, Rank.charToRank('Q'));
        assertEquals(Rank.KING, Rank.charToRank('K'));
        assertEquals(Rank.ACE, Rank.charToRank('A'));                
    }
    
    @Test
    public void charToRankWorksWithLowercaseLetter()
    {
        assertEquals(Rank.ACE, Rank.charToRank('a'));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void chartToRankThrowsException()
    {
        Rank.charToRank('w');
    }
}
