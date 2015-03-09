package algorithms.tst.intern;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class TstPrettyPrinter {

    private Character[][] matrix;
    private ReturnInfo rootInfo;
    private int x;
    private int y;
    private TstNode root;

    public TstPrettyPrinter(TstNode root) {
        this.root = root;
    }

    private void calculateDimensions() {
        rootInfo = recursiveCalculateDimensions(root);
        y = (rootInfo.depth) * 2;
        x = (rootInfo.leftDimension + rootInfo.rightDimension + 1) * 2 + 1;
    }

    private ReturnInfo recursiveCalculateDimensions(TstNode node) {
        if (node == null) {
            return null;
        }
        ReturnInfo leftInfo = recursiveCalculateDimensions(node.getLeftChild());
        ReturnInfo rightInfo = recursiveCalculateDimensions(node.getRightChild());
        ReturnInfo middleInfo = recursiveCalculateDimensions(node.getMiddleChild());

        ReturnInfo toReturn = new ReturnInfo();
        int maxDepth = 0;

        if (leftInfo == null) {
            toReturn.leftDimension = 0;
        } else {
            toReturn.leftDimension = leftInfo.leftDimension + leftInfo.rightDimension + 1;
            if (maxDepth < leftInfo.depth) {
                maxDepth = leftInfo.depth;
            }
        }

        if (rightInfo == null) {
            toReturn.rightDimension = 0;
        } else {
            toReturn.rightDimension = rightInfo.leftDimension + rightInfo.rightDimension + 1;
            if (maxDepth < rightInfo.depth) {
                maxDepth = rightInfo.depth;
            }
        }

        if (middleInfo != null) {
            toReturn.leftDimension += middleInfo.leftDimension;
            toReturn.rightDimension += middleInfo.rightDimension;
            if (maxDepth < middleInfo.depth) {
                maxDepth = middleInfo.depth;
            }
        }
        toReturn.depth = maxDepth + 1;

        return toReturn;
    }

    public void setRootNode(TstNode root) {
        this.root = root;
        calculateDimensions();
        matrix = new Character[x][y];
    }

    private class ReturnInfo {
        int leftDimension = 0;
        int rightDimension = 0;
        int depth = 0;
    }

    public String prettyPrint() {
        populateMatrix(root, (rootInfo.rightDimension) * 2 + 1, 0);
        revertMatrix();
        StringBuilder stringBuilder = new StringBuilder();
        constructString(stringBuilder);
        return stringBuilder.toString();
    }

    private void constructString(StringBuilder stringBuilder) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (matrix[i][j] == null) {
                    stringBuilder.append(" ");
                } else {
                    stringBuilder.append(matrix[i][j]);
                }
            }
            stringBuilder.append("\n");
        }
    }

    private void revertMatrix() {
        Character[][] revertedMatrix = new Character[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                revertedMatrix[i][j] = matrix[j][i];
            }
        }
        matrix = new Character[y][x];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix[j][i] = revertedMatrix[j][x - i - 1];
            }
        }
    }

    private void populateMatrix(TstNode node, int line, int column) {
        if (node == null) {
            return;
        }
        matrix[line][column] = node.getCharacter();
        if (node.getMiddleChild() != null) {
            matrix[line][column + 1] = '|';
            populateMatrix(node.getMiddleChild(), line, column + 2);
        }
        if (node.getRightChild() != null) {
            ReturnInfo right = recursiveCalculateDimensions(node.getRightChild());
            matrix[line - right.leftDimension - 1][column + 1] = '\\';
            populateMatrix(node.getRightChild(), line - ((right.leftDimension + 1) * 2), column + 2);
        }
        if (node.getLeftChild() != null) {
            ReturnInfo left = recursiveCalculateDimensions(node.getLeftChild());
            matrix[left.rightDimension + line + 1][column + 1] = '/';
            populateMatrix(node.getLeftChild(), (left.rightDimension + 1) * 2 + line, column + 2);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

