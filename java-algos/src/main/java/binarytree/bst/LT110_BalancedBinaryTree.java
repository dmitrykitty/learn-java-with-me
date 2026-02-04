package binarytree.bst;

import binarytree.TreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.

 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true

 * Example 2:
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false

 * Example 3:
 * Input: root = []
 * Output: true
 */
public class LT110_BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);

        return Math.abs(left - right) < 2 && isBalanced(root.left) && isBalanced(root.right);


    }

    private int getDepth(TreeNode node){
        if(node == null){
            return 0;
        }
        int left = getDepth(node.left);
        int right = getDepth(node.right);

        return Math.max(left, right) + 1;
    }

}
