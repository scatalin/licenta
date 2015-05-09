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
    private int depth;
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

    public void setLeftLimit(int leftLimit) {
        this.interval.leftLimit = leftLimit;
    }

    public int getRightLimit() {
        return interval.rightLimit;
    }

    public void setRightLimit(int rightLimit) {
        this.interval.rightLimit = rightLimit;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
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
