

package poker.handCategories;

import poker.Rank;

public class Quads extends HandCategory
{
    private Rank rank;
    public Quads(Rank rank)
    {
        super(1);
        this.rank = rank;
    }

    @Override
    public int compareTo(HandCategory otherHandCategory)
    {
        int comparison =  super.compareTo(otherHandCategory);
        if (comparison != 0) { return comparison;}
        Quads other = (Quads) otherHandCategory;
        return this.rank.compareTo(other.rank);
    }           
}
