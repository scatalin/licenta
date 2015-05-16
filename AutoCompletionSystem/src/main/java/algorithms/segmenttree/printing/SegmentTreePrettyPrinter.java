package algorithms.segmenttree.printing;

import algorithms.segmenttree.SegmentTreeLinear;
import algorithms.segmenttree.intern.SegmentTreeData;
import algorithms.utils.AbstractPrettyPrinter;

/**
 * Created by Catalin on 3/13/2015 .
 */
public class SegmentTreePrettyPrinter extends AbstractPrettyPrinter {

    private SegmentTreeData data;
    private StringBuilder stringBuilder;

    public SegmentTreePrettyPrinter(SegmentTreeLinear tree) {
        this.data = tree.getData();
    }

    @Override
    protected void constructString(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;

        for (String character : data.getAlphabet()) {
            stringBuilder.append(character).append(":").append(data.getPosition(character));
            addIndentation();
        }
        stringBuilder.append("\n");
    }


    private void addIndentation() {
        stringBuilder.append("       ");
    }

    @Override
    protected void initPrint() {
    }
}
