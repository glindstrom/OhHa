

package poker.handCategories;
import poker.Evaluator;
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
        int handValueThis = valueOfQuads(this.getHand());
        int handValueThat = valueOfQuads(other.getHand());
        return handValueThis - handValueThat;        
    }         
    
    private int valueOfQuads(Hand h)
    {
        int [] handFrequencies = Evaluator.calculateCardFrequencies(h);
        for (int i = 0; i < handFrequencies.length; i++)
        {
            if (handFrequencies[i] == 4)
            {
                return i;
            }
        }
        return -1;
    }
}
