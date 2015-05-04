package algorithms.segmenttree;

import algorithms.SearchTree;
import algorithms.segmenttree.intern.Interval;
import algorithms.segmenttree.intern.SegmentNode;
import algorithms.segmenttree.intern.SegmentTreeData;
import algorithms.segmenttree.printing.NestedTstPrettyPrinter;
import algorithms.segmenttree.printing.SegmentTreePrettyPrinter;
import algorithms.utils.PrettyPrinter;
import model.dictionary.Word;
import system.Properties;

import java.util.List;

/**
 * Created by Catalin on 3/13/2015 .
 */
public class SegmentTree implements SearchTree {

    private SegmentNode root;
    private int size;
    private SegmentTreeData data;

    public SegmentTree() {
        this.root = new SegmentNode();
        size = Properties.SEGMENT_SIZE;
    }

    public void setMaximumSize(int size) {
        this.size = size;
    }

    public void buildSegmentTree() {
        root = new SegmentNode();
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

    @Override
    public void insert(Word word) {
        int position = data.getInterval(word.getWord().substring(0, 1));
        recursiveInsert(root, word, position);
    }

    private void recursiveInsert(SegmentNode node, Word word, int position) {
        if (node.isLeaf()) {
            node.getTree().insert(word);
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
        PrettyPrinter printer = new NestedTstPrettyPrinter(root);
        return printer.prettyPrint();
    }

    @Override
    public void load(List<Word> words, boolean reset) {
        if (reset) {
            recursiveReset(root);
        }
        for (Word word : words) {
            insert(word);
        }
    }

    private void recursiveReset(SegmentNode node) {
        if (node.isLeaf()) {
            node.getTree().reset();
        } else {
            recursiveReset(node.getLeftChild());
            recursiveReset(node.getRightChild());
        }
    }

    @Override
    public List<Word> getNextTopK(String prefix) {
        int position = data.getInterval(prefix.substring(0, 1));
        return recursiveGetNextTopK(root, prefix, position);
    }

    private List<Word> recursiveGetNextTopK(SegmentNode node, String prefix, int position) {
        if (node.isLeaf()) {
            return node.getTree().getNextTopK(prefix);
        } else {
            int left = node.getLeftLimit();
            int right = node.getRightLimit();
            int middle = left + ((right - left) / 2);
            return recursiveGetNextTopK(position <= middle ? node.getLeftChild() : node.getRightChild(), prefix, position);
        }
    }

    @Override
    public void setK(int k) {
        recursiveSetK(root, k);
    }

    private void recursiveSetK(SegmentNode node, int k) {
        if (node.isLeaf()) {
            node.getTree().setK(k);
        } else {
            recursiveSetK(node.getLeftChild(), k);
            recursiveSetK(node.getRightChild(), k);
        }
    }

    @Override
    public void resetSearchK() {
        recursiveResetSearchK(root);
    }

    private void recursiveResetSearchK(SegmentNode node) {
        if (node.isLeaf()) {
            node.getTree().resetSearchK();
        } else {
            recursiveResetSearchK(node.getLeftChild());
            recursiveResetSearchK(node.getRightChild());
        }
    }

    @Override
    public String print() {
        return printSubtrees();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public void reset() {
        recursiveReset(root);
        buildSegmentTree();
    }

//    public int search(String s) {
//        return recursiveSearch(root, s, position);
//        int position = data.getInterval(s.substring(0, 1));
//    }
//
//    private int recursiveSearch(SegmentNode node, String s, int position) {
//        if (node.isLeaf()) {
//            return node.getTree().search(s);
//        } else {
//            int left = node.getLeftLimit();
//            int right = node.getRightLimit();
//            int middle = left + ((right - left) / 2);
//            return recursiveSearch(position <= middle ? node.getLeftChild() : node.getRightChild(), s, position);
//        }
}
