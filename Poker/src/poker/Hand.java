package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Hand object represents a poker hand containing at most seven cards.
 *
 * @see Card
 */
public class Hand
{
    /**
     * the cards in the hand
     */
    private List<Card> cards;

    /**
     * Class constructor.
     */
    public Hand()
    {
        this.cards = new ArrayList();
    }

    /**
     * Class constructor specifying the cards in the hand.
     * @param cards Specifies up to seven card objects to be included in the
     * hand.
     */
    public Hand(Card... cards)
    {
        this();
        if (cards.length > 7)
        {
            throw new IllegalArgumentException("A hand can contain at most seven cards.");
        }
        if (cards == null)
        {
            throw new NullPointerException("Card must not be null");
        }
        this.cards.addAll(Arrays.asList(cards));
    }
    
    /**
     * Class constructor specifying the cards in the hand as a String.
     * @param hand a string representation of the cards in the hand
     */
    Hand(String hand)
    {
        this();
        if (hand == null)
        {
            throw new NullPointerException("String must not be null");
        }
        for (int i = 0; i < hand.length(); i += 2)
        {
            String card = String.valueOf(hand.charAt(i)) + hand.charAt(i + 1);
            Card c = new Card(card);
            if (!this.addCard(c))
            {
                throw new IllegalArgumentException("A hand can contain at most seven cards.");
            }            
        }
    }

    /**
     * Adds a card to the Hand. At most seven cards can be added to the hand.
     *
     * @param c the card to be added
     * @return true if the card was successfully added, false otherwise
     * @throws NullPointerException if c == null
     */
    public final boolean addCard(Card c)
    {
        if (c == null)
        {
            throw new NullPointerException("Card must not be null.");
        }
        if (this.cards.size() == 7)
        {
            return false;
        }
        return this.cards.add(c);
    }

    /**
     * Returns the cards in the hand.
     *
     * @return A shallow copy of the set of cards.
     */
    public List<Card> getCards()
    {
        return new ArrayList<>(this.cards);
    }

    /**
     * Returns number of cards in hand.
     *
     * @return integer in the interval [0,7].
     */
    public int size()
    {
        return this.cards.size();
    }
    
    /**
     * Combines the cards from two hands to form a new hand.
     * @param h1 the first hand
     * @param h2 the second hand
     * @return a hand containing the cards in h1 and h2
     */
    public static Hand mergeHands(Hand h1, Hand h2)
    {
        Hand h = new Hand();
        for (Card c : h1.cards)
        {
            h.addCard(c);
        }
        for (Card c : h2.cards)
        {
            h.addCard(c);
        }
        return h;
    }

    @Override
    public String toString()
    {
        String hand = "";
        for (Card c : this.cards)
        {
            hand += c.toString(); 
        }
        
        return hand;
    }
    
    
}
