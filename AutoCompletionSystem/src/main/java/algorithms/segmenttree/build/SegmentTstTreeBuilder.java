package algorithms.segmenttree.build;

import algorithms.SearchTree;
import algorithms.SearchTreeFactory;
import dictionary.Dictionary;
import dictionary.Word;

import java.util.List;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class SegmentTstTreeBuilder {

    public static SearchTree buildWeightedSegmentTst(Dictionary dictionary) {
        SearchTree tree = SearchTreeFactory.getCompletionTree();
        fillTreeWithWords(dictionary.getWordsSortedByWeight(), tree);
        return tree;
    }

    public static SearchTree buildSegmentTst(Dictionary dictionary) {
        SearchTree tree = SearchTreeFactory.getCompletionTree();
        fillTreeWithWords(dictionary.asList(), tree);
        return tree;
    }

    private static void fillTreeWithWords(List<Word> dictionary, SearchTree tree) {
        tree.load(dictionary, true);
    }
}
