package updating;

import system.Properties;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class UserWordRule implements Rule{

    @Override
    public int applyRule(int maxWeight) {
        return Properties.USER_WEIGHT;
    }
}
