
package poker.handCategories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import poker.Card;
import poker.Hand;
import poker.Rank;

/**
 *HandCategory is the abstract base class for the different hand types you can have
 * at showdown.
 * 
 */
public abstract class HandCategory implements Comparable<HandCategory>
{
    private int ordinal;
    private Hand hand;
    
    public HandCategory (int ordinal, Hand h)
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
            return new ThreeOfAKind(h);
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
    
    private static boolean findFourOfAKind(Hand h)
    {
        return hasKind(4, h);
    }

    private static boolean findThreeOfAKind(Hand h)
    {
        return hasKind(3, h);
    }

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

    private static  boolean findOnePair(Hand h)
    {
        return hasKind(2, h);
    }

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
    
    public abstract int compareInternally(HandCategory otherHandCategory);
    
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
    
    protected int compareRanksInHand(Hand h1, Hand h2)
    {
        List<Card> thisCards = h1.getCards();
        List<Card> otherCards = h2.getCards();       
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
    
    protected int compareKickers(int [] cardFrequenciesThis, int [] cardFrequenciesOther)
    {
     
        for (int i = 0; i < cardFrequenciesThis.length; i++)
        {
            if (cardFrequenciesThis[i] == 1 && cardFrequenciesOther[i] == 0)
            {
                return -1;
            }
            if (cardFrequenciesOther[i] == 1 && cardFrequenciesThis[i] == 0)
            {
                return 1;
            }
        }
        return 0;       
    }
    
}
