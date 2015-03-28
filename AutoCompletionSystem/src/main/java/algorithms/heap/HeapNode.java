package algorithms.heap;

import algorithms.tst.intern.TstNode;

/**
 * Created by Catalin on 3/28/2015 .
 */
public class HeapNode {
    private TstNode node;
    private String builtWord;

    public HeapNode(TstNode node) {
        this.node = node;
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

    public int compareTo(HeapNode parent) {
        return node.compareTo(parent.getNode());
    }
}
