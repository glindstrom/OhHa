

package poker.handCategories;

import poker.Hand;


public class FullHouse extends HandCategory
{
    public FullHouse(Hand h)
    {
        super(2, h);
    }

    @Override
    public int compareInternally(HandCategory otherHandCategory)
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
