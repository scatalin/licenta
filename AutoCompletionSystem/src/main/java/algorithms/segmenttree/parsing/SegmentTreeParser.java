package algorithms.segmenttree.parsing;

import algorithms.SearchTree;
import algorithms.segmenttree.intern.SegmentNode;
import algorithms.tst.intern.TstNode;
import algorithms.tst.parsing.TreeParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gstan on 16.03.2015.
 */
public class SegmentTreeParser {
    private final SegmentNode root;
    private final TreeParser parser;
    private List<SearchTree> leafs;

    public SegmentTreeParser(SegmentNode root) {
        this.root = root;
        parser = new TreeParser();
    }

    public List<SearchTree> findAllTst() {
        leafs = new ArrayList<>();
        recursiveFindAll(root);
        return leafs;
    }

    private void recursiveFindAll(SegmentNode node) {
        if (node.isLeaf()) {
            if (!node.getTree().isEmpty()) {
                leafs.add(node.getTree());
            }
        } else {
            recursiveFindAll(node.getLeftChild());
            recursiveFindAll(node.getRightChild());
        }
    }

    public int getDepth(){
        return recursiveGetDepth(root);
    }

    private int recursiveGetDepth(SegmentNode node) {
        if (node.isLeaf()) {
            return 1;
        }

        int depthLeft = recursiveGetDepth(node.getLeftChild());
        int depthRight = recursiveGetDepth(node.getRightChild());

        return Math.max(depthLeft,depthRight)+1;
    }

    public int countNodes() {
        return recursiveCountNodes(root);
    }

    private int recursiveCountNodes(SegmentNode node) {
        if (node.isLeaf()) {
            return parser.countNodes((TstNode) node.getTree().getRoot());
        }

        int number = 0;
        number += recursiveCountNodes(node.getLeftChild());
        number += recursiveCountNodes(node.getRightChild());

        return number;
    }
}
