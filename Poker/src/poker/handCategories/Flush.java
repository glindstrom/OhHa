

package poker.handCategories;

import poker.Hand;

/**
 * Represents a flush, the fourth strongest hand category.
 * 
 */
public class Flush extends HandCategory
{
    protected Flush(Hand h)
    {
        super(3, h);
    }

    @Override
    protected int compareInternally(HandCategory otherHandCategory)
    {
        Flush other = (Flush) otherHandCategory;
        return compareRanksInHand(this.getHand(), other.getHand());
    }
}
