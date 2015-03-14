package algorithms.segmenttree.intern;

/**
 * Created by gstan on 13.03.2015.
 */
public class SegmentNode {

    private Interval interval;
    private SegmentNode leftChild;
    private SegmentNode rightChild;
    private int depth;

    public SegmentNode() {
        interval = new Interval();
    }

    public SegmentNode(Interval interval) {
        this.interval = interval;
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
        String left = interval.leftLimit+"";
        if(interval.leftLimit<10){
            left = left+" ";
        }
        String right = interval.rightLimit+"";
        if(interval.rightLimit<10){
            right = right+" ";
        }
        return "[" + left + "," + right + "]";
    }


}
