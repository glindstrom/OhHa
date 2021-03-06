

package poker.handCategories;

import java.util.TreeSet;
import poker.deck.Card;
import poker.evaluator.Hand;
import poker.deck.Rank;

/**
 * Represents a straight, the fifth strongest hand category.
 * 
 */
public class Straight extends HandCategory
{
    /**
     * Class constructor.
     * @param h a hand, whose value is straight
     */
    protected Straight(Hand h)
    {
        super(4, h);
    }

    @Override
    protected int compareInternally(HandCategory otherHandCategory)
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
