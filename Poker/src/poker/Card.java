/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package poker;


class Card 
{
    private Rank rank;
    private Suit suit;
    
    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }
    
    public Card(String card)
    {
        if (card.length() != 2)
        {
            throw new IllegalArgumentException();
        }
        this.rank = Rank.charToRank(card.charAt(0));
        this.suit = Suit.charToSuit(card.charAt(1));
    }
    
    public Rank getRank()
    {
        return this.rank;
    }
    
    public Suit getSuit()
    {
        return suit;
    }
    
    

}
