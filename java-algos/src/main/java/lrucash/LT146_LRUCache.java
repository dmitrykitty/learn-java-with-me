package lrucash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LT146_LRUCache {
    private static class Node{
        int key;
        int value;
        Node next;

        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    private int size;
    private final int capacity;

    private final Map<Integer, Node> map;
    private final List<Node> list;

    public LT146_LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;

        map = new HashMap<>();
        list = new LinkedList<>();

    }

    public int get(int key) {
        Node res = map.get(key);
        if(res == null){
            return -1;
        }

        list.remove(res);
        list.addFirst(res);

        return res.value;

    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;

            list.remove(node);
            list.addFirst(node);

            return;
        }

        if(size >= capacity){
            size--;

            Node nodeToRemove = list.removeLast();
            map.remove(nodeToRemove.key);
        }
        Node nodeToAdd = new Node(key, value);
        map.put(key, nodeToAdd);
        list.addFirst(nodeToAdd);

        size++;
    }
}
