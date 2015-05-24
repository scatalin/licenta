package dictionary.inserting;

import dictionary.Word;

/**
 * Created by Catalin on 5/16/2015 .
 */
public interface WeightUpdate {
    int updateWeight(Word word, int weight, int userW, int actW);

    int overrideWeight(Word word, int weight);

    boolean updateModel();
}
