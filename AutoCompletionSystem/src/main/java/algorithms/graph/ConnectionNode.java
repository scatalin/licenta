package algorithms.graph;

/**
 * Created by Catalin on 6/17/2015 .
 */
public class ConnectionNode {
    private final WordNode wordNode;
    private int weight;

    public ConnectionNode(WordNode wordNode, int weight) {
        this.wordNode = wordNode;
        this.weight = weight;
    }

    public WordNode getWordNode() {
        return wordNode;
    }

    public int getWeight() {
        return weight;
    }

    public void increaseWeight() {
        weight++;
    }
}
