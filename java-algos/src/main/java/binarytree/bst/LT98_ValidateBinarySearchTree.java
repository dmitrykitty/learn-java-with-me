package binarytree.bst;

import binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 * The left subtree of a node contains only nodes with keys strictly less than the node's key.
 * The right subtree of a node contains only nodes with keys strictly greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * Example 1:
 * Input: root = [2,1,3]
 * Output: true
 * <p>
 * Example 2:
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class LT98_ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;

        return isValidHelper(root.left, min, root.val) &&
                isValidHelper(root.right, root.val, max);
    }


    private boolean isValidHelper(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return isValidHelper(node.left, min, node.val) &&
                isValidHelper(node.right, node.val, max);
    }

    public static record NodeConstrains(
            TreeNode node,
            long min,
            long max
    ) {
    }

    public boolean isValidBSTIterative(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<NodeConstrains> stack = new ArrayDeque<>(); //to accept null
        stack.add(new NodeConstrains(root, Long.MIN_VALUE, Long.MAX_VALUE));

        while (!stack.isEmpty()) {
            NodeConstrains nodeConstrains = stack.pop();
            if (nodeConstrains.node != null) {
                long nodeVal = nodeConstrains.node.val;
                if (nodeVal <= nodeConstrains.min || nodeVal >= nodeConstrains.max) {
                    return false;
                }

                stack.push(new NodeConstrains(nodeConstrains.node.left, nodeConstrains.min, nodeVal));
                stack.push(new NodeConstrains(nodeConstrains.node.right, nodeVal, nodeConstrains.max));
            }
        }
        return true;
    }
}
