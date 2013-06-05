
package poker.deck;
/**
 * This enum type represents the suit of a Card.
 * @see Card
 */
public enum Suit
{
    SPADES('s'), 
    HEARTS('h'), 
    DIAMONDS('d'), 
    CLUBS('c');
    
    private char abbreviation;
    
    private Suit(char abbreviation)
    {
        this.abbreviation = abbreviation;
    }
    
    /**
     * Returns the Suit that matches a character abbreviation.
     * @param c A character representing a suit.
     * @return The suit corresponding to the character c.
     * @throws IllegalArgumentException if c not in "CDHS" (case insensitive)
     */
    public static Suit charToSuit(char c)
    {
        for (Suit suit : Suit.values())
        {
            if (suit.abbreviation == Character.toLowerCase(c))
            {
                return suit;
            }
        }
        throw new IllegalArgumentException();
    }

    public char getAbbreviation()
    {
        return abbreviation;
    }
    
    
}
