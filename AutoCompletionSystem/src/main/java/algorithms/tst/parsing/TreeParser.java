package algorithms.tst.parsing;

import algorithms.tst.TstNode;

/**
 * Created by gstan on 10.03.2015.
 */
public class TreeParser {

    public DimensionsInfo calculateDimensions(TstNode node) {
        if (node == null) {
            return new DimensionsInfo();
        }
        DimensionsInfo leftInfo = calculateDimensions(node.getLeftChild());
        DimensionsInfo rightInfo = calculateDimensions(node.getRightChild());
        DimensionsInfo middleInfo = calculateDimensions(node.getMiddleChild());

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

    public int countNodes(TstNode node) {
        if (node == null) {
            return 0;
        }
        int number = 0;
        number += countNodes(node.getLeftChild());
        number += countNodes(node.getMiddleChild());
        number += countNodes(node.getRightChild());
        number++;
        return number;
    }
}
