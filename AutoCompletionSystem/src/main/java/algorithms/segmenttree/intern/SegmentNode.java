package algorithms.segmenttree.intern;

import algorithms.SearchTree;
import algorithms.tst.TernarySearchTreeRecursive;
import algorithms.tst.build.SearchTreeFactory;

/**
 * Created by gstan on 13.03.2015.
 */
public class SegmentNode {

    private final Interval interval;
    private SegmentNode leftChild;
    private SegmentNode rightChild;
    private SearchTree tst;

    public SegmentNode() {
        this(new Interval());
    }

    public SegmentNode(Interval interval) {
        this.interval = interval;
        tst = new TernarySearchTreeRecursive();
    }

    public int getLeftLimit() {
        return interval.leftLimit;
    }

    public int getRightLimit() {
        return interval.rightLimit;
    }

    public SegmentNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(SegmentNode leftChild) {
        this.leftChild = leftChild;
    }

    public SegmentNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(SegmentNode rightChild) {
        this.rightChild = rightChild;
    }

    public String toString() {
        String left = interval.leftLimit + "";
        if (interval.leftLimit < 10) {
            left = left + " ";
        }
        String right = interval.rightLimit + "";
        if (interval.rightLimit < 10) {
            right = right + " ";
        }
        return "[" + left + "," + right + "]";
    }

    public void createTst() {
        tst = SearchTreeFactory.buildTst();
    }

    public boolean isLeaf() {
        return (interval.rightLimit - interval.leftLimit) == 0;
    }

    public SearchTree getTree() {
        return tst;
    }
}
