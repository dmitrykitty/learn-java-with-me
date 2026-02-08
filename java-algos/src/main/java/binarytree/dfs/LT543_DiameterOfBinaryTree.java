package binarytree.dfs;

import binarytree.TreeNode;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.

 * The length of a path between two nodes is represented by the number of edges between them.
 */
public class LT543_DiameterOfBinaryTree {
    int maxSum = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        return Math.max(left + right, maxSum);

    }

    private int dfs(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        maxSum = Math.max(left + right, maxSum);

        return Math.max(left, right) + 1;
    }
}
