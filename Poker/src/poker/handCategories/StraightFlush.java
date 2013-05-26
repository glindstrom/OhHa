

package poker.handCategories;

import java.util.TreeSet;
import poker.Card;
import poker.Hand;

/**
 * Represents a straight flush, the strongest hand category.
 * 
 */
public class StraightFlush extends HandCategory
{
    
    protected StraightFlush(Hand h)
    {
        super(0, h);
        
    }        

    @Override
    protected int compareInternally(HandCategory otherHandCategory)
    {
        StraightFlush other = (StraightFlush) otherHandCategory;
        TreeSet<Card> thisCards = new TreeSet(this.getHand().getCards());
        TreeSet<Card> otherCards = new TreeSet(other.getHand().getCards());
        return thisCards.first().getRank().compareTo(otherCards.first().getRank()); 
    }
    
    
}