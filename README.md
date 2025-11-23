# Java Learning Path

A comprehensive repository documenting my self-guided journey in mastering Java. This project contains practical implementations, algorithmic challenges, and mini-projects that demonstrate key language mechanisms and best practices.

---

## Overview

The project is organized into thematic packages representing a progression from Object-Oriented Programming fundamentals to Collections and Functional Programming, reaching advanced topics like the Reflection API and Input/Output (I/O) operations.

---

## Project Structure

### 1.  Object-Oriented Programming (OOP)
Practical application of the four pillars of OOP.
* **Battle Simulation (RPG)**: A complex combat system.
    * Abstract `Hero` class and concrete implementations (`Warrior`, `Mage`, `Archer`).
    * **Generic Weapon System**: Heroes are typed by the weapon they wield (`Hero<T extends Weapon>`).
    * Interfaces: `Mortal`, `RangeWeapon`, `MeleeWeapon`, `MagicWeapon`.
    * Combat logic and object interaction (`TrainingGround`).
* **Geometry (Figures)**: Polymorphism in practice.
    * Abstract `Shape` class and interfaces like `WithPerimeter` and `Internal` (checking if a point lies inside a shape).
    * Implementations: `Circle`, `Rectangle`.
* **Inheritance & Composition**:
    * Computer hardware hierarchy (`Computer` -> `Laptop`, `Mobile`).
    * ATM simulation (`Bankomat`) with banknote management logic.
* **Enums**: Enumerations with abstract methods (`ProcessorType`).

### 2. Collections Framework
Working with data structures and algorithms.
* **Cinema Management System**:
    * Bidirectional relationships between `Film` and `Actor`.
    * Advanced filtering (by year, genre, actors) and sorting.
* **Polynomial Operations**:
    * Representation of polynomials as `Map<Integer, Integer>` (power -> coefficient).
    * Logic for adding polynomials and formatting them into readable strings (e.g., `ax^2 + bx + c`).
* **Lists, Sets, and Maps**:
    * Performance comparison: `ArrayList` vs `LinkedList`.
    * Custom comparators (`StudentAgeComparator`, `ChatUsersAmountComparator`).
    * Usage of `TreeMap` and `LinkedHashMap` (e.g., implementation of an LRU Cache).

### 3. Java I/O (Input/Output)
File operations and data streams.
* **Text Analysis**:
    * Finding words starting with a vowel.
    * Finding word chains (last letter of a word = first letter of the next).
    * Counting the maximum sequence of digits in a text.
* **File Modification**:
    * `CodeEditor`: A program that reads a `.java` file and changes access modifiers from `public` to `private`.
    * `TextEditor`: Reversing text in files line by line.
* **Streams vs Readers**: Examples using `FileInputStream`, `BufferedReader`, `Scanner`, and the `Files` class.

### 4. Reflection API & Annotations
Introspection and metaprogramming.
* **Mini-ORM (Object-Relational Mapping)**:
    * Custom annotations: `@Table` and `@Column`.
    * `DataBaseUtils` class: Dynamically generates SQL `INSERT INTO...` queries based on object fields using reflection.
* **Validation**:
    * Custom annotation `@MinAge`.
    * Runtime object validator (`AnnotationValidator`).
* **Exploration**: Dynamic instantiation, accessing private fields and methods.

### 5. Functional Programming
Modern data processing (Java 8+).
* **Stream API**:
    * Filtering, mapping, sorting, and reducing (`reduce`).
    * Working with `Optional` to avoid `NullPointerException`.
    * Data grouping (`Collectors.groupingBy`) and statistics (`IntSummaryStatistics`).
* **Functional Interfaces**: Lambda expressions, Method References.

### 6. Generics
Writing flexible and type-safe code.
* **Custom Data Structure**: `MyArray<T>` class – a dynamic array implementing the `Iterable` interface.
* **Pair Class**: A universal container for a pair of objects with a `swap` method.

### 7. Exception Handling
Error management.
* Creating custom exceptions (`MyException`).
* Exception hierarchy (Checked vs Unchecked).
* Practice with `try-catch-finally` blocks and `try-with-resources`.

### 8. Strings
Text manipulation.
* Algorithms: Palindrome checking, finding unique characters.
* Performance: `String` vs `StringBuilder` (benchmarking).

---

## Key Educational Projects

1.  **RPG Battle Simulator**
    * *Goal*: Understanding polymorphism, abstract classes, and generics.
    * *Description*: A system allowing team battles where every hero (Warrior, Mage, Archer) has a unique weapon type and attack method.

2.  **Mini-ORM (Reflection API)**
    * *Goal*: Practical application of reflection and annotations.
    * *Description*: A tool that converts Java objects into SQL queries. It automatically maps class names and fields to database tables and columns.

3.  **Cinema Management System**
    * *Goal*: Mastering Collections and Stream API.
    * *Description*: A system managing a database of films and actors, handling complex queries (e.g., "Find top 10 Drama movies starring Actor X").

4.  **Java Code Editor (I/O)**
    * *Goal*: File operations.
    * *Description*: A utility that parses Java source files and modifies code structure (e.g., changing access modifiers) directly within the files.
