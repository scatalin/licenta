package dictionary.inserting;

import dictionary.Word;

/**
 * Created by Catalin on 5/16/2015 .
 */
public interface WeightUpdate {
    void updateWeight(Word word, int weight);

    void overrideWeight(Word word, int weight);
}
