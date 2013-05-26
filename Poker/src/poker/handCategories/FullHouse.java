

package poker.handCategories;

import poker.Hand;

/**
 * Represents a full house, the third strongest hand category.
 *
 */
public class FullHouse extends HandCategory
{
    /**
     * Class constructor.
     * @param h a hand, whose value is full house
     */
    protected FullHouse(Hand h)
    {
        super(2, h);
    }

    @Override
    protected int compareInternally(HandCategory otherHandCategory)
    {
        FullHouse other = (FullHouse) otherHandCategory;
        int comparison = this.cardRank(this.getHand(), 3) - other.cardRank(other.getHand(), 3);
        if (comparison == 0)
        {
            comparison = this.cardRank(this.getHand(), 2) - other.cardRank(other.getHand(), 2);
        }
        return comparison;
    }
}
