package graphs;

import java.util.*;

public class LT133_CloneGraph {
    private static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }
        Queue<Node> q = new ArrayDeque<>();

        Map<Node, Node> visited = new HashMap<>();
        q.add(node);
        visited.put(node, new Node(node.val));

        while(!q.isEmpty()){
            Node cur = q.poll();
            Node copyNode = visited.get(cur);

            for(Node nb : cur.neighbors){
                if(!visited.containsKey(nb)){
                    q.add(nb);
                    visited.put(nb, new Node(nb.val));
                }
                copyNode.neighbors.add(visited.get(nb));
            }
        }

        return visited.get(node);
    }

    public Node cloneGraphRecursive(Node node) {
        if(node == null){
            return null;
        }
        Map<Node, Node> visited = new HashMap<>();
        return dfs(node, visited);

    }
    private Node dfs(Node node, Map<Node, Node> visited){
        if(node == null){
            return null;
        }

        if(visited.containsKey(node)){
            return visited.get(node);
        }
        Node copyNode = new Node(node.val);
        visited.put(node, copyNode);
        for(Node nb : node.neighbors){
            copyNode.neighbors.add(dfs(nb, visited));
        }
        return copyNode;
    }
}
