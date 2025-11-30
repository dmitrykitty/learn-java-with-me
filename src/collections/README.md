# Java Collections Framework

## PART I: Comprehensive Theoretical Introduction

The Java Collections Framework (JCF) is a unified architecture for representing and manipulating collections. It allows you to manipulate groups of objects as a single unit. To use it effectively, you must know **which implementation to choose** for a specific problem to optimize performance.

### 1. The Core Hierarchy
Interfaces define the contract, while classes provide the implementation.

* **`Collection`**: The root interface (extends `Iterable`).
    * **`List`**: An **ordered** sequence. Allows duplicates.
        * *Use when*: You need to access elements by index or maintain insertion order.
    * **`Set`**: A collection of **unique** elements. Models the mathematical set abstraction.
        * *Use when*: You need to filter out duplicates or check for existence efficiently.
    * **`Queue` / `Deque`**: Ordered list for holding elements about to be processed (LIFO/FIFO).

* **`Map`** (Not a Collection): An object that maps keys to values. Keys must be unique.

### 2. Algorithm: How to choose a collection?
Ask yourself these questions in order:

1.  **Do you need key-value pairs?** -> **Map**
    * Need sorting by key? -> `TreeMap` (O(log n))
    * Need insertion order or LRU cache? -> `LinkedHashMap`
    * Just need speed? -> `HashMap` (O(1)) - *Default choice*

2.  **Do you need unique elements?** -> **Set**
    * Need sorting? -> `TreeSet`
    * Need insertion order? -> `LinkedHashSet`
    * Just need speed? -> `HashSet` (O(1)) - *Default choice*

3.  **Do you need a simple list?** -> **List**
    * Do you add/remove from the ends mostly? -> `LinkedList`
    * Do you read often by index? -> `ArrayList` (O(1) access) - *Default choice*

### 3. Critical Concept: `hashCode()` and `equals()`
For `HashMap` and `HashSet` to work correctly, objects stored in them **must** override `hashCode` and `equals` consistently.
* **Contract**: If `a.equals(b)` is true, then `a.hashCode() == b.hashCode()` must be true.
* If you break this rule, you might put an object into a Map but never be able to retrieve it.

---

## PART II: Package Content & Solutions

This package demonstrates the usage of various data structures through cheat sheets, algorithmic tasks, and complex system simulations.

### 1. Lists (`list`)
* **`ArrayListRunner`**: Demonstrates the dynamic array nature of `ArrayList`. Fast random access (`get(i)`), but slow insertions/deletions in the middle (shifting elements).
* **`LinkedListTest`**: Shows `LinkedList` used as a `Deque` (stack/queue) with methods like `addFirst`/`pollLast`. Highlights that it is better for frequent modifications at ends but slower for random access.

### 2. Maps (`map`)
* **`hashmap`**: Basic usage. Explains iteration via `keySet`, `values`, and `entrySet`.
* **`linkedhashmap`**: Demonstrates the `accessOrder` feature (constructor parameter), which is essential for building **LRU (Least Recently Used) Caches** where the order changes based on reads.
* **`treemap`**: Using `NavigableMap` features like `firstEntry`, `ceilingKey`, and `subMap` to work with sorted data ranges.

### 3. Sets (`sets`)
* **`SetCheatSheet`**: A direct comparison of `HashSet` (unordered, fast), `LinkedHashSet` (ordered by insertion), and `TreeSet` (sorted) behavior.

### 4. Sorting & Ordering (`sorting`)
* **Comparable vs Comparator**:
    * **Natural Ordering**: `Student` class implements `Comparable` to define default sorting logic (e.g., by average grade).
    * **Custom Logic**: `ChatNameComparator` and `ChatUsersAmountComparator` allow sorting `Chat` objects by different criteria without modifying the class.
* **Advanced Usage**: Using `thenComparing` to chain sort criteria (e.g., sort by name, then by user count).

### 5. Algorithmic Practice (`bigpractise/practise1`)
* **Filtering**: `Filter.java` removes elements from a list based on a condition (e.g., odd numbers).
* **Uniqueness**: `ArrayUtils` counts unique numbers in a list using a `HashSet`.
* **Polynomials**: `PolynomialsUtils` uses a `Map<Integer, Integer>` to represent mathematical polynomials (power -> coefficient). It implements logic to add two polynomials and format the result as a string (e.g., `2x^3 + 5x + 1`).

### 6. Complex Project: Cinema System (`bigpractise/practise2`)
A robust simulation demonstrating relationships and stream processing:
* **Many-to-Many**: `Film` has a list of `Actor`s, and `Actor` has a list of `Film`s.
* **Cinema Logic**: The `Cinema` class acts as a database/repository.
    * **Advanced Filtering**: Finding movies by specific criteria (Year, Genre, Casting).
    * **Set Operations**: `getFilmsByActors` uses `containsAll` to find movies featuring a specific group of actors.

### 7. Contract Safety (`equalsandhashcode`)
* **`PersonExample`**: Demonstrates that without overriding `equals` and `hashCode`, two `Person` objects with identical data are considered different by collections like `HashSet`.