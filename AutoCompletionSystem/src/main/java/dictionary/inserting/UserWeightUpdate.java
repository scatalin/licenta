package dictionary.inserting;

import dictionary.Word;

/**
 * Created by Catalin on 5/16/2015 .
 */
public class UserWeightUpdate implements WeightUpdate {

    @Override
    public int updateWeight(Word word, int weight) {
        return word.getWeight();
    }

    @Override
    public int overrideWeight(Word word, int weight) {
        return word.getWeight();
    }

    @Override
    public boolean updateModel() {
        return true;
    }
}
