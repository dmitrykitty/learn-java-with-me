package binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


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
        Queue<TreeNode> q = new LinkedList<>();
        q.poll();
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

    public static void queue_bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int currentLevel = queue.size();
            for (int i = 0; i < currentLevel; i++) {
                TreeNode node = queue.poll();
                System.out.println(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }
}
