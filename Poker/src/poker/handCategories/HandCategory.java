
package poker.handCategories;

import poker.Hand;

/**
 *
 * @author Gabriel
 */
public abstract class HandCategory implements Comparable<HandCategory>
{
    private int ordinal;
    private Hand hand;
    
    public HandCategory (int ordinal, Hand h)
    {
        this.ordinal = ordinal;
        this.hand = h;
    }

    public int getOrdinal()
    {
        return ordinal;
    }

    public Hand getHand()
    {
        return hand;
    }
    
    

    @Override
    public int compareTo(HandCategory other)
    {
        return this.ordinal - other.ordinal;
    }
    
    
}
