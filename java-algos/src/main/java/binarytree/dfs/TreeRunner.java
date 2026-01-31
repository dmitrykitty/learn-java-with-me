package binarytree.dfs;

import binarytree.TreeNode;

public class TreeRunner {
    static void main() {
        TreeNode root = createTestTree();

        DFS_BFS.recursive_dfs_pre_order(root);

        System.out.println();

        DFS_BFS.recursive_dfs_in_order(root);

        System.out.println();

        DFS_BFS.recursive_dfs_post_order(root);

        System.out.println();

        DFS_BFS.stack_dfs_pre_order(root);


    }

    public static TreeNode createTestTree(){
        TreeNode root1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root1.left = node2;
        root1.right = node3;

        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        return root1;
    }
}
