package dictionary.inserting;

import dictionary.Word;

/**
 * Created by Catalin on 5/16/2015 .
 */
public class DefaultDictionaryWeightUpdate implements WeightUpdate {

    @Override
    public void updateWeight(Word word, int weight) {
        word.increaseFrequency(weight);
    }

    @Override
    public void overrideWeight(Word word, int weight) {
        word.setWeight(weight);
    }

    @Override
    public boolean updateModel() {
        return false;
    }
}
