package algorithms;

import algorithms.segmenttree.SegmentTreeLinear;
import algorithms.tst.TernarySearchTreeRecursive;

/**
 * Created by Catalin on 5/16/2015 .
 */
public class SearchTreeFactory {

    public static SearchTree createTst() {
        return new TernarySearchTreeRecursive();
    }

    public static SearchTree createCompletionTree(){
        return new SegmentTreeLinear();
    }
}
