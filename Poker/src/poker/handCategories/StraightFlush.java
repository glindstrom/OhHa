

package poker.handCategories;

import poker.Card;


public class StraightFlush extends HandCategory
{
    private Card highestCard;
    public StraightFlush(Card highestCard)
    {
        super(0);
        this.highestCard = highestCard;
    }        

    @Override
    public int compareTo(HandCategory otherHandCategory)
    {
        int comparison =  super.compareTo(otherHandCategory);
        if (comparison != 0) { return comparison;}
        StraightFlush other = (StraightFlush) otherHandCategory;
        return this.highestCard.getRank().compareTo(other.highestCard.getRank());        
    }
    
    
}