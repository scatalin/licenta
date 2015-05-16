package algorithms.segmenttree.build;

import algorithms.SearchTree;
import dictionary.Dictionary;
import dictionary.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class SegmentTstTreeFactory {

    public static void buildSegmentTst(SearchTree segmentTree, List<Word> dictionary) {
        segmentTree.load(dictionary, true);
    }

    public static void buildWeightedSegmentTst(SearchTree segmentTree, Dictionary dictionary) {
        buildSegmentTst(segmentTree, dictionary.getWordsSortedByWeight());
    }


    public static void buildRandomSegmentTst(SearchTree segmentTree, Dictionary dictionary) {
        List<Word> words = new ArrayList<>(dictionary.getWords());
        int middlePosition = words.size() / 2;
        Word word = words.remove(middlePosition);
        segmentTree.insert(word);
        Random random = new Random();
        while (!words.isEmpty()) {
            int position = random.nextInt(words.size());
            word = words.remove(position);
            segmentTree.insert(word);
        }
    }
}
