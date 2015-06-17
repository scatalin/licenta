package algorithms.tst;

import dictionary.Word;

/**
 * Created by Catalin on 2/21/2015.
 */
public class TernarySearchTreeRecursive extends AbstractTernarySearchTree {

    private int toInsertWeight;
    private boolean wasEndWord;

    @Override
    public void insert(Word word) {
        this.toInsertWeight = word.getWeight();
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
                node.setWordWeight(toInsertWeight);
                wasEndWord = false;
            }
            return node;
        }
        if (toInsertWeight > node.getSubtreesWeight()) {
            node.setSubtreesWeight(toInsertWeight);
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
            node.setWordWeight(toInsertWeight);
            node.setEndWord();
        }
        return node;
    }

    @Override
    public void update(String word, int weight) {
        this.toInsertWeight = weight;
        wasEndWord = false;
        root = recursiveInsert(root, word);
        //todo update-ul se poate face la revenirea in sus pe arbore dupa ce ai facut update la word
    }

    public boolean search(String word) {
        return recursiveSearch(root, word);
    }

    private boolean recursiveSearch(TstNode node, String s) {
        if (s.isEmpty()) {
            return true;
        }
        if (node == null) {
            return false;
        }
        if (toInsertWeight > node.getSubtreesWeight()) {
            node.setSubtreesWeight(toInsertWeight);
        }
        int comparisonResult = Character.valueOf(s.charAt(0)).compareTo(node.getCharacter());
        boolean result = false;
        if (comparisonResult < 0) {
            result |= recursiveSearch(node.getLeftChild(), s);
        }
        if (comparisonResult > 0) {
            result |= recursiveSearch(node.getRightChild(), s);
        }
        if (comparisonResult == 0) {
            result |= recursiveSearch(node.getMiddleChild(), s.substring(1));
        }
        return result;
    }
}