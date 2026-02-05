package binarytree.bfs;

import binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.

 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]

 * Example 2:
 * Input: root = [1,2,3,4,null,null,null,5]
 * Output: [1,3,4,5]

 * Example 3:
 * Input: root = [1,null,3]
 * Output: [1,3]
 */
public class LT199_BinaryTreeRightSizeView {
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null){
            return List.of();
        }

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();

        q.add(root);

        while(!q.isEmpty()){
            int currentLevelSize = q.size();
            int mostRight = currentLevelSize - 1;
            for(int i = 0; i < currentLevelSize; i++){
                TreeNode node = q.poll();

                if(i == mostRight){
                    result.add(node.val);
                }

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
