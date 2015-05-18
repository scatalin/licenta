package dictionary.inserting;

import dictionary.Word;

/**
 * Created by Catalin on 5/16/2015 .
 */
public class DefaultDictionaryWeightUpdate implements WeightUpdate {

    @Override
    public int updateWeight(Word word, int weight) {
        int initialWeight = word.getWeight();
        word.increaseFrequency(weight);
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
        return false;
    }
}
