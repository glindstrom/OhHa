
package poker.deck;

/**
 * This enum type represents the rank of a Card.
 * @see Card
 */
public enum Rank
{

    ACE('A'),
    KING('K'), 
    QUEEN('Q'), 
    JACK('J'), 
    TEN('T'), 
    NINE('9'),
    EIGHT('8'),
    SEVEN('7'),
    SIX('6'), 
    FIVE('5'), 
    FOUR('4'),
    THREE('3'), 
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
