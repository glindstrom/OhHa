
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
}
