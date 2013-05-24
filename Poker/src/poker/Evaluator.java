package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import poker.handCategories.*;
/**
 * Determines the winner of two poker hands. 
 * 
 */
public class Evaluator
{

    /*
     * Class constructor.
     */
    public Evaluator()
    {
    }
    /**
     * Evaluates two poker hands at showdown. If only one hand contains five cards, then that hand wins.
     * If neither hand contains five cards they tie.
     * @param h1 the first hand to be compared
     * @param h2 the second hand to be compared
     * @return Returns 1 if h1 wins, 2 if h2 wins and 0 if the hands tie.
     */
    public int compareHands(Hand h1, Hand h2)
    {
        if (h1.size() != 5 && h2.size() != 5)
        {
            return 0;
        }
        if (h1.size() == 5 && h2.size() != 5)
        {
            return 1;
        }
        if (h2.size() == 5 && h1.size() != 5)
        {
            return 2;
        }
        int comparison = handCategory(h1).compareTo(handCategory(h2));
        if (comparison < 0)
        {
            return 1;
        }
        if (comparison > 0)
        {
            return 2;
        }
        return 0;
    }

    // this method should be in separate class?
    public HandCategory handCategory(Hand h)
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
    
    public Hand best5CardHand(Hand h)
    {        
        return best5CardHandOutOfNcards(h, 7);
    }

    private boolean findFourOfAKind(Hand h)
    {
        return hasKind(4, h);
    }

    private boolean findThreeOfAKind(Hand h)
    {
        return hasKind(3, h);
    }

    private boolean findTwoPair(Hand h)
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

    private boolean findOnePair(Hand h)
    {
        return hasKind(2, h);
    }

    private boolean hasKind(int n, Hand h)
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

    private boolean findFlush(Hand h)
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

    private boolean findStraight(Hand h)
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
    public static int [] calculateCardFrequencies(Hand h)
    {
        int [] cardFrequencies = new int[13];
        for (Card c : h.getCards())
        {
            cardFrequencies[c.getRank().ordinal()]++;
        }
        return cardFrequencies;
    }

    private Hand best5CardHandOutOfNcards(Hand h, int n)
    {   
        Hand bestHand = new Hand();
        List<Card> cards = h.getCards();
        for (int i = 0; i < n ; i++)
        {
            for (int j = i+1; j < n; j++)
            {
                for (int k = j+1; k < n; k++)
                {
                    for (int l = k+1; l < n; l++)
                    {
                        for (int m = l+1; m < n; m++)
                        {
                            Hand comparableHand = new Hand();
                            comparableHand.addCard(cards.get(i));
                            comparableHand.addCard(cards.get(j));
                            comparableHand.addCard(cards.get(k));
                            comparableHand.addCard(cards.get(l));
                            comparableHand.addCard(cards.get(m));
                            if (compareHands(comparableHand, bestHand) == 1)
                            {
                                bestHand = comparableHand;
                            }
                        }
                    }
                }
            }
        }
        return bestHand;
    }        
}
