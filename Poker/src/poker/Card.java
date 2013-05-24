
package poker;

/**
 * A Card object represents a playing card.
 * A card has a rank and a suit.
 * @see Rank
 * @see Suit
 */
public class Card implements Comparable<Card>
{
    private Rank rank;
    private Suit suit;
    
    /**
     * Constructs a card of the specified rank and suit.
     * @param rank The rank of the card.
     * @param suit The suit of the card.
     */
    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }
    /**
     * Constructs a card from a String representation.
     * @param card A string representation of a card. For example Ad = ace of diamonds.
     * @throws IllegalArgumentException if the length of card differs from two.
     */
    public Card(String card)
    {
        if (card.length() != 2)
        {
            throw new IllegalArgumentException();
        }
        this.rank = Rank.charToRank(card.charAt(0));
        this.suit = Suit.charToSuit(card.charAt(1));
    }
    /**
     * Returns the rank of this card.
     * @return The rank of this card.
     */
    public Rank getRank()
    {
        return this.rank;
    }
    
    /**
     * Returns the suit of this card.
     * @return The suit of this card.
     */
    public Suit getSuit()
    {
        return suit;
    }        
/**
 * Compares two cards to each other.
 * @param other the card this card is compared against
 * @return a negative integer if this cars has higher rank, a positive integer
 * if it has lower rank, and 0 if ranks are equal
 */
    @Override
    public int compareTo(Card other)
    {
        return this.rank.compareTo(other.getRank());
    }

    
    @Override
    public boolean equals(Object otherObject)
    {
        if (this == otherObject)
        {
            return true;
        }
        if (otherObject == null)
        {
            return false;
        }
        if (!(otherObject instanceof Card))
        {
            return false;
        }
        Card other = (Card) otherObject;
        return this.rank == other.rank && this.suit == other.suit;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 47 * hash + (this.rank != null ? this.rank.hashCode() : 0);
        hash = 47 * hash + (this.suit != null ? this.suit.hashCode() : 0);
        return hash;
    }
    
    
}
