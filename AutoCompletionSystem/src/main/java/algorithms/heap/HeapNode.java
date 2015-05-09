package algorithms.heap;

import algorithms.tst.intern.TstNode;

/**
 * Created by Catalin on 3/28/2015 .
 */
public class HeapNode {
    protected TstNode node;
    private String builtWord;

    public HeapNode(TstNode node, String startWord) {
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

    public int compareTo(HeapNode parent) {
        return node.compareTo(parent.getNode());
    }

    public HeapNode clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new HeapNode(node, builtWord);
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
