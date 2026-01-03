package linkedlist;

/**
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 * <p>
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 * <p>
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 */
public class LT234_PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        //1 step - find miidle of the list
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        //2 step - reverse second part of the list
        //now slow - new head of list to reverse
        ListNode prev = null;
        while(slow != null){
            ListNode tmp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = tmp;
        }
        ListNode currReversed = prev;
        ListNode curr = head;

        //3 step - compare
        while(currReversed != null){
            if(currReversed.val != curr.val){
                return false;
            }
            currReversed = currReversed.next;
            curr = curr.next;
        }
        return true;
    }
}
