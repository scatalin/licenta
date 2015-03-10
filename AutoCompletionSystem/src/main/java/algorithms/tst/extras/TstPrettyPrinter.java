package algorithms.tst.extras;

import algorithms.tst.intern.TstNode;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class TstPrettyPrinter {

    private Character[][] matrix;
//    private Character[][] dummyMatrix;
    private DimensionsInfo rootInfo;
    private int x;
    private int y;
    private TstNode root;

    public TstPrettyPrinter() {
    }

    public void setRootNode(TstNode root) {
        this.root = root;
        calculateDimensions();
        matrix = new Character[x][y];
        System.out.println("depth of the tst is " + rootInfo.depth);
        System.out.println("left dimension of the tst is " + rootInfo.leftDimension);
        System.out.println("right dimension of the tst is " + rootInfo.rightDimension);
    }

    private void calculateDimensions() {
        rootInfo = recursiveCalculateDimensions(root);
        if (rootInfo != null) {
            y = rootInfo.depth * 2;
            x = (rootInfo.leftDimension + rootInfo.rightDimension + 1);
        } else {
            x = 0;
            y = 0;
        }
    }

    private DimensionsInfo recursiveCalculateDimensions(TstNode node) {
        if (node == null) {
            return null;
        }
        DimensionsInfo leftInfo = recursiveCalculateDimensions(node.getLeftChild());
        DimensionsInfo rightInfo = recursiveCalculateDimensions(node.getRightChild());
        DimensionsInfo middleInfo = recursiveCalculateDimensions(node.getMiddleChild());

        DimensionsInfo toReturn = new DimensionsInfo();
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



    public String prettyPrint() {
        populateMatrix(root, rootInfo.rightDimension, 0);
        //todo remove debugging
//        printMatrix(matrix, x, y);
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
        DimensionsInfo middle, right, left;
        try {
            if (node == null) {
                return;
            }
            middle = recursiveCalculateDimensions(node.getMiddleChild());
            if(middle == null){
                middle = new DimensionsInfo();
            }
            matrix[line][column] = node.getCharacter();
            if (node.getMiddleChild() != null) {
                matrix[line][column + 1] = '|';
                populateMatrix(node.getMiddleChild(), line, column + 2);
            }
            if (node.getRightChild() != null) {
                right = recursiveCalculateDimensions(node.getRightChild());
                matrix[line - middle.rightDimension - right.leftDimension - 1][column + 1] = '\\';
                populateMatrix(node.getRightChild(), line - middle.rightDimension - right.leftDimension - 1, column + 2);
            }
            if (node.getLeftChild() != null) {
                left = recursiveCalculateDimensions(node.getLeftChild());
                matrix[line + middle.leftDimension + left.rightDimension + 1][column + 1] = '/';
                populateMatrix(node.getLeftChild(), line + middle.leftDimension + left.rightDimension + 1, column + 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void printMatrix(Character[][] matrix, int x, int y) {
//        System.out.println("matrix " + x + " " + y);
//        for (int i = 0; i < x; i++) {
//            System.out.print("|");
//            for (int j = 0; j < y; j++) {
//                if (matrix[i][j] == null) {
//                    System.out.print(" ");
//                } else {
//                    System.out.print(matrix[i][j]);
//                }
//            }
//            System.out.println("|");
//        }
//        System.out.println("matrix");
//
//
//        Character[][] revertedMatrix = new Character[y][x];
//        for (int i = 0; i < y; i++) {
//            for (int j = 0; j < x; j++) {
//                revertedMatrix[i][j] = matrix[j][i];
//            }
//        }
//        matrix = new Character[y][x];
//        for (int i = 0; i < x; i++) {
//            for (int j = 0; j < y; j++) {
//                matrix[j][i] = revertedMatrix[j][x - i - 1];
//            }
//        }
//
//        System.out.println("reverted matrix " + x + " " + y);
//        for (int i = 0; i < y; i++) {
//            System.out.print("|");
//            for (int j = 0; j < x; j++) {
//                if (matrix[i][j] == null) {
//                    System.out.print(" ");
//                } else {
//                    System.out.print(matrix[i][j]);
//                }
//            }
//            System.out.println("|");
//        }
//        System.out.println("matrix");
//    }

//    private String constructDummyString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        revertDummyMatrix();
//        for (int i = 0; i < y; i++) {
//            for (int j = 0; j < x; j++) {
//                if (dummyMatrix[i][j] == null) {
//                    stringBuilder.append(" ");
//                } else {
//                    stringBuilder.append(dummyMatrix[i][j]);
//                }
//            }
//            stringBuilder.append("\n");
//        }
//        return stringBuilder.toString();
//    }
//
//    private void revertDummyMatrix() {
//        Character[][] revertedMatrix = new Character[y][x];
//        for (int i = 0; i < y; i++) {
//            for (int j = 0; j < x; j++) {
//                revertedMatrix[i][j] = matrix[j][i];
//            }
//        }
//        dummyMatrix = new Character[y][x];
//        for (int i = 0; i < x; i++) {
//            for (int j = 0; j < y; j++) {
//                dummyMatrix[j][i] = revertedMatrix[j][x - i - 1];
//            }
//        }
//    }

}

