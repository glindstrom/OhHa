

package poker.handCategories;

import poker.Hand;

/**
 * Represents two pair, the seventh strongest hand category.
 * 
 */
public class TwoPair extends HandCategory
{
    protected TwoPair(Hand h)
    {
        super(6, h);
    }

    @Override
    public int compareInternally(HandCategory otherHandCategory)
    {
        TwoPair other = (TwoPair) otherHandCategory;
        int [] handFrequenciesThis = calculateCardFrequencies(this.getHand());
        int [] handFrequenciesOther = calculateCardFrequencies(other.getHand());
        for (int i = 0; i < handFrequenciesThis.length; i++)
        {
            if (handFrequenciesThis[i] == 2 && handFrequenciesOther[i] < 2)
            {
                return -1;
            }
            if (handFrequenciesOther[i] == 2 && handFrequenciesThis[i] < 2)
            {
                return 1;
            }
        }
        return compareKickers(handFrequenciesThis, handFrequenciesOther);
    }


}
