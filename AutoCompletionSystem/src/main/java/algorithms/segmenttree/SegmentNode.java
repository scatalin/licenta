package algorithms.segmenttree;

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

    public int createChildren() {
        depth = 1;
        interval.middle = interval.leftLimit + ((interval.rightLimit - interval.leftLimit) / 2);
        if (interval.leftLimit == interval.rightLimit) {
            return 1;
        }

        int maxDepth = 0;

        Interval leftInterval = new Interval();
        leftInterval.leftLimit = interval.leftLimit;
        leftInterval.rightLimit = interval.middle;
        leftChild = new SegmentNode(leftInterval);
        int leftDepth = leftChild.createChildren();
        if (maxDepth < leftDepth) {
            maxDepth = leftDepth;
        }

        Interval rightInterval = new Interval();
        rightInterval.leftLimit = interval.middle + 1;
        rightInterval.rightLimit = interval.rightLimit;
        rightChild = new SegmentNode(rightInterval);
        int rightDepth = rightChild.createChildren();
        if (maxDepth < rightDepth) {
            maxDepth = rightDepth;
        }

        depth = maxDepth + 1;
        return depth;
    }

    private class Interval {
        int leftLimit = 0;
        int rightLimit = 0;
        int middle = 0;

        @Override
        public String toString() {
            return "[" + leftLimit + "," + rightLimit + "]";
        }
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
