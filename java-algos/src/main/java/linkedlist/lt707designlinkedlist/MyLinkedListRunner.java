package linkedlist.lt707designlinkedlist;

public class MyLinkedListRunner {
    static void main() {

        MyLinkedList obj = new MyLinkedList();
        int param_1 = obj.get(1);
        System.out.println(param_1);

        obj.addAtHead(5);
        System.out.println(obj.get(0));

        obj.addAtTail(3);
        System.out.println(obj.get(1));

        obj.addAtIndex(1,4);
        System.out.println(obj.get(1));

    }
}
