package poker;

import static org.junit.Assert.*;
import org.junit.Test;
import poker.handCategories.*;

public class EvaluatorTest
{

    @Test
    public void handValueIsFourOfAKind()
    {
        Hand h = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("Qs"), new Card("2h"));
        Evaluator e = new Evaluator();
        HandCategory hc = e.handCategory(h);
        assertEquals(FourOfAKind.class, hc.getClass());
    }

    @Test
    public void handValueIsThreeOfAKind()
    {
        Hand h = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("5s"), new Card("2h"));
        Evaluator e = new Evaluator();
        HandCategory hc = e.handCategory(h);
        assertEquals(ThreeOfAKind.class, hc.getClass());
    }

    @Test
    public void handValueIsTwoPair()
    {
        Hand h = new Hand(new Card("Qh"), new Card("Qd"), new Card("5c"), new Card("5s"), new Card("2h"));
        Evaluator e = new Evaluator();
        HandCategory hc = e.handCategory(h);
        assertEquals(TwoPair.class, hc.getClass());
    }

    @Test
    public void handValueIsOnePair()
    {
        Hand h = new Hand(new Card("Qh"), new Card("Qd"), new Card("5c"), new Card("7s"), new Card("2h"));
        Evaluator e = new Evaluator();
        HandCategory hc = e.handCategory(h);
        assertEquals(OnePair.class, hc.getClass());
    }

    @Test
    public void handValueIsFlush()
    {
        Hand h = new Hand(new Card("Ah"), new Card("Kh"), new Card("Th"), new Card("8h"), new Card("2h"));
        Evaluator e = new Evaluator();
        HandCategory hc = e.handCategory(h);
        assertEquals(Flush.class, hc.getClass());
    }

    @Test
    public void handValueIsStraight()
    {
        Hand h = new Hand(new Card("7h"), new Card("Ts"), new Card("9h"), new Card("8h"), new Card("Jh"));
        Evaluator e = new Evaluator();
        HandCategory hc = e.handCategory(h);
        assertEquals(Straight.class, hc.getClass());
    }

    @Test
    public void handValueIsStraightFlush()
    {
        Hand h = new Hand(new Card("7h"), new Card("Th"), new Card("9h"), new Card("8h"), new Card("Jh"));
        Evaluator e = new Evaluator();
        HandCategory hc = e.handCategory(h);
        assertEquals(StraightFlush.class, hc.getClass());
    }

    @Test
    public void handValueIsHighCard()
    {
        Hand h = new Hand(new Card("7h"), new Card("Th"), new Card("2c"), new Card("8h"), new Card("Jh"));
        Evaluator e = new Evaluator();
        HandCategory hc = e.handCategory(h);
        assertEquals(HighCard.class, hc.getClass());
    }
}
