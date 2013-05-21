

package poker.handCategories;

import poker.Hand;


public class StraightFlush extends HandCategory
{
    
    public StraightFlush(Hand h)
    {
        super(0, h);
        
    }        

    @Override
    public int compareTo(HandCategory otherHandCategory)
    {
        int comparison =  super.compareTo(otherHandCategory);
        if (comparison != 0) { return comparison;}
        StraightFlush other = (StraightFlush) otherHandCategory;
        return this.getHand().getCards().first().getRank().compareTo(other.getHand().getCards().first().getRank());        
    }
    
    
}