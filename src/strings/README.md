# Java Strings

## PART I: Comprehensive Theoretical Introduction

Handling text is one of the most common tasks in programming. In Java, strings are objects, not primitive types (like `int` or `char`). Understanding how Java handles strings in memory is crucial for writing efficient applications.

### 1. Class Hierarchy & Interfaces
It is important to understand "what a String is" in the context of Java's type system.

**The `CharSequence` Interface:**
This is the root interface for reading a sequence of characters. It provides uniform access to read-only operations like `length()`, `charAt()`, and `subSequence()`.
* **Implementations**:
    * **`String`**: Immutable (niezmienny).
    * **`StringBuilder`**: Mutable (zmienny), fast, not thread-safe.
    * **`StringBuffer`**: Mutable, thread-safe (synchronized).

**Interfaces implemented by `String`:**
1.  **`Serializable`**: Allows the string to be converted into a byte stream (saved to a file or sent over a network).
2.  **`Comparable<String>`**: Allows strings to be sorted naturally (lexicographically, e.g., "Apple" before "Banana").
3.  **`CharSequence`**: As described above.

### 2. Immutability (Niezmienność)
The `String` class is **immutable**.
* **Meaning**: Once a `String` object is created, its internal character array (`byte[]` or `char[]`) cannot be changed.
* **Consequence**: Methods like `toUpperCase()` or `replace()` **DO NOT** modify the original object. They create and return a **NEW** object.
* **Why?**: Security (hash codes, database passwords), Thread Safety (read-only by definition), and the String Pool.

### 3. The String Constant Pool
To save memory, Java stores string literals in a special area of the Heap called the **String Pool**.
* `String s1 = "Java";` -> Checks pool. If "Java" exists, returns reference. If not, creates it.
* `String s2 = "Java";` -> Returns the SAME reference as s1.
* `String s3 = new String("Java");` -> Forces creation of a NEW object outside the pool (Heap). **Avoid this**.

### 4. String vs StringBuilder vs StringBuffer

| Feature           | `String`                  | `StringBuilder`                        | `StringBuffer`                      |
|:------------------|:--------------------------|:---------------------------------------|:------------------------------------|
| **Mutability**    | Immutable                 | Mutable                                | Mutable                             |
| **Thread Safety** | Yes (Safe)                | No (Unsafe)                            | Yes (Synchronized)                  |
| **Performance**   | Slow for concatenation    | Very Fast                              | Fast (slower than Builder)          |
| **Use Case**      | Literals, Keys, Constants | Loop concatenation, heavy manipulation | Legacy code, Multithreaded text gen |

---

## PART II: Package Content & Solutions

This package demonstrates practical string manipulation, algorithms, and performance benchmarks.

### 1. Basic Manipulation (`lesson12`)
A collection of tasks solving common text processing problems:
* **Replacements**: `Task1` demonstrates `replace()` to swap emoticons.
* **Inspection**: `Task2` uses `startsWith()` and `endsWith()` to validate string structure.
* **Formatting**: `Task3` formats a full name into initials (F.I.O) using `charAt()` and `toUpperCase()`.
* **Search Algorithms**:
    * `Task4`: Counts occurrences of specific characters using nested loops.
    * `Task5`: Splits a string into N-sized chunks using `substring()` and array manipulation.

### 2. Performance & Mutability (`lesson14`)
* **Benchmark**: `StringBuilderAndStringBuffer` compares the speed of concatenating strings in a loop.
    * **Scenario**: Appending 100,000 integers.
    * **Result**: `String` takes seconds (creates 100k temporary objects). `StringBuilder` takes milliseconds (modifies internal array). This proves why you should never use `+` in large loops.

### 3. Algorithms & Practice (`practise`)
* **Palindromes**: `Polindrom.java` shows two ways to check if a word reads the same backwards:
    1.  **High-level**: Using `StringBuilder.reverse()`.
    2.  **Algorithmic**: Comparing characters from both ends moving towards the center (`text.charAt(i) != text.charAt(len-i-1)`).
* **Unique Characters**: `UniqueChars.java` implements logic to filter out duplicate characters from a string while preserving the original order (ignoring spaces).