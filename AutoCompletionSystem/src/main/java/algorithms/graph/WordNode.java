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
        int result = adjacencyList.indexOf(wordNode);
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
}
