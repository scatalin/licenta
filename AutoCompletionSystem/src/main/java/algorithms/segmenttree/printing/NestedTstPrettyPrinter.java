package algorithms.segmenttree.printing;

import algorithms.SearchTree;
import algorithms.segmenttree.intern.SegmentNode;
import algorithms.segmenttree.parsing.SegmentTreeParser;
import algorithms.utils.AbstractPrettyPrinter;

import java.util.List;

/**
 * Created by gstan on 16.03.2015.
 */
public class NestedTstPrettyPrinter extends AbstractPrettyPrinter {

    private final SegmentNode root;
    private List<SearchTree> trees;

    public NestedTstPrettyPrinter(SegmentNode root) {
        this.root = root;
    }

    @Override
    protected void initPrint() {
        SegmentTreeParser parser = new SegmentTreeParser(root);
        trees = parser.findAllTst();
    }

    @Override
    protected void constructString(StringBuilder stringBuilder) {
        for (SearchTree tst : trees) {
            stringBuilder.append(tst.print());
            stringBuilder.append("\n\n\n");
        }
    }
}
