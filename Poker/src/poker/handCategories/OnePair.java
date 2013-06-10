

package poker.handCategories;

import poker.evaluator.Hand;

/**
 * Represents one pair, the eighth strongest hand category.
 * @author Gabriel
 */
public class OnePair extends HandCategory
{
    /**
     * Class constructor.
     * @param h a hand, whose value is one pair
     */
    protected OnePair(Hand h)
    {
        super(7, h);
    }       

    @Override
    protected int compareInternally(HandCategory otherHandCategory)
    {
        OnePair other = (OnePair) otherHandCategory;
        int pairRankThis = cardRank(this.getHand(), 2);
        int pairRankOther = cardRank(other.getHand(), 2);
        if (pairRankThis != pairRankOther)
        {
            return pairRankThis - pairRankOther;
        }
        return compareKickers(calculateCardFrequencies(this.getHand()), calculateCardFrequencies(other.getHand()));
    }        
}
