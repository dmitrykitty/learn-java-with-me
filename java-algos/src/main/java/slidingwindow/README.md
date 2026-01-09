Poniżej znajduje się projekt pliku `README.md` dla pakietu `slidingwindow`. Dokumentacja została przygotowana w języku angielskim, zgodnie z Twoją prośbą, z zachowaniem technicznego i szczegółowego charakteru odpowiedniego dla studenta informatyki.

---

# Sliding Window Technique: Patterns and Logic

The **Sliding Window** is a powerful optimization technique used to transform nested loops (typically ) into a single pass (linear  complexity). It is primarily applied to problems involving contiguous subarrays or subsegments of a data structure.

## 1. The Core Pattern

The general logic of a sliding window involves two pointers (`begin` and `end`) that define the boundaries of the current "window". As we iterate through the collection, the `end` pointer expands the window, while the `begin` pointer shrinks it to maintain a specific invariant (state).

### General Template

Based on the implementations in this package, the algorithm follows this structure:

```java
int begin = 0;
int windowState = 0; // Can be a sum, frequency map, or counter
int result = 0;

for (int end = 0; end < n; end++) {
    // 1. Expand the window: Include nums[end] in windowState
    updateState(nums[end]);

    // 2. Shrink the window: While the condition is violated, move 'begin'
    while (isConditionViolated(windowState)) {
        removeState(nums[begin]);
        begin++;
    }

    // 3. Update the result: The window [begin...end] is now valid
    // For Max length: end - begin + 1
    // For Min length: update inside the while loop
    result = Math.max(result, end - begin + 1);
}

```

## 2. Classification of Windows

### A. Fixed-Size Window

The distance between `begin` and `end` is constant ().

* **Use case:** Finding the maximum average of  consecutive elements.
* **Technique:** Once `end - begin + 1 == k`, we start shifting the window by moving both pointers simultaneously.
* **Implementation:** `LT643_MaximumAverageSubarrayI`.

### B. Variable-Size Window

The window grows until it breaks a rule, then shrinks until it is valid again.

* **Use case:** Finding the smallest subarray whose sum  target.
* **Technique:** Use a `while` loop to contract the window from the left as much as possible while keeping the condition true.
* **Implementation:** `LT209_MinimumSizeSubarraySum`.

## 3. Key Hacks and Implementation Tricks

### Managing "At Most K" Constraints

In problems like `LT1004` (flipping at most  zeros) or `LT1493` (deleting one element), we do not actually modify the array.

* **The Hack:** We use a counter (`windowState`) to track the number of "disallowed" elements (zeros) currently inside the window.
* **Logic:** The window remains valid as long as `zeros <= k`. When `zeros > k`, we move `begin` until a zero is "pushed out" of the window.

### Frequency Maps for Distinct Elements

When dealing with "types" of items (e.g., fruit types in `LT904`), a `HashMap` is the standard tool to track the frequency of each type within the window.

* **Efficiency Hack:** When shrinking, always check if the frequency of `nums[begin]` reaches 0. If it does, you **must** remove the key from the map so that `map.size()` correctly reflects the number of distinct elements.

### Optimization: HashSet vs. HashMap

In `LT219` (finding duplicates within distance ), the window's purpose is to act as a "look-back" buffer.

* **Technique:** Instead of a Map, use a `HashSet` to store elements within the window of size . If we try to add an element that is already in the Set, we have found a duplicate within the allowed range.
* **Performance:** A `HashSet` solution often outperforms a `HashMap` solution because we only care about existence, not counts.

### Result Initialization

* **Max Problems:** Initialize `result` with `Integer.MIN_VALUE` or `0`.
* **Min Problems:** Initialize `result` with `Integer.MAX_VALUE` and return `0` at the end if the value never changed.

## 4. Complexity Analysis

* **Time Complexity:** . Although there is a nested `while` loop, each pointer (`begin` and `end`) only moves from  to  exactly once. Therefore, each element is visited at most twice.
* **Space Complexity:** *  if the state is just a counter or sum.
*  or  if using a Map/Set to store window elements.



## Summary Table of Solved Problems

| Problem    | Window Type   | Invariant / Condition | Result Update   |
|------------|---------------|-----------------------|-----------------|
| **LT643**  | Fixed         | Window size           | Max Average     |
| **LT209**  | Variable      | `sum >= target`       | Min Length      |
| **LT1004** | Variable      | `zeros <= k`          | Max Length      |
| **LT904**  | Variable      | `map.size() <= 2`     | Max Length      |
| **LT219**  | Fixed/Sliding | `distance <= k`       | Boolean (Found) |