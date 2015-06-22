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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConnectionNode)) return false;

        ConnectionNode that = (ConnectionNode) o;

        if (wordNode != null ? !wordNode.equals(that.wordNode) : that.wordNode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return wordNode != null ? wordNode.hashCode() : 0;
    }
}
