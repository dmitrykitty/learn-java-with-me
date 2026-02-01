package binarytree.dfs;

import binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * A leaf is a node with no children.

 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Explanation: The root-to-leaf path with the target sum is shown.

 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 * Explanation: There are two root-to-leaf paths in the tree:
 * (1 --> 2): The sum is 3.
 * (1 --> 3): The sum is 4.
 * There is no root-to-leaf path with sum = 5.

 * Example 3:
 * Input: root = [], targetSum = 0
 * Output: false
 * Explanation: Since the tree is empty, there are no root-to-leaf paths.
 */
public class LT112_PathSum {
    public boolean hasPathSumRecursive(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        targetSum -= root.val;

        if(root.left == null && root.right == null){
            return targetSum == 0;
        }

        return hasPathSumRecursive(root.left, targetSum) || hasPathSumRecursive(root.right, targetSum);
    }

    //iterative
    private static class Pair {
        int currentSum;
        TreeNode node;

        public Pair(int currentSum, TreeNode node) {
            this.currentSum = currentSum;
            this.node = node;
        }
    }

    public boolean hasPathSumIterative(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }

        Deque<Pair> stack = new ArrayDeque<>();
        stack.push(new Pair(targetSum, root));

        while(!stack.isEmpty()){
            Pair pair = stack.pop();
            int currentSum = pair.currentSum - pair.node.val;
            if(pair.node.left == null && pair.node.right == null && currentSum == 0){
                return true;
            }

            if(pair.node.left != null){
                stack.push(new Pair(currentSum, pair.node.left));
            }
            if(pair.node.right != null){
                stack.push(new Pair(currentSum, pair.node.right));
            }
        }

        return false;
    }
}
