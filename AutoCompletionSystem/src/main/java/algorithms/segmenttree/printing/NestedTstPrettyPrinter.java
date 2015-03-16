package algorithms.segmenttree.printing;

import algorithms.segmenttree.intern.SegmentNode;
import algorithms.segmenttree.intern.SegmentTreeData;
import algorithms.segmenttree.parsing.SegmentTreeParser;
import algorithms.tst.TernarySearchTree;
import algorithms.utils.AbstractPrettyPrinter;
import algorithms.utils.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gstan on 16.03.2015.
 */
public class NestedTstPrettyPrinter extends AbstractPrettyPrinter {

    private SegmentNode root;
    private List<TernarySearchTree> trees;
    private StringBuilder stringBuilder;

    public NestedTstPrettyPrinter(SegmentNode root) {
        this.root = root;
    }

    @Override
    protected void constructString(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
        for (TernarySearchTree tst : trees) {
            stringBuilder.append(tst.print());
            stringBuilder.append("\n\n\n");
        }
    }

    @Override
    protected void initPrint() {
        SegmentTreeParser parser = new SegmentTreeParser(root);
        trees = parser.findAllTst();
    }
}
