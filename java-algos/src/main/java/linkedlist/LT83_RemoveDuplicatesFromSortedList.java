package linkedlist;

/**
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
 * Return the linked list sorted as well.
 * <p>
 * Example 1:
 * Input: head = [1,1,2]
 * Output: [1,2]
 * <p>
 * Example 2:
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 */
public class LT83_RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode left = head;
        ListNode right = head;
        while(left != null){
            right = right.next;
            while(right != null && right.val == left.val){
                right = right.next;
            }
            left.next = right;
            left = left.next;
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
            curr = curr.next;
            }
        }
        return head;
    }
}
