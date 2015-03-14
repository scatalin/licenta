package algorithms.segmenttree.printing;

import algorithms.segmenttree.SegmentNode;
import algorithms.utils.AbstractPrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 3/13/2015 .
 */
public class SegmentTreePrettyPrinter extends AbstractPrettyPrinter {

    private SegmentNode root;
    private List<List<SegmentNode>> matrix;
    private StringBuilder stringBuilder;

    public SegmentTreePrettyPrinter(SegmentNode root) {
        this.root = root;
    }

    @Override
    protected void constructString(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
        recursiveConstructMatrix(root, 0);
        for( List<SegmentNode> line : matrix){
            for( SegmentNode node : line){
                addIdentation(node.getDepth());
                stringBuilder.append(node.toStringInterval());
                addIdentation(node.getDepth());
            }
            stringBuilder.append("\n");
        }
    }

    private void addIdentation( int depth) {
        for (int i = 0; i < depth; i++) {
            stringBuilder.append("   ");
        }
    }

    private void recursiveConstructMatrix(SegmentNode node, int level) {
        if(node == null){
            return;
        }
        matrix.get(level).add(node);
        recursiveConstructMatrix(node.getLeftChild(), level + 1);
        recursiveConstructMatrix(node.getLeftChild(), level + 1);
    }

    @Override
    protected void initPrint() {
        matrix = new ArrayList<List<SegmentNode>>();
        for(int i = 0; i< root.getDepth(); i++){
            matrix.add(new ArrayList<SegmentNode>());
        }
    }

}
