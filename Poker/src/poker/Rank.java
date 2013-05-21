
package poker;

/**
 * This enum type represents the rank of a Card.
 * @see Card
 */
public enum Rank
{
    /**
     * Ordinal value 0. Abbreviation 'A'.
     */
    ACE('A', 0),
    /**
     * Ordinal value 1. Abbreviation 'K'.
     */
    KING('K', 1), 
    /**
     * Ordinal value 2. Abbreviation 'Q'.
     */
    QUEEN('Q', 2), 
    /**
     * Ordinal value 3. Abbreviation 'J'.
     */
    JACK('J', 3), 
    /**
     * Ordinal value 4. Abbreviation 'T'.
     */
    TEN('T', 4), 
    /**
     * Ordinal value 5. Abbreviation '9'.
     */
    NINE('9', 5),
    /**
     * Ordinal value 6. Abbreviation '8'.
     */
    EIGHT('8', 6),
    /**
     * Ordinal value 7. Abbreviation '7'.
     */
    SEVEN('7', 7),
    /**
     * Ordinal value 8. Abbreviation '6'.
     */
    SIX('6', 8), 
    /**
     * Ordinal value 9. Abbreviation '5'.
     */
    FIVE('5', 9), 
    /**
     * Ordinal value 10. Abbreviation '4'.
     */
    FOUR('4', 10),
    /**
     * Ordinal value 11. Abbreviation '3'.
     */
    THREE('3', 11), 
    /**
     * Ordinal value 12. Abbreviation '2'.
     */
    TWO('2', 12);    
    
    private char abbreviation;
    private int value;
    
    private Rank(char abbreviation, int value)
    {
        this.abbreviation = abbreviation;
        this.value = value;
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
    
    public static Rank intToRank(int i)
    {
        for (Rank rank : Rank.values())
        {
            if (rank.value == i)
            {
                return rank;
            }
        }
        throw new IllegalArgumentException();
    }
}
