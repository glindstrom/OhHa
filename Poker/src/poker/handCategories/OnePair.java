

package poker.handCategories;

import poker.Hand;


public class OnePair extends HandCategory
{
    public OnePair(Hand h)
    {
        super(7, h);
    }       

    @Override
    public int compareInternally(HandCategory otherHandCategory)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
