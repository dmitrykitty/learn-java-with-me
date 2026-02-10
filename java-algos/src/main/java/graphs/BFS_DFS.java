package graphs;

import java.util.*;

public class BFS_DFS {
    public static class Node {
        int value;
        List<Node> neighbors = new ArrayList<>();

        Node(int value) {
            this.value = value;
        }
    }

    public static void dfsRecursive(Node node, Set<Node> visited) {
        if (node == null || visited.contains(node)) return;

        visited.add(node);
        System.out.println("Visited: " + node.value);

        for (Node neighbor : node.neighbors) {
            dfsRecursive(neighbor, visited);
        }
    }

    public static void dfsIterative(Node startNode) {
        Set<Node> visited = new HashSet<>();
        Deque<Node> stack = new ArrayDeque<>();

        stack.push(startNode);

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            if (!visited.contains(current)) {
                visited.add(current);
                System.out.println("Visited: " + current.value);

                for (Node neighbor : current.neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public static void bfsIterative(Node startNode) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new ArrayDeque<>();

        visited.add(startNode);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println("Visited: " + current.value);

            for (Node neighbor : current.neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
}