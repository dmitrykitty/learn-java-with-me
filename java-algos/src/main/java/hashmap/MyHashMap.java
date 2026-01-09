package hashmap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class MyHashMap {


    private final List<List<ListNode>> buckets;
    private final static int MAX_SIZE = 257;

    public static class ListNode {
        int key;
        int value;

        private ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        buckets = new ArrayList<>(MAX_SIZE);
        for(int i = 0; i < MAX_SIZE; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    /**
     * Persists key/value pair; updates if key exists
     */
    public void put(int key, int value) {
        int listPosition = key % MAX_SIZE;
        List<ListNode> bucket = buckets.get(listPosition);
        for(ListNode node: bucket) {
            if(node.key == key) {
                node.value = value;
                return;
            }
        }
        bucket.addLast(new ListNode(key, value));

    }

    public int get(int key) {
        int listPosition = key % MAX_SIZE;
        List<ListNode> list = buckets.get(listPosition);
        for (ListNode listNode : list) {
            if (listNode.key == key) {
                return listNode.value;
            }
        }
        return -1;
    }

    public void remove(int key) {
        int index = key % MAX_SIZE;
        List<ListNode> bucket = buckets.get(index);
        for (ListNode node : bucket) {
            if (node.key == key) {
                bucket.remove(node);
                break;
            }
        }
    }
}
