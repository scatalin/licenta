package algorithms.segmenttree;

import algorithms.segmenttree.intern.Interval;
import algorithms.segmenttree.intern.SegmentNode;
import algorithms.segmenttree.printing.SegmentTreePrettyPrinter;
import algorithms.utils.PrettyPrinter;

/**
 * Created by Catalin on 3/13/2015 .
 */
public class SegmentTree {

    private SegmentNode root;
    private PrettyPrinter printer;
    private int size;

    public SegmentTree() {
        this.root = new SegmentNode();
        printer = new SegmentTreePrettyPrinter(root);
    }

    public void setMaximumSize(int size) {
        this.size = size;
    }

    public void buildSegmentTree() {
        root.setLeftLimit(0);
        root.setRightLimit(size);
        recursiveBuildSegmentTree(root, new Interval(0, size));
    }

    private int recursiveBuildSegmentTree(SegmentNode node, Interval interval) {
        node.setDepth(1);
        int middle = interval.leftLimit + ((interval.rightLimit - interval.leftLimit) / 2);
        if (interval.leftLimit == interval.rightLimit) {
            return 1;
        }

        int maxDepth = 0;

        Interval leftInterval = new Interval();
        leftInterval.leftLimit = interval.leftLimit;
        leftInterval.rightLimit = middle;
        node.setLeftChild(new SegmentNode(leftInterval));
        int leftDepth = recursiveBuildSegmentTree(node.getLeftChild(), leftInterval);
        if (maxDepth < leftDepth) {
            maxDepth = leftDepth;
        }

        Interval rightInterval = new Interval();
        rightInterval.leftLimit = middle + 1;
        rightInterval.rightLimit = interval.rightLimit;
        node.setRightChild(new SegmentNode(rightInterval));
        int rightDepth = recursiveBuildSegmentTree(node.getRightChild(), rightInterval);
        if (maxDepth < rightDepth) {
            maxDepth = rightDepth;
        }

        node.setDepth(maxDepth + 1);
        return node.getDepth();
    }

    public String print() {
        return printer.prettyPrint();
    }
}
