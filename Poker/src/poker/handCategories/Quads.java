

package poker.handCategories;
import poker.Hand;



public class Quads extends HandCategory
{
   
    public Quads(Hand h)
    {
        super(1, h);
        
    }

    @Override
    public int compareTo(HandCategory otherHandCategory)
    {
        int comparison =  super.compareTo(otherHandCategory);
        if (comparison != 0) { return comparison;}
        Quads other = (Quads) otherHandCategory;
        int handValueThis = cardRank(this.getHand(), 4);
        int handValueThat = cardRank(other.getHand(), 4);
        return handValueThis - handValueThat;        
    }         
        
    @Override
    public int compareInternally(HandCategory otherHandCategory)
    {
        Quads other = (Quads) otherHandCategory;
        int handValueThis = cardRank(this.getHand(), 4);
        int handValueThat = cardRank(other.getHand(), 4);
        int comparison = handValueThis - handValueThat;
        if (comparison == 0)
        {
            comparison = cardRank(this.getHand(), 1) - cardRank(other.getHand(), 1);
        }
        return comparison;
    }
}
