
package poker;


public enum Rank
{
    TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'),
    NINE('9'), TEN('T'), JACK('J'), QUEEN('Q'), KING('K'), ACE('A');    
    
    private char abbreviation;
    
    private Rank(char abbreviation)
    {
        this.abbreviation = abbreviation;
    }
    
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
}
