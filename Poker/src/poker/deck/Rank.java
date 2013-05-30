
package poker.deck;

/**
 * This enum type represents the rank of a Card.
 * @see Card
 */
public enum Rank
{
    /**
     * Ordinal value 0. Abbreviation 'A'.
     */
    ACE('A'),
    /**
     * Ordinal value 1. Abbreviation 'K'.
     */
    KING('K'), 
    /**
     * Ordinal value 2. Abbreviation 'Q'.
     */
    QUEEN('Q'), 
    /**
     * Ordinal value 3. Abbreviation 'J'.
     */
    JACK('J'), 
    /**
     * Ordinal value 4. Abbreviation 'T'.
     */
    TEN('T'), 
    /**
     * Ordinal value 5. Abbreviation '9'.
     */
    NINE('9'),
    /**
     * Ordinal value 6. Abbreviation '8'.
     */
    EIGHT('8'),
    /**
     * Ordinal value 7. Abbreviation '7'.
     */
    SEVEN('7'),
    /**
     * Ordinal value 8. Abbreviation '6'.
     */
    SIX('6'), 
    /**
     * Ordinal value 9. Abbreviation '5'.
     */
    FIVE('5'), 
    /**
     * Ordinal value 10. Abbreviation '4'.
     */
    FOUR('4'),
    /**
     * Ordinal value 11. Abbreviation '3'.
     */
    THREE('3'), 
    /**
     * Ordinal value 12. Abbreviation '2'.
     */
    TWO('2');    
    
    private char abbreviation;
    
    private Rank(char abbreviation)
    {
        this.abbreviation = abbreviation;
    }
    
    /**
     * Returns the rank that matches a character abbreviation.
     * @param c A character representing a rank.
     * @return The Rank corresponding to the character c.
     * @throws IllegalArgumentException if c not in "AKQJT98765432" (case insensitive)
     */
    public static Rank charToRank(char c)
    {
        for (Rank rank : Rank.values())
        {
            if (rank.abbreviation == Character.toUpperCase(c))
            {
                return rank;
            }
        }
        throw new IllegalArgumentException();
    }

    public char getAbbreviation()
    {
        return abbreviation;
    }        
}
