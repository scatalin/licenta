package algorithms.tst.intern;

/**
 * Created by Catalin on 2/21/2015.
 */
public class TstNode implements Comparable<TstNode> {

    private Character character;
    private int weight;
    private int endWordWeight;
    private boolean endWord;
    private TstNode leftChild;
    private TstNode middleChild;
    private TstNode rightChild;

    public TstNode() {
        this(' ', 0);
    }

    public TstNode(Character character) {
        this(character, 0);
    }

    public TstNode(Character character, int weight) {
        this.character = character;
        this.weight = weight;
        this.endWordWeight = weight;
        endWord = false;
    }

    public boolean isEmpty() {
        return character.equals(' ');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TstNode)) {
            return false;
        }

        TstNode tstNode = (TstNode) o;

        return character.equals(tstNode.character);
    }

    @Override
    public int hashCode() {
        return character.hashCode();
    }

    @Override
    public String toString() {
        return "TstNode{" +
                "ch=" + character +
                ", W='" + weight + '\'' +
                ", lC=" + leftChild +
                ", mC=" + middleChild +
                ", rC=" + rightChild +
                "}";
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public TstNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TstNode leftChild) {
        this.leftChild = leftChild;
    }

    public TstNode getMiddleChild() {
        return middleChild;
    }

    public void setMiddleChild(TstNode middleChild) {
        this.middleChild = middleChild;
    }

    public TstNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TstNode rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isEndWord() {
        return endWord;
    }

    public void setEndWord() {
        this.endWord = true;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getEndWordWeight() {
        return endWordWeight;
    }

    public void setEndWordWeight(int endWordWeight) {
        this.endWordWeight = endWordWeight;
    }

    public int getSpecificWeight() {
        return endWord ? endWordWeight : weight;
    }

    @Override
    public int compareTo(TstNode o) {
        return Integer.valueOf(this.getWeight()).compareTo(o.getWeight());
    }

//    public int compareWordsWeights(TstNode o) {
//        return Integer.valueOf(this.getSpecificWeight()).compareTo(o.getSpecificWeight());
//    }
}
