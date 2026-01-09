Poniżej znajduje się projekt pliku `README.md` dla pakietu `twopointers`. Dokumentacja została przygotowana w sposób techniczny i analityczny, koncentrując się na mechanice wskaźników oraz uzasadnieniu ich stosowania w konkretnych scenariuszach.

---

# Two Pointers Technique: Patterns and Implementation Logic

The **Two Pointers** technique is a fundamental algorithmic pattern used to solve problems involving arrays or strings with optimal space complexity (). It typically involves using two indices to traverse the data structure in a coordinated manner.

## 1. Core Patterns in this Package

Based on the analyzed implementations, the techniques can be categorized into three distinct patterns:

### A. Opposite Ends (Left & Right)

Pointers start at the beginning and end of the array, moving toward each other.

* **Mechanism:** `l = 0`, `r = n - 1`. Move `l++` or `r--` based on a comparison with a target value.
* **Why it works:** In a sorted array, this allows for a reduction of the search space in  time. Increasing the left pointer increases the sum, while decreasing the right pointer decreases it.
* **Implementation:** `LT15_3Sum`. Note that 3Sum reduces the problem to a "2Sum" sub-problem for each element, combining a standard loop with opposite-end pointers.

### B. Fast & Slow (Read & Write)

Both pointers start at the same end but move at different speeds or based on different conditions.

* **Mechanism:** One pointer (`read` or `i`) iterates through all elements. The other pointer (`write` or `k`) tracks the position where the next valid element should be placed.
* **Why it works:** It allows for in-place modifications without using auxiliary arrays, preserving  space.
* **Implementation:** * `LT26_RemoveDuplicatesFromSortedArray`: The `k` pointer only advances when a new unique element is found.
* `LT283_MoveZeroes`: The `w` pointer tracks the next available position for a non-zero element, effectively "squeezing" zeros to the end.



### C. Multiple Structures (Parallel Pointers)

Used when comparing two different strings or arrays.

* **Mechanism:** Each structure has its own independent pointer.
* **Implementation:** * `LT392_IsSubsequence`: One pointer moves through the potential subsequence only when a match is found in the main string.
* `LT88_MergeSortArray`: Three pointers are used to merge two sorted arrays directly into the larger one.



## 2. Technical Hacks and Optimizations

### Iterating Backwards to Avoid Data Overwrite

In `LT88_MergeSortArray`, pointers start at the **end** of the arrays.

* **The Logic:** Since the target array (`nums1`) has empty space at the end, filling it from largest to smallest ensures that we don't overwrite elements in `nums1` that haven't been processed yet. This removes the need for a temporary copy of the array.

### Handling Deletions with Backward Scanning

In `LT844_BackingStringCompare`, the strings contain backspace characters (`#`).

* **The Challenge:** Processing from the front is difficult because a `#` affects preceding characters.
* **The Solution:** Iterate from the end to the beginning. When a `#` is encountered, increment a skip counter. This allows you to determine exactly which characters "survive" the deletions in  additional space.

### Duplicate Management in Sorted Arrays

When solving `LT15_3Sum`, simply using a `Set` to handle duplicates is often insufficient or memory-intensive.

* **The Optimization:** Skip adjacent identical elements using `if (nums[i] == nums[i + 1]) continue;`. This prevents the algorithm from processing the same triplet values multiple times, significantly improving execution time.

## 3. Implementation Comparison: Complexity vs. Readability

In `LT283_MoveZeroes` and `LT26_RemoveDuplicates`, there is a clear evolution from complex `while` loops to clean `for` loops with conditional swaps/writes.

* **Key Takeaway:** A two-pointer solution is often most stable when one pointer represents the "stable state" (already processed) and the other scans for "new candidates."