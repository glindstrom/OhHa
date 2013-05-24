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

    private List<Card> cards;

    /**
     * Class constructor.
     */
    public Hand()
    {
        this.cards = new ArrayList();
    }

    /**
     * Creates a hand containing the specified cards.
     *
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

    Hand(String hand)
    {
        this.cards = new ArrayList();
        for (int i = 0; i < hand.length(); i += 2)
        {
            String card = String.valueOf(hand.charAt(i)) + hand.charAt(i + 1);
            Card c = new Card(card);
            this.cards.add(c);
        }
    }

    /**
     * Adds a card to the Hand. At most seven cards can be added to the hand.
     *
     * @param c the card to be added
     * @return Returns true if the card was successfully added, false otherwise.
     */
    public boolean addCard(Card c)
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
}
