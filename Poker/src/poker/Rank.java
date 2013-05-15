
package poker;


public enum Rank
{
    ACE('A'), KING('K'), QUEEN('Q'), JACK('J'), TEN('T'), NINE('9'), EIGHT('8'),
    SEVEN('7'), SIX('6'), FIVE('5'), FOUR('4'), THREE('3'), TWO('2');
    
    private char abbreviation;
    
    private Rank(char abbreviation)
    {
        this.abbreviation = abbreviation;
    }
    
    public static Rank returnRank(char c)
    {
        for (Rank rank : Rank.values())
        {
            if (rank.abbreviation == c)
            {
                return rank;
            }
        }
        return null;
    }
}
