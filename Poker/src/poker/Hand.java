

package poker;

import java.util.ArrayList;
import java.util.List;

public class Hand 
{
    private List<Card> cards;
    
    public Hand()
    {
        this.cards = new ArrayList();
    }
    
    public boolean addCard(Card c)
    {
        if (this.cards.size() == 5)
        {
            return false;
        }
        this.cards.add(c);
        return true;
    }
}
