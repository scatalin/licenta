package algorithms;

import algorithms.segmenttree.SegmentTreeLinear;
import algorithms.tst.TernarySearchTreeRecursive;

/**
 * Created by Catalin on 5/16/2015 .
 */
public class SearchTreeFactory {

    private static Data data;

    public static SearchTree createTst() {
        return new TernarySearchTreeRecursive();
    }

    public static SearchTree createCompletionTree() {
        return new SegmentTreeLinear();
    }

    public static Data createData() {
        if (data == null) {
            data = new Data();
        }
        return data;
    }
}
