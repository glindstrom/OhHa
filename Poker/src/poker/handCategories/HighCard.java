

package poker.handCategories;

import poker.Hand;

/**
 * Represents hight cards, the weakest hand category.
 * 
 */
public class HighCard extends HandCategory
{
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
