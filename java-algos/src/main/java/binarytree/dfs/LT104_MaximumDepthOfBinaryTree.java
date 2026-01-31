package binarytree.dfs;

import binarytree.TreeNode;

import java.util.*;

public class LT104_MaximumDepthOfBinaryTree {
    public int maxDepthRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepthRecursive(root.left);
        int right = maxDepthRecursive(root.right);

        return 1 + Math.max(left, right);
    }

    public static class Pair {
        TreeNode node;
        int depth;

        public Pair(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public int maxDepthIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<Pair> stack = new ArrayDeque<>();
        stack.push(new Pair(root, 1));
        int max = 0;

        while (!stack.isEmpty()) {
            Pair popped = stack.pop();

            TreeNode left = popped.node.left;
            TreeNode right = popped.node.right;
            int currentDepth = popped.depth;

            max = Math.max(max, currentDepth);

            if (popped.node.left != null) {
                stack.push(new Pair(left, currentDepth + 1));
            }
            if (popped.node.right != null) {
                stack.push(new Pair(right, currentDepth + 1));
            }
        }
        return max;
    }
}
