package algorithms.tst;

/**
 * Created by Catalin on 2/21/2015.
 */
public class TstNode implements Comparable<TstNode> {

    private Character character;
    private int subtreesWeight;
    private int wordWeight;
    private boolean endWord;
    private TstNode leftChild;
    private TstNode middleChild;
    private TstNode rightChild;

    public TstNode(Character character) {
        this(character, 0);
    }

    public TstNode(Character character, int subtreesWeight) {
        this.character = character;
        this.subtreesWeight = subtreesWeight;
        this.wordWeight = subtreesWeight;
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
                ", W='" + subtreesWeight + '\'' +
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

    public int getSubtreesWeight() {
        return subtreesWeight;
    }

    public void setSubtreesWeight(int subtreesWeight) {
        this.subtreesWeight = subtreesWeight;
    }

    public int getWordWeight() {
        return wordWeight;
    }

    public void setWordWeight(int wordWeight) {
        this.wordWeight = wordWeight;
    }

    public int getSpecificWeight() {
        return endWord ? wordWeight : subtreesWeight;
    }

    @Override
    public int compareTo(TstNode o) {
        return Integer.valueOf(this.getSubtreesWeight()).compareTo(o.getSubtreesWeight());
    }

//    public int compareWordsWeights(TstNode o) {
//        return Integer.valueOf(this.getSpecificWeight()).compareTo(o.getSpecificWeight());
//    }
}
