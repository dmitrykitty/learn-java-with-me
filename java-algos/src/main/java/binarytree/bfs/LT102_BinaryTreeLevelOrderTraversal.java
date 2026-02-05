package binarytree.bfs;

import binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]

 * Example 2:
 * Input: root = [1]
 * Output: [[1]]

 * Example 3:
 * Input: root = []
 * Output: []
 */
public class LT102_BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return List.of();
        }

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();

        q.add(root);

        while(!q.isEmpty()){
            int currentLevelSize = q.size();
            List<Integer> currentLevel = new ArrayList<>();

            for(int i = 0; i < currentLevelSize; i++){
                TreeNode node = q.poll();
                currentLevel.add(node.val);

                if(node.left != null){
                    q.add(node.left);
                }

                if(node.right != null){
                    q.add(node.right);
                }

            }

            result.add(currentLevel);
        }

        return result;
    }

}
