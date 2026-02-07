package binarytree;

public class LT116_PopulatingNextRightPointer {
    public Node connect(Node root) {

        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;

            Node maxFromLeft = root.left;
            Node minFromRight = root.right;

            while (maxFromLeft.right != null) {
                maxFromLeft = maxFromLeft.right;
                minFromRight = minFromRight.left;

                maxFromLeft.next = minFromRight;
            }
        }

        connect(root.left);
        connect(root.right);

        return root;
    }

    public Node connectBFS(Node root) {
        if(root == null){
            return null;
        }
        Node currentLevel = root;
        while(currentLevel.left != null){

            Node node = currentLevel;
            while(node != null){

                node.left.next = node.right;

                if(node.next != null){
                    node.right.next = node.next.left;
                }

                node = node.next;
            }

            currentLevel = currentLevel.left;
        }
        return root;

    }
}
