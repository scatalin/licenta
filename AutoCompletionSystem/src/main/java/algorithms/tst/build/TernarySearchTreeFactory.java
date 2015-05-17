package algorithms.tst.build;

import algorithms.SearchTree;
import algorithms.SearchTreeFactory;
import algorithms.tst.TernarySearchTreeRecursive;
import dictionary.Dictionary;
import dictionary.Word;

import java.util.List;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class TernarySearchTreeFactory {

    public static SearchTree createTst() {
        return new TernarySearchTreeRecursive();
    }

    public static SearchTree buildTst(List<Word> dictionary) {
        SearchTree tst = SearchTreeFactory.createTst();
        tst.load(dictionary, false);
        return tst;
    }

    public static SearchTree buildWeightedTst(Dictionary dictionary) {
        return buildTst(dictionary.getWordsSortedByWeight());
    }
}
