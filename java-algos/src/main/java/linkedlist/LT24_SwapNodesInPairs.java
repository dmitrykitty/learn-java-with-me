package linkedlist;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes
 * (i.e., only nodes themselves may be changed.)
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * <p>
 * Example 2:
 * Input: head = []
 * Output: []
 * <p>
 * Example 3:
 * Input: head = [1]
 * Output: [1]
 * <p>
 * Example 4:
 * Input: head = [1,2,3]
 * Output: [2,1,3]
 */
public class LT24_SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode curr = dummy;

        while(curr.next != null && curr.next.next != null){
            ListNode first = curr.next;
            ListNode second = first.next;

            first.next = second.next;
            second.next = first;

            curr.next = second;
            curr = first;

        }

        return dummy.next;
    }
}
