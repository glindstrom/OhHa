/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package poker;


class Card 
{
    private Rank rank;
    
    public Card(String card)
    {
        this.rank = Rank.ACE;
        
    }
    
    public Rank getRank()
    {
        return this.rank;
    }
    
    

}
