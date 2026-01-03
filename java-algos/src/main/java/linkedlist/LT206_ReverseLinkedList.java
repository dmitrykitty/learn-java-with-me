package linkedlist;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * <p>
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 * <p>
 * Example 3:
 * Input: head = []
 * Output: []
 */
public class LT206_ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        while(curr != null){
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        head = prev;
        return head;
    }
}
