package updating;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class ActualityRule implements Rule {
    @Override
    public int applyRule(int maxWeight) {
        return maxWeight / 10;
    }
}
