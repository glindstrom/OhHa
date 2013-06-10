

package poker.handCategories;

import poker.evaluator.Hand;

/**
 * Represents high card, the weakest hand category.
 * 
 */
public class HighCard extends HandCategory
{
    /**
     * Class constructor.
     * @param h a hand, whose value is high card
     */
    protected HighCard(Hand h)
    {
        super(8, h);
    }

    @Override
    protected int compareInternally(HandCategory otherHandCategory)
    {
        HighCard other = (HighCard) otherHandCategory;
        return compareRanksInHand(this.getHand(), other.getHand());
    }


}
