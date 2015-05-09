package algorithms.segmenttree.build;

import algorithms.segmenttree.SegmentTree;
import model.dictionary.Dictionary;
import model.dictionary.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class SegmentTstTreeFactory {

    public static void buildSegmentTst(SegmentTree segmentTree, Dictionary dictionary) {
        segmentTree.load(dictionary.getWords(), true);
    }

    public static void buildWeightedSegmentTst(SegmentTree segmentTree, Dictionary dictionary) {
        dictionary.sortDictionaryByWeight();
        buildSegmentTst(segmentTree, dictionary);
    }


    public static void buildRandomSegmentTst(SegmentTree segmentTree, Dictionary dictionary) {
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
