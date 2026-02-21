package linkedlist;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.

 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted linked list:
 * 1->1->2->3->4->4->5->6

 * Example 2:
 * Input: lists = []
 * Output: []

 * Example 3:
 * Input: lists = [[]]
 * Output: []
 */
public class LT25_MergeKLinkedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        Queue<ListNode> minHeap = new PriorityQueue<>(lists.length, (a, b) -> Integer.compare(a.val, b.val));

        //O(K*logK)
        for(ListNode lst : lists){
            if(lst != null) minHeap.add(lst);

        }

        ListNode dummy = new ListNode();
        ListNode cur = dummy;

        //O(N*logK)
        while(!minHeap.isEmpty()){
            ListNode minNode = minHeap.poll();
            cur.next = minNode;

            if(minNode.next != null){
                minHeap.add(minNode.next);
            }

            cur = cur.next;
        }

        return dummy.next;

    }
}
