package algorithms.segmenttree;

import algorithms.segmenttree.printing.SegmentTreePrettyPrinter;
import algorithms.utils.PrettyPrinter;

/**
 * Created by Catalin on 3/13/2015 .
 */
public class SegmentTree {

    private SegmentNode root;
    private PrettyPrinter printer;

    public SegmentTree() {
        this.root = new SegmentNode();
        printer = new SegmentTreePrettyPrinter(root);
    }

    public void setMaximumSize(int size){
        root.setRightLimit(size);
    }

    public void buildSegmentTree(){
        root.createChildren();
    }

    public String print(){
        return printer.prettyPrint();
    }
}
