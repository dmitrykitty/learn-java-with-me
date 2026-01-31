package binarytree.dfs;

import binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;


public class DFS_BFS {
    public static void recursive_dfs_pre_order(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        recursive_dfs_pre_order(root.left);
        recursive_dfs_pre_order(root.right);
    }

    public static void recursive_dfs_in_order(TreeNode root) {
        if (root == null) {
            return;
        }
        recursive_dfs_in_order(root.left);
        System.out.println(root.val);
        recursive_dfs_in_order(root.right);
    }

    public static void recursive_dfs_post_order(TreeNode root) {
        if (root == null) {
            return;
        }
        recursive_dfs_post_order(root.left);
        recursive_dfs_post_order(root.right);
        System.out.println(root.val);
    }

    //memory complecity O(hight) = O(len(N))
    public static void stack_dfs_pre_order(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            System.out.println(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
}
