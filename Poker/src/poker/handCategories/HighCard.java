

package poker.handCategories;

import poker.Hand;


public class HighCard extends HandCategory
{
    public HighCard(Hand h)
    {
        super(8, h);
    }

    @Override
    public int compareInternally(HandCategory otherHandCategory)
    {
        HighCard other = (HighCard) otherHandCategory;
        return compareRanksInHand(this.getHand(), other.getHand());
    }


}
