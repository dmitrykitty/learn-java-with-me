package linkedlist;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * <p>
 * Example 2:
 * Input: head = [1], n = 1
 * Output: []
 * <p>
 * Example 3:
 * Input: head = [1,2], n = 1
 * Output: [1]
 */
public class LT19_RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // introduced a dummy node pointing to the head.
        // this is a standard pattern in Linked Lists to handle edge cases gracefully,
        // specifically when the node to be removed is the 'head' itself.
        ListNode dummy = new ListNode(0, head);

        ListNode right = dummy;
        ListNode left = dummy;

        /*
         move the 'right' pointer 'n' steps forward.
         this creates a fixed distance (gap) of 'n' steps between 'left' and 'right'.
        */
        for(int i = 0; i < n; i++){
            right = right.next;
        }

        // move both pointers one step at a time until 'right' reaches the end of the list.
        // since the gap of 'n' is maintained:
        // when 'right' hits the last node (tail), 'left' will be exactly at the node
        // immediately PRECEDING the target node we want to remove.
        while(right.next != null){
            right = right.next;
            left = left.next;
        }

        left.next = left.next.next;
        return dummy.next;
    }
}
