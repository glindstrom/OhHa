
package poker;
/**
 * This enum type represents the suit of a Card.
 * @see Card
 */
public enum Suit
{
    /**
     * Ordinal value 0.
     */
    SPADES('S'), 
    /**
     * Ordinal value 1.
     */
    HEARTS('H'), 
    /**
     * Ordinal value 2.
     */
    DIAMONDS('D'), 
    /**
     * Ordinal value 3.
     */
    CLUBS('C');
    
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
            if (suit.abbreviation == Character.toUpperCase(c))
            {
                return suit;
            }
        }
        throw new IllegalArgumentException();
    }
}
