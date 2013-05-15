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
    public void RanksCorrespondToChars()
    {
        String ranks = "AKQJT98765432";
        int i = 0;
        for (Rank rank : Rank.values())
        {
            assertEquals(rank, Rank.returnRank(ranks.charAt(i)));
            i++;
        }
    }
}
