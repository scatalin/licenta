package algorithms.segmenttree;

/**
 * Created by gstan on 13.03.2015.
 */
public class SegmentNode {

    private Interval interval;
    private SegmentNode leftChild;
    private SegmentNode rightChild;

    public SegmentNode() {
        this.interval = new Interval();
    }

    public void setSize(int size){
        interval.rightLimit = size;
    }

    public void buildSegmentTree(){

    }

    //todo add an interval acording to the tree expected size. divide it by some algorithm
}
