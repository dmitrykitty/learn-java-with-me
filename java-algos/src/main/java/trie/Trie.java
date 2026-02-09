package trie;

import binarytree.TreeNode;

import java.util.HashMap;
import java.util.Map;

class Trie {
    Node root;

    private static class Node {
        public Map<Character, Node> letterMap;
        public boolean isEnd;

        public Node() {
            letterMap = new HashMap<>();
            isEnd = false;
        }
    }

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node current = root;
        for (char ch : word.toCharArray()) {
            if (current.letterMap.containsKey(ch)) {
                current = current.letterMap.get(ch);
            } else {
                Node nodeToAdd = new Node();
                current.letterMap.put(ch, nodeToAdd);
                current = nodeToAdd;
            }

            if (ch == word.charAt(word.length() - 1)) {
                current.isEnd = true;
            }
        }
    }

    public boolean search(String word) {
        Node current = root;
        for (char ch : word.toCharArray()) {
            if (!current.letterMap.containsKey(ch)) {
                return false;
            }

            current = current.letterMap.get(ch);

            if (ch == word.charAt(word.length() - 1) && !current.isEnd) {
                return false;
            }
        }
        return true;
    }

    public boolean startsWith(String prefix) {
        Node current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.letterMap.containsKey(ch)) {
                return false;
            }
            current = current.letterMap.get(ch);
        }
        return true;
    }
}