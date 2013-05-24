

package poker.handCategories;

import java.util.TreeSet;
import poker.Card;
import poker.Hand;
import poker.Rank;

public class Straight extends HandCategory
{
    public Straight(Hand h)
    {
        super(4, h);
    }

    @Override
    public int compareInternally(HandCategory otherHandCategory)
    {
        Straight other = (Straight) otherHandCategory;
        TreeSet<Card> thisCards = new TreeSet(this.getHand().getCards());
        TreeSet<Card> otherCards = new TreeSet(other.getHand().getCards());
        Rank highestRankThis = thisCards.pollFirst().getRank();
        Rank highestRankOther = otherCards.pollFirst().getRank();
        if (highestRankThis == Rank.ACE && highestRankOther == Rank.ACE)
        {
            highestRankThis = thisCards.pollFirst().getRank();
            highestRankOther = otherCards.pollFirst().getRank();
        }
        return highestRankThis.compareTo(highestRankOther);
    }
}
