package binarytree.bfs;

import binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 */
public class LT103_BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null){
            return List.of();
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        List<List<Integer>> result = new ArrayList<>();
        q.addFirst(root);
        int currentLevelNum = 0;

        while(!q.isEmpty()){
            int currentSize = q.size();
            List<Integer> lst = new ArrayList<>();

            for(int i = 0; i < currentSize; i++){
                TreeNode node = null;
                if(currentLevelNum % 2 == 0){
                    node = q.pollLast();

                    if(node.left != null){
                        q.addFirst(node.left);
                    }

                    if(node.right != null){
                        q.addFirst(node.right);
                    }
                } else {
                    node = q.pollFirst();

                    if(node.right != null){
                        q.addLast(node.right);
                    }

                    if(node.left != null){
                        q.addLast(node.left);
                    }
                }

                lst.add(node.val);
            }
            currentLevelNum++;
            result.add(lst);
        }

        return result;
    }
}
