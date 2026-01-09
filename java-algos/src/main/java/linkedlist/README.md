# Linked List Algorithms & Patterns Summary

This document summarizes the core techniques and patterns used to solve common singly linked list problems. These patterns optimize for  time complexity and, most importantly,  space complexity by manipulating pointers instead of using auxiliary data structures.

## 1. The Dummy Node Pattern

A **Dummy Node** (or Sentinel Node) is a temporary node placed at the beginning of the list that points to the `head`.

* **Mechanism:** `ListNode dummy = new ListNode(0, head);`.
* **Why it works:** It simplifies edge cases where the `head` itself might be removed or changed. It ensures that every functional node in the list has a preceding node, allowing for a uniform logic throughout the algorithm.
* **Applied in:** Removing the N-th node from the end, merging sorted lists, swapping nodes in pairs, and general list design.

## 2. Two-Pointer Techniques (Fast & Slow)

The "Tortoise and Hare" approach is the most versatile tool for linked list analysis.

### A. Finding the Middle (Middle Detection)

* **Mechanism:** Move the `slow` pointer by 1 step and the `fast` pointer by 2 steps per iteration.
* **Outcome:** When `fast` reaches the end (`null`), `slow` is at the middle node.
* **Note:** This is the foundation for checking palindromes and deleting middle nodes.

### B. Cycle Detection (Floyd's Algorithm)

* **Mechanism:** If `slow == fast` at any point, a cycle exists.
* **Finding the Start of the Cycle:** After detection, reset `slow` to `head` and move both pointers 1 step at a time. They will meet exactly at the cycle's entry point.
* **Calculating Cycle Length:** Once in the cycle, keep one pointer fixed and move the other until it returns to the fixed one, counting the steps.

### C. Maintaining a Fixed Gap

* **Mechanism:** Move the `right` pointer  steps ahead before starting the `left` pointer.
* **Outcome:** When `right` reaches the tail, `left` is positioned exactly before the N-th node from the end.
* **Applied in:** `LT19_RemoveNthNodeFromEndOfList`.

## 3. In-place Reversal

Reversing a list without extra memory requires re-linking the `next` pointers of each node.

* **Mechanism:** Iterate through the list using three pointers: `prev` (initially `null`), `curr`, and a temporary `tmp` to store the next node before overwriting the current node's link.
* **Logic:** `tmp = curr.next; curr.next = prev; prev = curr; curr = tmp;`.
* **Applied in:** Standard list reversal and the second half of palindrome detection.

## 4. Palindrome Verification

Checking if a linked list is a palindrome is a multi-step process that combines several patterns.

1. **Find the middle** using the Fast & Slow pointers.
2. **Reverse the second half** of the list in place.
3. **Compare values** step-by-step starting from the `head` and the new head of the reversed second half.
4. **Result:** If all compared values match, the list is a palindrome.

## 5. Technical "Gotchas" and Optimizations

* **Null Safety:** Always verify `fast != null && fast.next != null` before advancing a fast pointer to prevent `NullPointerException`.
* **Pointer Maintenance:** When deleting a node, you must have access to the *preceding* node (`slowPred`) to bypass the target.
* **Sorted List Advantage:** In sorted lists, duplicates are always adjacent. You can remove them by checking if `curr.val == curr.next.val` and skipping the next node.
* **Memory Efficiency:** Avoid using Stacks or Lists for these problems; pointer manipulation allows for  auxiliary space complexity.

## Complexity Summary

| Algorithm / Technique   | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Find Middle             | $O(n)$          | $O(1)$           |
| Reverse List            | $O(n)$          | $O(1)$           |
| Cycle Detection (Floyd) | $O(n)$          | $O(1)$           |
| Remove N-th from End    | $O(n)$          | $O(1)$           |
| Palindrome Check        | $O(n)$          | $O(1)$           |
| Merge Sorted Lists      | $O(n + m)$      | $O(1)$           |