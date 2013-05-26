

package poker.handCategories;

import poker.Hand;

/**
 * Represents three of a kind, the sixth strongest hand category.
 * 
 */
public class Trips extends HandCategory
{
    protected Trips(Hand h)
    {
        super(5, h);
    }

    @Override
    protected int compareInternally(HandCategory otherHandCategory)
    {
        Trips other = (Trips) otherHandCategory;
        int comparison =  cardRank(this.getHand(), 3) - cardRank(other.getHand(), 3);
        if (comparison == 0)
        {
            int [] handFrequenciesThis = calculateCardFrequencies(this.getHand());
            int [] handFrequenciesOther = calculateCardFrequencies(other.getHand());
            comparison = compareKickers(handFrequenciesThis, handFrequenciesOther);
        }        
        return comparison;
    }    
}
