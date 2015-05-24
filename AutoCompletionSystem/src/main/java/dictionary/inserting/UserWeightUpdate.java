package dictionary.inserting;

import dictionary.Word;

/**
 * Created by Catalin on 5/16/2015 .
 */
public class UserWeightUpdate implements WeightUpdate {

    @Override
    public int updateWeight(Word word, int weight, int userW, int actW) {
        int initialWeight = word.getWeight();
        word.increaseFrequency(weight);
        word.setUserFrequency(word.getUserFrequency() + userW);
        word.setUserActuality(word.getUserActuality() + actW);
        return initialWeight;
    }

    @Override
    public int overrideWeight(Word word, int weight) {
        int initialWeight = word.getWeight();
        word.setWeight(weight);
        return initialWeight;
    }

    @Override
    public boolean updateModel() {
        return true;
    }
}
