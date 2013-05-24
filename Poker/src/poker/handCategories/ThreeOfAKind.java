

package poker.handCategories;

import poker.Hand;

public class ThreeOfAKind extends HandCategory
{
    public ThreeOfAKind(Hand h)
    {
        super(5, h);
    }

    @Override
    public int compareInternally(HandCategory otherHandCategory)
    {
        ThreeOfAKind other = (ThreeOfAKind) otherHandCategory;
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
