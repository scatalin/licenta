package algorithms.tst.build;

import algorithms.SearchTree;
import algorithms.tst.TernarySearchTreeRecursive;
import model.dictionary.Dictionary;
import model.dictionary.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class SearchTreeFactory {

    public static SearchTree buildTst(Dictionary dictionary) {
        SearchTree tst = new TernarySearchTreeRecursive();
        tst.load(dictionary.getWords(),true);
        return tst;
    }

    public static SearchTree buildWeightedTst(Dictionary dictionary) {
        dictionary.sortDictionaryByWeight();
        return buildTst(dictionary);
    }

    public static SearchTree buildRandomTst(Dictionary dictionary) {
        SearchTree tst = new TernarySearchTreeRecursive();
        List<Word> words = new ArrayList<Word>(dictionary.getWords());
        int middlePosition = words.size() / 2;
        Word word = words.remove(middlePosition);
        tst.insert(word);
        Random random = new Random();
        while (words.size() > 0) {
            int position = random.nextInt(words.size());
            word = words.remove(position);
            tst.insert(word);
        }
        return tst;
    }
}
