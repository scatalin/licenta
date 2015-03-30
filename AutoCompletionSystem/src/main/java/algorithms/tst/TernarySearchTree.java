package algorithms.tst;

import model.dictionary.Word;

import java.util.List;

/**
 * Created by Catalin on 2/21/2015.
 */
public interface TernarySearchTree {

    void load(List<Word> words);

    void additionalLoad(List<Word> words);

    int search(String s);

    List<Word> getNextTopK(String prefix);

    void resetSearchK();

    void setK(int k);

    void insert(String word, int weight);

    String print();
}
