package algorithms.tst.build;

import algorithms.SearchTree;
import algorithms.tst.TernarySearchTreeRecursive;
import dictionary.Dictionary;
import dictionary.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class SearchTreeFactory {

    public static SearchTree createTst() {
        return new TernarySearchTreeRecursive();
    }

    public static SearchTree buildTst(List<Word> dictionary) {
        SearchTree tst = new TernarySearchTreeRecursive();
        tst.load(dictionary, true);
        return tst;
    }

    public static SearchTree buildWeightedTst(Dictionary dictionary) {
        return buildTst(dictionary.getWordsSortedByWeight());
    }

    public static SearchTree buildRandomTst(Dictionary dictionary) {
        SearchTree tst = new TernarySearchTreeRecursive();
        List<Word> words = new ArrayList<>(dictionary.getWords());
        int middlePosition = words.size() / 2;
        Word word = words.remove(middlePosition);
        tst.insert(word);
        Random random = new Random();
        while (!words.isEmpty()) {
            int position = random.nextInt(words.size());
            word = words.remove(position);
            tst.insert(word);
        }
        return tst;
    }
}
