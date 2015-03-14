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
    private List<List<SegmentElement>> matrix;
    private StringBuilder stringBuilder;

    public SegmentTreePrettyPrinter(SegmentNode root) {
        this.root = root;
    }

    @Override
    protected void constructString(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
        recursiveConstructMatrix(root, 0);
        for (List<SegmentElement> line : matrix) {
            for (SegmentElement node : line) {
                if(node.node != null) {
                    addIdentation(node.level-1, 0);
                    stringBuilder.append(node.node.toString());
                    addIdentation(node.level-1, 1);
                }
                else {
                    addIdentation(1,0);
                }
            }
            stringBuilder.append("\n");
        }
    }

    private void addIdentation(int depth, int extra) {
        double size = Math.pow(2, depth);
        size -= extra;
        for (int i = 0; i < size; i++) {
            stringBuilder.append("       ");
        }
    }

    private void recursiveConstructMatrix(SegmentNode node, int level) {
        if (node == null) {
            if(level == matrix.size()-1){
                addNonExistentSon();
            }
            return;
        }
        matrix.get(level).add(new SegmentElement(matrix.size()-level,node));
        recursiveConstructMatrix(node.getLeftChild(), level + 1);
        recursiveConstructMatrix(node.getRightChild(), level + 1);
    }

    private void addNonExistentSon(){
        matrix.get(matrix.size()-1).add(new SegmentElement(0,null));
    }

    @Override
    protected void initPrint() {
        matrix = new ArrayList<List<SegmentElement>>();
        for (int i = 0; i < root.getDepth(); i++) {
            matrix.add(new ArrayList<SegmentElement>());
        }
    }

    private class SegmentElement{
        int level;
        SegmentNode node;

        public SegmentElement(int level, SegmentNode node) {
            this.level = level;
            this.node = node;
        }
    }

}
