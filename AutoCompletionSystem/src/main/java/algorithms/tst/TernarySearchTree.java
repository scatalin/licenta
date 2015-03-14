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

    List<Word> getTopK(String prefix);

    void setK(int k);

    void insert(String word, int weight);

    String print();
}
