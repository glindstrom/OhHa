/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;


public enum Suit
{
    CLUBS('C'), DIAMONDS('D'), HEARTS('H'), SPADES('S');
    
    private char abbreviation;
    
    private Suit(char abbreviation)
    {
        this.abbreviation = abbreviation;
    }
    
    public static Suit charToSuit(char c)
    {
        for (Suit suit : Suit.values())
        {
            if (suit.abbreviation == Character.toUpperCase(c))
            {
                return suit;
            }
        }
        throw new IllegalArgumentException();
    }
}
