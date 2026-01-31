package binarytree.dfs;

import binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class LT101_SymmetricTree {
    public boolean isSymmetric(TreeNode root) {

        if (root.left == null && root.right == null) {
            return true;
        } else if (root.left == null || root.right == null) {
            return false;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root.left);
        stack.push(root.right);

        while (!stack.isEmpty()) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();

            if (left.val != right.val) {
                return false;
            }

            if (left.right != null && right.left != null) {
                stack.push(left.right);
                stack.push(right.left);
            } else if (left.right != null || right.left != null) {
                return false;
            }


            if (left.left != null && right.right != null) {
                stack.push(left.left);
                stack.push(right.right);
            } else if (left.left != null || right.right != null) {
                return false;
            }
        }
        return true;
    }

    //BFS
    public boolean isSymmetric2(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            if (t1 == null && t2 == null) {
                continue;
            }

            if (t1 == null || t2 == null || t1.val != t2.val) {
                return false;
            }

            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }

    //recursive
    public boolean isSymmetricRecursive(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }

        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }
}
