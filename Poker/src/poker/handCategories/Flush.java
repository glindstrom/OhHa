

package poker.handCategories;

// Gabriel Lindström

import poker.handCategories.HandCategory;


public class Flush extends HandCategory
{
    public Flush()
    {
        super(3);
    }

    @Override
    public int compareTo(HandCategory t)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
