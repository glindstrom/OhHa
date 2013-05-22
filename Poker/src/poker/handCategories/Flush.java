

package poker.handCategories;

import poker.Hand;

public class Flush extends HandCategory
{
    public Flush(Hand h)
    {
        super(3, h);
    }

    @Override
    public int compareInternally(HandCategory otherHandCategory)
    {
        Flush other = (Flush) otherHandCategory;
        return compareRanksInHand(this.getHand(), other.getHand());
    }
}
