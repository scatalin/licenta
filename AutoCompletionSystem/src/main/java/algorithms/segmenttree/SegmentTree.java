package algorithms.segmenttree;

import algorithms.segmenttree.intern.Interval;
import algorithms.segmenttree.intern.SegmentNode;
import algorithms.segmenttree.intern.SegmentTreeData;
import algorithms.segmenttree.printing.SegmentTreePrettyPrinter;
import algorithms.utils.PrettyPrinter;
import model.dictionary.Word;

/**
 * Created by Catalin on 3/13/2015 .
 */
public class SegmentTree {

    private SegmentNode root;
    private int size;
    private SegmentTreeData data;

    public SegmentTree() {
        this.root = new SegmentNode();
    }

    public void setMaximumSize(int size) {
        this.size = size;
    }

    public void buildSegmentTree() {
        root.setLeftLimit(0);
        root.setRightLimit(size);
        data = new SegmentTreeData(size);
        data.parseInterval();
        recursiveBuildSegmentTree(root, new Interval(0, size));
    }

    private int recursiveBuildSegmentTree(SegmentNode node, Interval interval) {
        node.setDepth(1);
        int middle = interval.leftLimit + ((interval.rightLimit - interval.leftLimit) / 2);
        if (interval.leftLimit == interval.rightLimit) {
            node.createTst();
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

    public int search(String s) {
        int position = data.getInterval(s.substring(0, 1));
        return recursiveSearch(root, s, position);
    }

    private int recursiveSearch(SegmentNode node, String s, int position) {
        if (node.isLeaf()) {
            return node.searchTst(s);
        } else {
            int left = node.getLeftLimit();
            int right = node.getRightLimit();
            int middle = left + ((right - left) / 2);
            return recursiveSearch(position <= middle ? node.getLeftChild() : node.getRightChild(), s, position);
        }
    }

    public void insert(Word word) {
        int position = data.getInterval(word.getWord().substring(0, 1));
        recursiveInsert(root, word, position);
    }

    private void recursiveInsert(SegmentNode node, Word word, int position) {
        if (node.isLeaf()) {
            node.insertInTst(word);
        } else {
            int left = node.getLeftLimit();
            int right = node.getRightLimit();
            int middle = left + ((right - left) / 2);
            recursiveInsert(position <= middle ? node.getLeftChild() : node.getRightChild(), word, position);
        }
    }

    public String printTree() {
        PrettyPrinter printer = new SegmentTreePrettyPrinter(root);
        return printer.prettyPrint();
    }

    public String printSubtrees() {
        PrettyPrinter printer = new SegmentTreePrettyPrinter(root);
        return printer.prettyPrint();
    }

}
