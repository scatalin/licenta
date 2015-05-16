package algorithms.segmenttree;

import algorithms.SearchTree;
import algorithms.segmenttree.intern.Interval;
import algorithms.segmenttree.intern.SegmentNode;
import algorithms.segmenttree.intern.SegmentTreeData;
import algorithms.segmenttree.printing.SegmentTreePrettyPrinter;
import algorithms.utils.PrettyPrinter;
import dictionary.Word;
import system.Properties;

import java.util.List;

/**
 * Created by Catalin on 3/13/2015 .
 */
public class SegmentTree implements SearchTree {

    private int size = Properties.SEGMENT_SIZE;
    private SegmentNode root;
    private SegmentTreeData data;

    public SegmentTree() {
        this.root = new SegmentNode();
        buildSegmentTree();
    }

    private void buildSegmentTree() {
        root = new SegmentNode(new Interval(0,size));
        data = new SegmentTreeData(size);
        recursiveBuildSegmentTree(root, new Interval(0, size));
    }

    private void recursiveBuildSegmentTree(SegmentNode node, Interval interval) {
        int middle = interval.leftLimit + ((interval.rightLimit - interval.leftLimit) / 2);
        if (interval.leftLimit == interval.rightLimit) {
            node.createTst();
            return;
        }

        Interval leftInterval = new Interval();
        leftInterval.leftLimit = interval.leftLimit;
        leftInterval.rightLimit = middle;
        node.setLeftChild(new SegmentNode(leftInterval));
        recursiveBuildSegmentTree(node.getLeftChild(), leftInterval);

        Interval rightInterval = new Interval();
        rightInterval.leftLimit = middle + 1;
        rightInterval.rightLimit = interval.rightLimit;
        node.setRightChild(new SegmentNode(rightInterval));
        recursiveBuildSegmentTree(node.getRightChild(), rightInterval);
    }

    @Override
    public void insert(Word word) {
        int position = data.getPosition(word.getWord().substring(0, 1));
        recursiveInsert(root, word, position);
    }

    private void recursiveInsert(SegmentNode node, Word word, int position) {
        if (node.isLeaf()) {
            node.getTree().insert(word);
        } else {
            int left = node.getLeftLimit();
            int right = node.getRightLimit();
            int middle = left + ((right - left) / 2);
            recursiveInsert((position <= middle) ? node.getLeftChild() : node.getRightChild(), word, position);
        }
    }

    @Override
    public void update(String word, int weight) {
        int position = data.getPosition(word.substring(0, 1));
        recursiveUpdate(root, word, weight, position);
    }

    private void recursiveUpdate(SegmentNode node, String word, int weight, int position) {
        if (node.isLeaf()) {
            node.getTree().update(word, weight);
        } else {
            int left = node.getLeftLimit();
            int right = node.getRightLimit();
            int middle = left + ((right - left) / 2);
            recursiveUpdate((position <= middle) ? node.getLeftChild() : node.getRightChild(), word, weight, position);
        }
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
        int position = data.getPosition(prefix.substring(0, 1));
        return recursiveGetNextTopK(root, prefix, position);
    }

    private List<Word> recursiveGetNextTopK(SegmentNode node, String prefix, int position) {
        if (node.isLeaf()) {
            return node.getTree().getNextTopK(prefix);
        } else {
            int left = node.getLeftLimit();
            int right = node.getRightLimit();
            int middle = left + ((right - left) / 2);
            return recursiveGetNextTopK((position <= middle) ? node.getLeftChild() : node.getRightChild(), prefix, position);
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
        PrettyPrinter printer = new SegmentTreePrettyPrinter(root);
        return printer.prettyPrint();
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
}
