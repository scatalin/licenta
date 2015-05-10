package algorithms.heap;

import algorithms.tst.intern.TstNode;

/**
 * Created by Catalin on 3/28/2015 .
 */
public class HeapTreeNode implements Comparable {
    protected TstNode node;
    private String builtWord;

    public HeapTreeNode(TstNode node, String startWord) {
        this.node = node;
        builtWord = startWord;
    }

    public String getBuiltWord() {
        return builtWord;
    }

    public void addCharacter(Character character) {
        builtWord = builtWord + character;
    }

    public TstNode getNode() {
        return node;
    }

    public void setNode(TstNode node) {
        this.node = node;
    }

    public int compareTo(Object parent) {
        return node.compareTo(((HeapTreeNode) parent).getNode());
    }

    public HeapTreeNode clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new HeapTreeNode(node, builtWord);
    }

    @Override
    public String toString() {
        return "{" +
                "W=" + builtWord +
                ", n=" + node.getCharacter() + "|" + node.getSpecificWeight() +
                (node.isEndWord() ? "|w" : "") +
                '}';
    }
}