

package poker.handCategories;

import java.util.TreeSet;
import poker.Card;
import poker.Hand;


public class StraightFlush extends HandCategory
{
    
    public StraightFlush(Hand h)
    {
        super(0, h);
        
    }        

    @Override
    public int compareInternally(HandCategory otherHandCategory)
    {
        StraightFlush other = (StraightFlush) otherHandCategory;
        TreeSet<Card> thisCards = new TreeSet(this.getHand().getCards());
        TreeSet<Card> otherCards = new TreeSet(other.getHand().getCards());
        return thisCards.first().getRank().compareTo(otherCards.first().getRank()); 
    }
    
    
}