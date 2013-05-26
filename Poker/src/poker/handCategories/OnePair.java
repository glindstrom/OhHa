

package poker.handCategories;

import poker.Hand;

/**
 * Represents one pair, the eighth strongest hand category.
 * @author Gabriel
 */
public class OnePair extends HandCategory
{
    protected OnePair(Hand h)
    {
        super(7, h);
    }       

    @Override
    protected int compareInternally(HandCategory otherHandCategory)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }        
}
