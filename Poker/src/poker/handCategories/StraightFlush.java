

package poker.handCategories;

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
        return this.getHand().getCards().first().getRank().compareTo(other.getHand().getCards().first().getRank()); 
    }
    
    
}