# Functional Programming in Java

## PART I: Comprehensive Theoretical Introduction

Java 8 introduced the most significant change in the language's history: support for **Functional Programming**. This paradigm shifts the coding style from **Imperative** (telling the computer *how* to do things step-by-step using loops and mutable state) to **Declarative** (telling the computer *what* result you want using expressions and transformations).

### 1. Lambda Expressions & Functional Interfaces
A **Lambda Expression** is essentially a concise way to represent a method interface using an expression. It allows treating code as data.
* **Syntax**: `(parameters) -> { body }`
* **Functional Interface**: An interface with **exactly one abstract method**. Lambdas provide the implementation for this single method.
* **Core Interfaces** (in `java.util.function`):
    * **`Predicate<T>`**: Takes an object, returns `boolean`. Used for filtering.
    * **`Function<T, R>`**: Takes type T, returns type R. Used for mapping/transformation.
    * **`Consumer<T>`**: Takes type T, returns `void`. Used for actions (e.g., printing).
    * **`Supplier<T>`**: Takes nothing, returns T. Used for factories/lazy generation.

### 2. The Stream API
A **Stream** is a sequence of elements supporting sequential and parallel aggregate operations.
* **Not a Data Structure**: Streams don't store data; they convey elements from a source (like a List) through a pipeline of computations.
* **The Pipeline**:
    1.  **Source**: Creation (from Collection, Array, I/O).
    2.  **Intermediate Operations**: Transform the stream into another stream (e.g., `filter`, `map`). These are **Lazy** – they don't execute until a terminal operation is called.
    3.  **Terminal Operations**: Produce a result or side-effect (e.g., `collect`, `forEach`, `reduce`). This triggers the processing.

### 3. Optional
`Optional<T>` is a container object which may or may not contain a non-null value. It was introduced to eliminate the infamous `NullPointerException` by forcing the programmer to explicitly handle the "no value" case using methods like `orElse()` or `ifPresent()`.

---

## PART II: Package Content & Solutions

This package explores modern Java features through examples ranging from basic syntax to complex data processing pipelines.

### 1. From Anonymous Classes to Lambdas (`lambdafunctions`)
* **Evolution of Syntax**: `LambdaExample` demonstrates how verbose Anonymous Inner Classes were replaced by concise Lambdas and even cleaner **Method References** (`Integer::compare`). It also explains custom implementations of `Comparator`.

### 2. Stream API Mechanics (`stream`)
* **The Pipeline in Action**: `StreamExample` is a comprehensive guide to stream operations:
    * **Mapping**: Transforming strings to integers (`map`, `mapToInt`).
    * **Filtering**: Selecting even/odd numbers (`filter`).
    * **Slicing**: Skipping elements or limiting results (`skip`, `limit`).
    * **Statistics**: Using `IntSummaryStatistics` to calculate count, sum, min, average, and max in one pass.

### 3. Advanced Reduction & Safety (`mapreduce_optional`)
* **Map-Reduce Pattern**: `MapReduceExample` shows how to process data in parallel to find aggregates (e.g., the oldest student) using the `reduce()` method.
* **Null Safety**: Demonstrates creating `Optional` from potentially null results and chaining methods safely (e.g., `optional.map().filter().ifPresent()`).
* **FlatMap**: Using `flatMap` to flatten a structure (e.g., converting a Stream of Students, where each has a List of marks, into a single Stream of marks).

### 4. Practice Tasks (`practise`)
Real-world data manipulation scenarios solved purely with streams:

* **Aggregations**: `Task1` calculates the average of numbers divisible by 5 and odd, handling the result safely with `ifPresent`.
* **Set Operations**: `Task2` finds unique strings longer than 8 characters using `collect(Collectors.toSet())` or `distinct().count()`.
* **Map Processing**: `Task3` streams over `map.entrySet()` to filter entries by key length and sum their values.
* **String Joining**: `Task4` efficiently concatenates a list of integers into a single string using `Collectors.joining()`.
* **Grouping**: `Task5` demonstrates powerful data reorganization using `Collectors.groupingBy` to creating a Map where keys are ages and values are lists of People with that age.