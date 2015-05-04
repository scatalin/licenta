package algorithms.tst;

import algorithms.tst.intern.TstNode;

import java.util.List;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class TernarySearchTreeIterative {

    //    @Override
    public boolean search(String s) {
        String string = s;
        TstNode node = new TstNode();
        boolean found;

        while (true) {
            if (string.length() == 0) {
                found = true;
                break;
            }

            if (node == null) {
                found = false;
                break;
            }

            int comparisonResult = node.getCharacter().compareTo(string.charAt(0));
            if (comparisonResult < 0) {
                node = node.getLeftChild();
                continue;
            }
            if (comparisonResult > 0) {
                node = node.getRightChild();
                continue;
            }

            if (comparisonResult == 0) {
                node = node.getMiddleChild();
                string = string.substring(1);
            }
        }
        return found;
    }

    //    @Override
    public List<String> getTopK(String prefix) {
        return null;
    }

    //    @Override
    public void insert(String s) {

        String string = s;
//        if(root == null){
//            root = new TstNode();
//        }
        TstNode node = new TstNode();

        while (true) {
            if (string.length() == 0) {
                break;
            }

            if (node.isEmpty()) {
                insertRemainingWord(node, string);
                break;
            }

            int comparisonResult = node.getCharacter().compareTo(string.charAt(0));
            if (comparisonResult < 0) {
                if (node.getLeftChild() == null) {
                    node.setLeftChild(new TstNode(string.charAt(0)));

                }
                node = node.getLeftChild();
                continue;
            }
            if (comparisonResult > 0) {
                if (node.getRightChild() == null) {
                    node.setRightChild(new TstNode(string.charAt(0)));

                }
                node = node.getRightChild();
                continue;
            }

            if (comparisonResult == 0) {
                string = string.substring(1);
                if (node.getMiddleChild() == null && string.length() > 0) {
                    node.setMiddleChild(new TstNode());
                }
                node = node.getMiddleChild();
            }
        }
    }

    private void insertRemainingWord(TstNode node, String string) {
        while (string.length() > 0) {
            node.setCharacter(string.charAt(0));
            string = string.substring(1);
            if (string.length() > 0) {
                node.setMiddleChild(new TstNode());
                node = node.getMiddleChild();
            }
        }
    }
}
