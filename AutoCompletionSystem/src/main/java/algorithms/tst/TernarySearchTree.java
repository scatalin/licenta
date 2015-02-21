package algorithms.tst;

/**
 * Created by Catalin on 2/21/2015.
 */
public class TernarySearchTree {

    private TstNode root;
    private TstPrinter printer;

    TernarySearchTree() {
        root = new TstNode();
        printer = new TstPrinter();
    }

    public void load(String[] strings) {
        root = null;
        for (String string : strings) {
            root = recursiveInsert(root, string);
        }
    }

    public void additionalLoad(String[] strings) {
        for (String string : strings) {
            root = recursiveInsert(root, string);
        }
    }

    public boolean search(String s) {
        return recursiveSearch(root, s);
    }

    private boolean recursiveSearch(TstNode node, String s) {
        if (s.length() == 0) {
            return true;
        }
        if (node == null) {
            return false;
        }
        int comparisonResult = node.getCharacter().compareTo(s.charAt(0));
        if (comparisonResult < 0) {
            return recursiveSearch(node.getLeftChild(), s.substring(1));
        }
        if (comparisonResult > 0) {
            return recursiveSearch(node.getRightChild(), s.substring(1));
        }
        return recursiveSearch(node.getMiddleChild(), s.substring(1));
    }

    public void insert(String s) {
        recursiveInsert(root, s);
    }

    private TstNode recursiveInsert(TstNode node, String s) {
        if (s.length() == 0) {
            return node;
        }
        if (node == null) {
            node = new TstNode(s.charAt(0));
            node.setMiddleChild(recursiveInsert(node.getMiddleChild(), s.substring(1)));
            return node;
        }
        int comparisonResult = node.getCharacter().compareTo(s.charAt(0));
        if (comparisonResult < 0) {
            node.setLeftChild(recursiveInsert(node.getLeftChild(), s));
        }
        if (comparisonResult > 0) {
            node.setRightChild(recursiveInsert(node.getRightChild(), s));
        }
        if(comparisonResult == 0){
            node.setMiddleChild(recursiveInsert(node.getMiddleChild(), s.substring(1)));
        }
        return node;
    }

    public String print(){
        return printer.prettyPrint(root);
    }
}
