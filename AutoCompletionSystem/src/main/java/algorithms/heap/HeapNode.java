package algorithms.heap;

import algorithms.tst.intern.TstNode;

/**
 * Created by Catalin on 3/28/2015 .
 */
public class HeapNode {
    private TstNode node;
    private String builtWord;

    public HeapNode(TstNode node) {
        this(node, "");
    }

    public HeapNode(TstNode node, String startWord) {
        this.node = node;
        builtWord = startWord;
    }

    public String getBuiltWord() {
        return builtWord;
    }

    public void setNode(TstNode node) {
        this.node = node;
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

    public HeapNode clone(){
        return new HeapNode(node,builtWord);
    }

    @Override
    public String toString() {
        return "HeapNode{" +
                "builtWord='" + builtWord + '\'' +
                ", node=" + node.getCharacter()+"|"+node.getSpecificWeight() +
                '}';
    }
}
