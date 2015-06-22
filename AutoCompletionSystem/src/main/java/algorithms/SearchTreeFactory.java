package algorithms;

import algorithms.segmenttree.SegmentTreeLinear;
import algorithms.tst.TernarySearchTreeRecursive;

/**
 * Created by Catalin on 5/16/2015 .
 */
public class SearchTreeFactory {

    private static final SearchTree globalSearchTree = new SegmentTreeLinear();

    private static Data data;

    public static SearchTree createTst() {
        return new TernarySearchTreeRecursive();
    }

    public static SearchTree getCompletionTree() {
        return globalSearchTree;
    }

    public static Data createData() {
        if (data == null) {
            data = new Data();
        }
        return data;
    }
}
