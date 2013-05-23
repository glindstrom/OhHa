

package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck 
{
    private List<Card> cards;
    
    public Deck()
    {
        this.cards = new ArrayList();
        initDeck();
    }
    
    public int size()
    {
        return this.cards.size();        
    }

    public Card dealCard()
    {
        return this.cards.remove(0);
    }

    public boolean remove(Card c)
    {
        return this.cards.remove(c);
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public void reset()
    {
        initDeck();
    }

    private void initDeck()
    {
        this.cards.clear();
        for (Suit suit : Suit.values())
        {
            for (Rank rank : Rank.values())
            {
                Card c = new Card(rank, suit);
                this.cards.add(c);
            }
        }
    }

}
