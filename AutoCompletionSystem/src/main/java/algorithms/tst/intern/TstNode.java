package algorithms.tst.intern;

/**
 * Created by Catalin on 2/21/2015.
 */
public class TstNode {

    private Character character;
    private String additionalInfo;
    private TstNode leftChild;
    private TstNode middleChild;
    private TstNode rightChild;

    public TstNode(){
        this(' ',"");
    }

    public TstNode(Character character){
        this(character,"");
    }

    public TstNode(Character character, String info){
        this.character = character;
        additionalInfo = info;
    }

    public boolean isEmpty() {
        return character.equals(' ');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TstNode)) return false;

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
                ", Info='" + additionalInfo + '\'' +
                ", lC=" + leftChild +
                ", mC=" + middleChild +
                ", rC=" + rightChild +
                "}";
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
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
}
