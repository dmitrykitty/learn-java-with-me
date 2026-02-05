package binarytree.bfs;

import binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

 * Example 1:
 * Input: root = [1,3,2,5,3,null,9]
 * Output: [1,3,9]

 * Example 2:
 * Input: root = [1,2,3]
 * Output: [1,3]
 */
public class LT515_FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        if(root == null){
            return List.of();
        }

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();

        q.add(root);

        while(!q.isEmpty()){
            int currentLevelSize = q.size();
            int currentMax = Integer.MIN_VALUE;

            for(int i = 0; i < currentLevelSize; i++){
                TreeNode node = q.poll();

                if(node.val > currentMax){
                    currentMax = node.val;
                }

                if(node.left != null){
                    q.add(node.left);
                }

                if(node.right != null){
                    q.add(node.right);
                }
            }

            result.add(currentMax);
        }

        return result;
    }
}
