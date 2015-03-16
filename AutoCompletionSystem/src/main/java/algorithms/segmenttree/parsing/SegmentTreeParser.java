package algorithms.segmenttree.parsing;

import algorithms.segmenttree.intern.SegmentNode;
import algorithms.tst.TernarySearchTree;
import model.dictionary.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gstan on 16.03.2015.
 */
public class SegmentTreeParser {
    private SegmentNode root;
    private List<TernarySearchTree> leafs;

    public SegmentTreeParser(SegmentNode root) {
        this.root = root;
    }

    public List<TernarySearchTree> findAllTst(){
        leafs = new ArrayList<TernarySearchTree>();
        recursiveFindAll(root);
        return leafs;
    }

    private void recursiveFindAll(SegmentNode node) {
        if (node.isLeaf()) {
            leafs.add(node.getTst());
        } else {
            recursiveFindAll(node.getLeftChild());
            recursiveFindAll(node.getRightChild());
        }
    }
}
