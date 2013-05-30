
package poker.handCategories;

import java.util.Collections;
import java.util.List;
import poker.deck.Card;
import poker.Hand;
import poker.deck.Rank;

/**
 *HandCategory is the abstract base class for the different hand types you can have
 * at showdown.
 * 
 */
public abstract class HandCategory implements Comparable<HandCategory>
{
    private int ordinal;
    private Hand hand;
    
    /**
     * Creates a new hand category.
     * @param ordinal an order number for the hand category. The lower the number, the stronger the hand.
     * @param h the hand associated with the hand category
     */
    protected HandCategory (int ordinal, Hand h)
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
    
    /**
     * Factory method for HandCategory.
     * @param h the hand for which the hand category is to be extracted
     * @return the hand category of the specified hand
     */
    public static HandCategory extractHandCategory(Hand h)
    {            
        calculateCardFrequencies(h);
        
        if (findFourOfAKind(h))
        {
            return new Quads(h);
        }
        if (findThreeOfAKind(h) && findOnePair(h))
        {
            return new FullHouse(h);
        }
        if (findThreeOfAKind(h))
        {
            return new Trips(h);
        }
        if (findTwoPair(h))
        {
            return new TwoPair(h);
        }
        if (findOnePair(h))
        {
            return new OnePair(h);
        }
        if (findFlush(h) && findStraight(h))
        {
            return new StraightFlush(h);
        }
        if (findFlush(h))
        {
            return new Flush(h);
        }
        if (findStraight(h))
        {
            return new Straight(h);
        }       
        return new HighCard(h);
    
    }
    
    /**
     * Checks if the given hand contains four of a kind
     * @param h the hand to be evaluated
     * @return true if the hand contains four of a kind
     */
    private static boolean findFourOfAKind(Hand h)
    {
        return hasKind(4, h);
    }
    
    /**
     * Checks if the given hand contains three of a kind
     * @param h the hand to be evaluated
     * @return true if the hand contains three of a kind
     */
    private static boolean findThreeOfAKind(Hand h)
    {
        return hasKind(3, h);
    }
    
    /**
     * Checks if the given hand contains two pair
     * @param h the hand to be evaluated
     * @return true if the hand contains two pair
     */
    private static boolean findTwoPair(Hand h)
    {
        int numberOfPairs = 0;
        int [] cardFrequencies = calculateCardFrequencies(h);
        for (int i : cardFrequencies)
        {
            if (i == 2)
            {
                numberOfPairs++;
            }
        }
        return numberOfPairs == 2;
    }

    /**
     * Checks if the given hand contains one pair
     * @param h the hand to be evaluated
     * @return true if the hand contains one pair
     */
    private static  boolean findOnePair(Hand h)
    {
        return hasKind(2, h);
    }

    /**
     * Checks if the given hand contains n cards of the same ranks
     * @param n the number of cards of the same rank
     * @param h the hand to be evaluated
     * @return true if the hand contains n cards of the same rank
     */
    private static  boolean hasKind(int n, Hand h)
    {
        int [] cardFrequencies = calculateCardFrequencies(h);
        for (int i = 0; i < cardFrequencies.length; i++)
        {
            if (cardFrequencies[i] == n)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the specified hand contains a flush.
     * @param h the hand to be evaluated
     * @return true if the specified hand contains a flush
     */
    private static boolean findFlush(Hand h)
    {
        List<Card> cards = h.getCards();
        Card card = cards.remove(0);
        for (Card c : cards)
        {
            if (c.getSuit() != card.getSuit())
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Checks if the specified hand contains a straight.
     * @param h the hand to be evaluated
     * @return true if the specified hand contains a straight
     */
    private static boolean findStraight(Hand h)
    {
        List<Card> cards = h.getCards();
        Collections.sort(cards);
        if (cards.get(0).getRank() == Rank.ACE && cards.get(1).getRank() == Rank.FIVE)
        {
            return true;
        }
        for (int i = 0; i < cards.size()-1; i++)
        {
            if (cards.get(i).getRank().ordinal() != cards.get(i+1).getRank().ordinal()-1)
            {
                return false;
            }
        }
        return true;
    }
    
     /**
     * Counts how many times each card rank occurs in a hand.
     * @param h the hand to be evaluated
     * @return an array containing the number of times each of the 13 card ranks occurs. 
     * The frequencies are in order of decreasing rank, so the number of aces is at index 0,
     * and the number of deuces at index 12;
     */
    protected static int [] calculateCardFrequencies(Hand h)
    {
        int [] cardFrequencies = new int[13];
        for (Card c : h.getCards())
        {
            cardFrequencies[c.getRank().ordinal()]++;
        }
        return cardFrequencies;
    }
    
    /**
     * Compares two hands belonging to the same hand category.
     * @param otherHandCategory the hand category this is compared against
     * @return a negative integer if the hand in this wins against the hand in that,
     * a positive integer if it loses and 0 if they tie
     */
    protected abstract int compareInternally(HandCategory otherHandCategory);
    
    @Override
    public int compareTo(HandCategory other)
    {
        int comparison = this.ordinal - other.ordinal;
        if (comparison == 0)
        {
            comparison = compareInternally(other);
        }
        return comparison;
    }
    
    /**
     * Returns a number representing a rank, which occurs n times in hand. 
     * Returns -1 if no card occurs with the specified frequency
     * @param h the hand to be evaluated
     * @param howManyOfAKind the number of times a rank should occur in the hand
     * @return a number representing a rank, which occurs n times in hand.
     * 0 represents an ace and 12 represents a deuce.
     */
    protected int cardRank(Hand h, int howManyOfAKind)
    {
        int [] handFrequencies = calculateCardFrequencies(h);
        for (int i = 0; i < handFrequencies.length; i++)
        {
            if (handFrequencies[i] == howManyOfAKind)
            {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Compares the card ranks of two sorted hands card by card.
     * @param h1 the first hand
     * @param h2 the second hand
     * @return a negative integer if a card in h1 has a higher rank than the 
     * corresponding card in h2, a positive integer if the rank of h2 is higher,
     * 0 if all corresponding cards are of equal rank
     */
    protected int compareRanksInHand(Hand h1, Hand h2)
    {
        List<Card> thisCards = h1.getCards();
        List<Card> otherCards = h2.getCards();   
        Collections.sort(thisCards);
        Collections.sort(otherCards);
        for (int i = 0; i < Math.min(thisCards.size(), otherCards.size()); i++)
        {
            int rankComparison = thisCards.get(i).getRank().compareTo(otherCards.get(i).getRank());
            if (rankComparison != 0)
            {
                return rankComparison;
            }
        }
        return 0;
    }
    
    /**
     * Compares the kickers in two poker hands, that is cards which are used to break ties
     * @param cardFrequenciesThis an array containing the frequency of each rank in this 
     * @param cardFrequenciesOther an array containing the frequency of each rank in other
     * @return -1 if this has a bigger kicker than other, 1 if this has a smaller kicker, 0 otherwise
     */
    protected int compareKickers(int [] cardFrequenciesThis, int [] cardFrequenciesOther)
    {
     
        for (int i = 0; i < cardFrequenciesThis.length; i++)
        {
            if (cardFrequenciesThis[i] == 1 && cardFrequenciesOther[i] != 1)
            {
                return -1;
            }
            if (cardFrequenciesOther[i] == 1 && cardFrequenciesThis[i] != 1)
            {
                return 1;
            }
        }
        return 0;       
    }
    
}
