package algorithms.tst;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class TernarySearchTreeIterative extends AbstractTernarySearchTree {

    @Override
    public boolean search(String s) {
        String s1 = s;
        TstNode node = root;
        boolean found = false;

        while (true) {
            if (s1.length() == 0) {
                found = true;
                break;
            }

            if (node == null) {
                found = false;
                break;
            }

            int comparisonResult = node.getCharacter().compareTo(s.charAt(0));
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
                s1 = s1.substring(1);
            }
        }
        return found;
    }

    @Override
    public void insert(String s) {

        String s1 = s;
        TstNode node = root;

        while (true) {
            if (s1.length() == 0) {
                break;
            }

            int comparisonResult = node.getCharacter().compareTo(s.charAt(0));
            if (comparisonResult < 0) {
                if (node.getLeftChild() == null) {
                    node.setLeftChild(new TstNode(s1.charAt(0)));
                }
                node = node.getLeftChild();
                continue;
            }
            if (comparisonResult > 0) {
                if (node.getRightChild() == null) {
                    node.setRightChild(new TstNode(s1.charAt(0)));
                }
                node = node.getRightChild();
                continue;
            }

            if (comparisonResult == 0) {
                if (node.getMiddleChild() == null) {
                    node.setMiddleChild(new TstNode(s1.charAt(0)));
                }
                node = node.getMiddleChild();
                s1 = s1.substring(1);
            }
        }
    }
}
