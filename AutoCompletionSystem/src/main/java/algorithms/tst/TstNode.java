package algorithms.tst;

/**
 * Created by Catalin on 2/21/2015.
 */
public class TstNode {

    private Character character;
    private String additionalInfo;
    private TstNode leftChild;
    private TstNode middleChild;
    private TstNode rightChild;

    TstNode(){
        this('\0',"");
    }

    TstNode(Character character){
        this(character,"");
    }

    TstNode(Character character, String info){
        this.character = character;
        additionalInfo = info;
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

    public TstNode getMiddleChild() {
        return middleChild;
    }

    public TstNode getRightChild() {
        return rightChild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TstNode)) return false;

        TstNode tstNode = (TstNode) o;

        if (!character.equals(tstNode.character)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return character.hashCode();
    }

    public boolean isLeaf(){
        return leftChild == null && middleChild == null && rightChild == null;
    }

    public boolean isEmpty(){
        return character.equals('\0');
    }

    @Override
    public String toString() {
        return "TstNode{" +
                "ch=" + character +
                ", Info='" + additionalInfo + '\'' +
                ", lC=" + leftChild +
                ", mC=" + middleChild +
                ", rC=" + rightChild +
                "}";
    }

    public void setLeftChild(TstNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setMiddleChild(TstNode middleChild) {
        this.middleChild = middleChild;
    }

    public void setRightChild(TstNode rightChild) {
        this.rightChild = rightChild;
    }
}
