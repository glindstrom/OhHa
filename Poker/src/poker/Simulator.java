

package poker;

import poker.deck.Card;
import poker.deck.Deck;

/**
 * Calculates the pre-flop all-in equity for two Hold'em hands using a Monte Carlo simulation. 
 * 
 */
public class Simulator implements PokerCalculator
{
    /**
     * The number of times hand1 won.
     */
    private int wins;
    /**
     * The number of times hand1 lost.
     */
    private int losses;
    /**
     * The number of times the hands tied.
     */
    private int ties;
    /**
     * The number of random boards dealt.
     */
    private int trials;
    /**
     * The first hand.
     */
    private Hand holeCards1;
    /**
     * The second hand.
     */
    private Hand holeCards2;
    /**
     * A deck of cards.
     */
    private Deck deck;
    /**
     * A hand evaluator.
     */
    private Evaluator evaluator;
    
    /**
     * Class constructor.
     */
    public Simulator()
    {
        resetStats();
        this.deck = new Deck();
        this.trials = 50000;
        this.evaluator = new Evaluator();
    }
    
    public int getWins()
    {
        return this.wins;
    }
    
    public int getLosses()
    {
        return this.losses;
    }

    public int getTies()
    {
        return ties;
    }        

    public int getTrials()
    {
        return trials;
    }

    public void setTrials(int trials)
    {
        this.trials = trials;
    }    
    
    /**
     * Calculates the winning percentage of the first hand.
     * @return the winning percentage expressed in decimal format
     */
    public double winPercentage()
    {
        return 1.0*this.wins/trials;
    }
    
    /**
     * Calculates the loss percentage of the first hand.
     * @return the loss percentage expressed in decimal format
     */
     public double lossPercentage()
    {
        return 1.0*losses/trials;
    }
     
    /**
     * Calculates the tie percentage.
     * @return the tie percentage expressed in decimal format
     */ 
    public double tiePercentage()
    {
        return 1.0*ties/trials;
    }
    
    @Override
    public void calculateEquity(String hand1, String hand2)
    {
        checkHand(hand1);
        checkHand(hand2);
        resetStats();
        this.holeCards1 = new Hand(hand1);
        this.holeCards2 = new Hand(hand2);
        
        for (int i = 0; i < this.trials; i++)
        {
            this.deck.reset();
            removeHoleCardsFromDeck();
            this.deck.shuffle();
            Hand board = deal5cards();
            Hand h1 = Hand.mergeHands(board, this.holeCards1);
            Hand h2 = Hand.mergeHands(board, this.holeCards2);
            updateResults(h1, h2);
        }
    }
    
    public String hand1ToString()
    {
        return this.holeCards1.toString();
    }
   
    public String hand2ToString()
    {
        return this.holeCards2.toString();
    }
    
    /**
     * Calculates the equity of the first hand.
     * Equity is defined as winning percentage + tie percentage / 2. For example,
     * if the pot size is 100 and the equity is 60 %, it means that the expected
     * stack size is 0.6*100=60.
     * @return the equity expressed as a decimal number
     */
    public double equity1()
    {
        return winPercentage() + 0.5*tiePercentage();
    }
    
    /**
     * Calculates the equity of the second hand.
     * @return the equity expressed as a decimal number
     */
    public double equity2()
    {
        return lossPercentage() + 0.5*tiePercentage();
    }
    
    /**
     * Removes the hole cards from the deck.
     */
    private void removeHoleCardsFromDeck()
    {
        for (Card c : this.holeCards1.getCards())
        {
            this.deck.remove(c);
        }
        for (Card c : this.holeCards2.getCards())
        {
            this.deck.remove(c);
        }
    }
    
    /**
     * Deals the board.
     * @return a 5-card hand
     */
    private Hand deal5cards()
    {
        Hand h = new Hand();
        for (int i = 0; i < 5; i++)
        {
            Card c = deck.dealCard();
            h.addCard(c);
        }
        return h;
    }

    /**
     * Updates the counters after performed simulation.
     * @param h1 The board + hole cards of the first hand.
     * @param h2 The board + hole cards of the second hand.
     */
    private void updateResults(Hand h1, Hand h2)
    {
        Hand bestHand1 = this.evaluator.best5CardHand(h1);
        Hand bestHand2 = this.evaluator.best5CardHand(h2);
        int comparison = this.evaluator.compareHands(bestHand1, bestHand2);
        if (comparison == 1)
        {
            this.wins++;
        }
        else if (comparison == 2)
        {
            this.losses++;
        }
        else 
        {
            ties++;
        }
    }
    
    /**
     * Sets all counters to zero.
     */
    private void resetStats()
    {
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
    }
    
    /**
     * Checks that the hand has correct length.
     * @param hand a string representation of a hand.
     */
    private void checkHand(String hand)
    {
        if (hand.length() != 4)
        {
            throw new IllegalArgumentException(hand + " is not a valid hand - expected 2 cards but got " + (int) Math.ceil(1.0*hand.length()/2));
        }
    }

    @Override
    public String toString()
    {
        String [] stringArray = {"Hand", "Equity", "Wins", "Ties",
            this.hand1ToString(), decimalToPercentageString(this.equity1()), Integer.toString(wins) ,Integer.toString(ties), 
            this.hand2ToString(), decimalToPercentageString(this.equity2()), Integer.toString(losses), Integer.toString(ties)
        };
        String s = "";
        for (int i = 0; i < stringArray.length; i++)
        {
            if ((i+1) % 4 != 0)
            {
                s +=  String.format("%-15s", stringArray[i]);
            }
            else
            {
                s += stringArray[i] + "\n";
            }
        }
        return s;
    }
    
    private String decimalToPercentageString(double d)
    {
        return String.format("%.2f", 100*d) + "%";
    }
    
    
}
