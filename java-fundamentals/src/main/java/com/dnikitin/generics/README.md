# Java Generics

## PART I: Comprehensive Theoretical Introduction

Generics (introduced in Java 5) enable types (classes and interfaces) to be parameters when defining classes, interfaces, and methods. Much like method parameters allow you to pass specific values, type parameters allow you to pass specific **types**.

### 1. The Problem Before Generics
Before Generics, collections operated on `Object`.
* **No Type Safety**: You could accidentally put a `String` into a List intended for `Integer`.
* **Manual Casting**: You had to manually cast every object retrieved from a collection (`(Integer) list.get(0)`).

### 2. Benefits of Generics
1.  **Compile-Time Safety**: The compiler catches invalid types *before* the code runs.
2.  **Elimination of Casts**: The compiler inserts casts automatically, making code cleaner.
3.  **Generic Algorithms**: You can write algorithms (sorting, searching) that work on *any* type of object.

### 3. Key Concepts
* **Type Parameter**: The `<T>`, `<E>`, `<K, V>` placeholders.
    * `E` - Element (used by Collections)
    * `K`, `V` - Key, Value
    * `T` - Type
* **Raw Types**: Using a generic class without the type parameter (e.g., `List list = new ArrayList()`). **Avoid this**; it disables type checking.
* **Bounded Type Parameters**: Restricting the types that can be used (e.g., `<T extends Number>` allows only Numbers).

### 4. Type Erasure (The "Under the Hood" Magic)
Java Generics are a compile-time feature. The Java Virtual Machine (JVM) **does not know about generics**.
* During compilation, the compiler removes all generic type information and replaces it with `Object` (or the bound type).
* It inserts the necessary type casts.
* **Consequence**: You cannot check `instanceof T` or create `new T[]` at runtime, because `T` does not exist at runtime.

---

## PART II: Package Content & Solutions

This package demonstrates how to create your own generic data structures and methods.

### 1. Custom Generic Data Structure (`lesson1`)
* **`MyArray<T>`**: A dynamic array implementation built from scratch.
    * **Generic Class**: Defined as `class MyArray<T>` to hold elements of any type.
    * **The Array Problem**: Since we cannot do `new T[size]`, the class internally uses `new Object[capacity]` and casts to `(T[])`.
    * **Iterable**: Implements `Iterable<T>` and a custom `Iterator`, allowing the structure to be used in standard for-each loops.

### 2. Generic Containers (`practise1`)
* **`Pair<K, V>`**: A universal container holding two related objects of potentially different types (Key and Value).
    * It uses multiple type parameters `<K, V>`.
    * Commonly used to return two values from a single method.

### 3. Generic Methods (`practise1`)
* **`PairUtil`**: A utility class demonstrating **Generic Methods**.
    * **Syntax**: The type parameters `<K, V>` are declared *before* the return type.
    * **`swap()`**: Takes a `Pair<K, V>` and returns a new `Pair<V, K>`, effectively swapping the key and value with full type safety.