package linkedlist.lt707designlinkedlist;

/**
 * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
 * A node in a singly linked list should have two attributes: val and next. val is the value of the current node,
 * and next is a pointer/reference to the next node.
 * If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node
 * in the linked list. Assume all nodes in the linked list are 0-indexed.

 * Implement the MyLinkedList class:

 * MyLinkedList() Initializes the MyLinkedList object.
 * int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
 * void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion,
 * the new node will be the first node of the linked list.
 * void addAtTail(int val) Append a node of value val as the last element of the linked list.
 * void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list.
 * If index equals the length of the linked list, the node will be appended to the end of the linked list.
 * If index is greater than the length, the node will not be inserted.
 * void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.

 * Example 1:
 * Input
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * Output
 * [null, null, null, null, 2, null, 3]

 * Explanation
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
 * myLinkedList.get(1);              // return 2
 * myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
 * myLinkedList.get(1);              // return 3
 */
public class MyLinkedList {
    private Node head;
    private int size = 0;

    private static class Node {
        Node next = null;
        int value = 0;

        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public MyLinkedList() {
        head = null;
    }

    public int get(int index) {
        if(index < 0 || index >= size){
            return -1;
        }
        Node cur = head;
        int curIndex = 0;
        while(curIndex != index){
            curIndex++;
            cur = cur.next;
        }
        return cur.value;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if(index < 0 || index > size) return;

        size++;

        if(index == 0){
            Node newHead = new Node(val, head);
            head = newHead;
            return;
        }

        Node cur = head;
        int curIndex = 0;

        while(curIndex != index - 1){
            curIndex++;
            cur = cur.next;
        }

        Node temp = cur.next;
        cur.next = new Node(val, temp);
    }


    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size) return;

        size--;

        if(index == 0){
            Node newHead = head.next;
            head.next = null;
            head = newHead;
            return;
        }

        Node cur = head;
        Node curNext = cur.next;
        int curIndex = 1;

        while(curIndex != index){
            curIndex++;
            cur = cur.next;
            curNext = curNext.next;
        }

        Node temp = curNext.next;
        curNext.next = null;
        cur.next = temp;
    }
}
