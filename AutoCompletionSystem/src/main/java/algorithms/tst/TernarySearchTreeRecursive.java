package algorithms.tst;

import algorithms.tst.intern.TstNode;
import model.dictionary.Word;

/**
 * Created by Catalin on 2/21/2015.
 */
public class TernarySearchTreeRecursive extends AbstractTernarySearchTree {

    private int toInsertWeight;
    private boolean wasEndWord;

    @Override
    public void insert(Word word) {
        this.toInsertWeight = word.getFrequency();
        wasEndWord = false;
        root = recursiveInsert(root, word.getWord());
    }

    private TstNode recursiveInsert(TstNode node, String s) {
        if (s.isEmpty()) {
            wasEndWord = true;
            return node;
        }
        if (node == null) {
            node = new TstNode(s.charAt(0), toInsertWeight);
            node.setMiddleChild(recursiveInsert(node.getMiddleChild(), s.substring(1)));
            if (wasEndWord) {
                node.setEndWord();
                node.setEndWordWeight(toInsertWeight);
                wasEndWord = false;
            }
            return node;
        }
        if (toInsertWeight > node.getWeight()) {
            node.setWeight(toInsertWeight);
        }
        int comparisonResult = Character.valueOf(s.charAt(0)).compareTo(node.getCharacter());
        if (comparisonResult < 0) {
            node.setLeftChild(recursiveInsert(node.getLeftChild(), s));
        }
        if (comparisonResult > 0) {
            node.setRightChild(recursiveInsert(node.getRightChild(), s));
        }
        if (comparisonResult == 0) {
            node.setMiddleChild(recursiveInsert(node.getMiddleChild(), s.substring(1)));
        }
        if (wasEndWord) {
            wasEndWord = false;
            node.setEndWordWeight(toInsertWeight);
            node.setEndWord();
        }
        return node;
    }

//    public int search(String s) {
//        return recursiveSearch(root, s);
//    }
//
//    private int recursiveSearch(TstNode node, String s) {
//        if (s.length() == 0) {
//            if (node == null) {
//                return -1;
//            }
//            if (node.isEndWord()) {
//                return node.getWeight();
//            }
//            return -1;
//        }
//        if (node == null) {
//            return -1;
//        }
//        int comparisonResult = Character.valueOf(s.charAt(0)).compareTo(node.getCharacter());
//        if (comparisonResult < 0) {
//            return recursiveSearch(node.getLeftChild(), s.substring(1));
//        }
//        if (comparisonResult > 0) {
//            return recursiveSearch(node.getRightChild(), s.substring(1));
//        }
//        return recursiveSearch(node.getMiddleChild(), s.substring(1));
//    }
}
