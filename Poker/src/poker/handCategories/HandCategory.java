
package poker.handCategories;

import java.util.ArrayList;
import poker.Card;
import poker.Evaluator;
import poker.Hand;

/**
 *
 * @author Gabriel
 */
public abstract class HandCategory implements Comparable<HandCategory>
{
    private int ordinal;
    private Hand hand;
    
    public HandCategory (int ordinal, Hand h)
    {
        this.ordinal = ordinal;
        this.hand = h;
    }

    public int getOrdinal()
    {
        return ordinal;
    }

    public Hand getHand()
    {
        return hand;
    }    
    
    public abstract int compareInternally(HandCategory otherHandCategory);

    @Override
    public int compareTo(HandCategory other)
    {
        int comparison = this.ordinal - other.ordinal;
        if (comparison == 0)
        {
            comparison = compareInternally(other);
        }
        return comparison;
    }
    
    protected int cardRank(Hand h, int howManyOfAKind)
    {
        int [] handFrequencies = Evaluator.calculateCardFrequencies(h);
        for (int i = 0; i < handFrequencies.length; i++)
        {
            if (handFrequencies[i] == howManyOfAKind)
            {
                return i;
            }
        }
        return -1;
    }
    
    protected int compareRanksInHand(Hand h1, Hand h2)
    {
        ArrayList<Card> thisCards = new ArrayList();
        ArrayList<Card> otherCards = new ArrayList();
        thisCards.addAll(h1.getCards());
        otherCards.addAll(h2.getCards());
        for (int i = 0; i < Math.min(thisCards.size(), otherCards.size()); i++)
        {
            int rankComparison = thisCards.get(i).getRank().compareTo(otherCards.get(i).getRank());
            if (rankComparison != 0)
            {
                return rankComparison;
            }
        }
        return 0;
    }
    
    protected int compareKickers(int [] cardFrequenciesThis, int [] cardFrequenciesOther)
    {
     
        for (int i = 0; i < cardFrequenciesThis.length; i++)
        {
            if (cardFrequenciesThis[i] == 1 && cardFrequenciesOther[i] == 0)
            {
                return -1;
            }
            if (cardFrequenciesOther[i] == 1 && cardFrequenciesThis[i] == 0)
            {
                return 1;
            }
        }
        return 0;       
    }
    
}
