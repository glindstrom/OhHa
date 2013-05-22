

package poker.handCategories;

import poker.Evaluator;
import poker.Hand;

public class TwoPair extends HandCategory
{
    public TwoPair(Hand h)
    {
        super(6, h);
    }

    @Override
    public int compareInternally(HandCategory otherHandCategory)
    {
        TwoPair other = (TwoPair) otherHandCategory;
        int [] handFrequenciesThis = Evaluator.calculateCardFrequencies(this.getHand());
        int [] handFrequenciesOther = Evaluator.calculateCardFrequencies(other.getHand());
        for (int i = 0; i < handFrequenciesThis.length; i++)
        {
            if (handFrequenciesThis[i] == 2 && handFrequenciesOther[i] < 2)
            {
                return -1;
            }
            if (handFrequenciesOther[i] == 2 && handFrequenciesThis[i] < 2)
            {
                return 1;
            }
        }
        return compareKickers(handFrequenciesThis, handFrequenciesOther)  ;
    }


}
