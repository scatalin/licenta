package algorithms;

import dictionary.Word;

import java.util.List;

/**
 * Created by Catalin on 4/5/2015 .
 */
public interface SearchTree {

    void load(List<Word> words, boolean reset);

    void load(List<Word> words);

    void insert(Word word);

    void setNumberOfSuggestions(int numberOfSuggestions);

    void resetCompletion();

    List<String> getSuggestions(String prefix);

    void update(String word, int weight);

    void reset();

    String print();
}
