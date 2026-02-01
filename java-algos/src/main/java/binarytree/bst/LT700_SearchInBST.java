package binarytree.bst;

import binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LT700_SearchInBST {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }

        if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    public TreeNode searchBSTRecursive(TreeNode root, int val) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val == val) {
                return node;
            }
            if (node.val > val && node.left != null) {
                stack.push(node.left);
            }
            if (node.val < val && node.right != null) {
                stack.push(node.right);
            }
        }
        return null;
    }
}
