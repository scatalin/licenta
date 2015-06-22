package algorithms.graph;

import algorithms.heap.HeapNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 6/17/2015 .
 */
public class HeapGraphNode implements HeapNode<HeapGraphNode> {

    private List<Integer> parentsWeight;
    private int level;
    private Integer nGramWeight;
    private String nGram="";
    private WordNode wordNode;

    public HeapGraphNode(WordNode wordNode, String nGram) {
        this.wordNode = wordNode;
        this.nGram = nGram;
        this.parentsWeight = new ArrayList<>();
    }

    public HeapGraphNode(HeapGraphNode node, WordNode wordNode, Integer newWeight) {
        this.wordNode = wordNode;
        nGram = node.getNGram() + " " + wordNode.getWord();
        parentsWeight = node.getParentsWeight();
        parentsWeight.add(newWeight);
    }

    public void computeWeight() {
        double sum = 0;
        for (Integer aParentsWeight : parentsWeight) {
            sum += 1 / (double) aParentsWeight;
        }
        nGramWeight = (int) (parentsWeight.size() / sum);
    }

    public WordNode getWordNode() {
        return wordNode;
    }

    public List<Integer> getParentsWeight() {
        return parentsWeight;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public HeapGraphNode clone() {
        HeapGraphNode newH = new HeapGraphNode(wordNode,nGram);
        newH.setLevel(level);
        newH.setNGram(nGram);
        newH.setNGramWeight(nGramWeight);
        return newH;
    }

    @Override
    public int compareTo(Object o) {
        return nGramWeight.compareTo(((HeapGraphNode) o).getNGramWeight());
    }

    public String getNGram() {
        return nGram;
    }

    public void setNGram(String nGram) {
        this.nGram = nGram;
    }

    public Integer getNGramWeight() {
        return nGramWeight;
    }

    public void setNGramWeight(Integer nGramWeight) {
        this.nGramWeight = nGramWeight;
    }

}
