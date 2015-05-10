package algorithms;

import dictionary.Word;

import java.util.List;

/**
 * Created by Catalin on 4/5/2015 .
 */
public interface SearchTree {

    void load(List<Word> words, boolean reset);

    List<Word> getNextTopK(String prefix);

    void resetSearchK();

    void setK(int k);

    String print();

    void insert(Word word);

    boolean isEmpty();

    Object getRoot();

    void reset();
}
