
package poker.handCategories;

/**
 *
 * @author Gabriel
 */
public abstract class HandCategory implements Comparable<HandCategory>
{
    private int ordinal;
    
    public HandCategory (int ordinal)
    {
        this.ordinal = ordinal;
    }

    public int getOrdinal()
    {
        return ordinal;
    }

    @Override
    public int compareTo(HandCategory other)
    {
        return this.ordinal - other.ordinal;
    }
    
    
}
