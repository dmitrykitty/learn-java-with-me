package binarytree.bfs;

import binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 */
public class LT1302_DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {

        Queue<TreeNode> q = new ArrayDeque<>();
        int result = 0;

        q.add(root);

        while(!q.isEmpty()){
            int currentSize = q.size();
            result = 0;

            for(int i = 0; i < currentSize; i++){
                TreeNode node = q.poll();
                result += node.val;

                if(node.left != null){
                    q.add(node.left);
                }

                if(node.right != null){
                    q.add(node.right);
                }
            }
        }

        return result;
    }
}
