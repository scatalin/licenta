package algorithms.segmenttree.printing;

import algorithms.Data;
import algorithms.SearchTreeFactory;
import algorithms.utils.AbstractPrettyPrinter;

/**
 * Created by Catalin on 3/13/2015 .
 */
public class SegmentTreePrettyPrinter extends AbstractPrettyPrinter {

    private Data data;
    private StringBuilder stringBuilder;

    public SegmentTreePrettyPrinter() {
        this.data = SearchTreeFactory.createData();
    }

    @Override
    protected void constructString(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;

        for (Character character : data.getAlphabet()) {
            stringBuilder.append(character).append(":").append(data.getPosition(character+""));
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
