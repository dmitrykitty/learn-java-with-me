package lrucash;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private static class Node{
        int key;
        int value;
        Node next;
        Node prev;

        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
        Node(){}
    }

    private int size;
    private final int capacity;

    private final Map<Integer, Node> map;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;

        map = new HashMap<>();
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;
    }

    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;

        size--;
    }

    private Node getLast(){
        return tail.prev;
    }

    private Node getFirst(){
        return head.next;
    }



    private void addFirst(Node node){
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;

        size++;
    }

    public int get(int key) {
        Node res = map.get(key);
        if(res == null){
            return -1;
        }

        remove(res);
        addFirst(res);

        return res.value;

    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;

            remove(node);
            addFirst(node);

            return;
        }

        if(size >= capacity){
            Node nodeToRemove = getLast();
            remove(nodeToRemove);
            map.remove(nodeToRemove.key);
        }
        Node nodeToAdd = new Node(key, value);
        addFirst(nodeToAdd);
        map.put(key, nodeToAdd);
    }
}
