

package poker.calculator;

import poker.deck.Card;
import poker.deck.Deck;
import poker.evaluator.Evaluator;
import poker.evaluator.Hand;

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

    /**
     * Sets the number of trials to be used.
     * @param trials the number of trials to be used
     * @throws IllegalArgumentException if the number of trials is less than one
     */
    public void setTrials(int trials)
    {
        if (trials < 1)
        {
            throw new IllegalArgumentException("The number of trials has to be positive.");
        }
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
        this.holeCards1 = new Hand(hand1);
        this.holeCards2 = new Hand(hand2);
        checkMutualExclusivity();
        resetStats();
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
    
    @Override
    public double equity1()
    {
        return winPercentage() + 0.5*tiePercentage();
    }
    
    @Override
    public double equity2()
    {
        return lossPercentage() + 0.5*tiePercentage();
    }
    
    /**
     * Removes the hole cards from the deck.
     */
    private void removeHoleCardsFromDeck()
    {
        for (Card c : this.holeCards1)
        {
            this.deck.remove(c);
        }
        for (Card c : this.holeCards2)
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
     * Checks that the hand is valid.
     * @param hand a string representation of a hand.
     * @throws IllegalArgumentException if the hand length differs from 4
     */
    private void checkHand(String hand)
    {
        if (hand.isEmpty())
        {
            throw new IllegalArgumentException("Invalid hand - hand is empty.");
        }
        
        if (hand.length() != 4)
        {
            throw new IllegalArgumentException(hand + " is not a valid hand - a hand must contain 2 cards.");
        }
        
        if (!handSyntaxValid(hand))
        {
            throw new IllegalArgumentException("Invalid syntax. " + hand + " is not a valid hand.");
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
    
    /**
     * Converts a double to percentage.
     * @param d the double to be converted
     * @return a string expressing the double as percentage
     */
    private String decimalToPercentageString(double d)
    {
        return String.format("%.2f", 100*d) + "%";
    }
    
    /**
     * Checks that the string represents a valid hand.
     * @param hand the hand to be checked.
     * @return true if the hand is valid
     */
    private boolean handSyntaxValid(String hand)
    {
        String ranks = "AKQJT98765432";
        String suits = "CDHS";
        hand = hand.toUpperCase();
                
        if (!ranks.contains(Character.toString(hand.charAt(0))) || !ranks.contains(Character.toString(hand.charAt(2)))
                || !suits.contains(Character.toString(hand.charAt(1))) || !suits.contains(Character.toString(hand.charAt(3))))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Checks that a card is not in both hands at the same time.
     * @throws IllegalArgumentException if both hands contain the same card.
     */
    private void checkMutualExclusivity()
    {
        for (Card c : this.holeCards1)
        {
            if (this.holeCards2.getCards().contains(c))
            {
                throw new IllegalArgumentException(c + " cannot be in both hands at the same time.");
            }
        }
    }
}
