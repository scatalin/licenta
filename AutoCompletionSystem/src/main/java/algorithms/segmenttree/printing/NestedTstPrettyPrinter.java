package algorithms.segmenttree.printing;

import algorithms.SearchTree;
import algorithms.segmenttree.SegmentTreeLinear;
import algorithms.utils.AbstractPrettyPrinter;

import java.util.List;

/**
 * Created by gstan on 16.03.2015.
 */
public class NestedTstPrettyPrinter extends AbstractPrettyPrinter {

    private final SegmentTreeLinear tree;
    private List<SearchTree> trees;

    public NestedTstPrettyPrinter(SegmentTreeLinear tree) {
        this.tree = tree;
    }

    @Override
    protected void initPrint() {
        trees = tree.getTrees();
    }

    @Override
    protected void constructString(StringBuilder stringBuilder) {
        for (SearchTree tst : trees) {
            stringBuilder.append(tst.print());
            stringBuilder.append("\n\n\n");
        }
    }
}
