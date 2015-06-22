package algorithms.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Catalin on 6/17/2015 .
 */
public class WordNode {

    private String word;
    private List<ConnectionNode> adjacencyList;

    public WordNode(String word) {
        this.word = word;
        this.adjacencyList = new LinkedList<>();
    }

    public void addConnection(WordNode wordNode) {
        int result = adjacencyList.indexOf(new ConnectionNode(wordNode,0));
        if (result >= 0) {
            adjacencyList.get(result).increaseWeight();
        }
        else {
            adjacencyList.add(new ConnectionNode(wordNode, 1));
        }
    }

    public String getWord() {
        return word;
    }

    public List<ConnectionNode> getAdjacencyList() {
        return adjacencyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordNode)) return false;

        WordNode wordNode = (WordNode) o;

        if (word != null ? !word.equals(wordNode.word) : wordNode.word != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return word != null ? word.hashCode() : 0;
    }
}
