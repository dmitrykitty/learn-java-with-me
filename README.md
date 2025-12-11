# Learn Java With Me

Welcome to my Java learning repository!

This project documents my self-guided journey in mastering the Java programming language. It serves as a comprehensive study guide, a portfolio of my progress, and a collection of practical examples covering everything from language fundamentals to advanced concurrency and testing strategies.

The repository is organized as a multi-module Maven project, allowing for a clean separation of concerns between different learning stages.

---

## Project Structure

### 1. [java-fundamentals](./java-fundamentals)
The core of the repository, covering the building blocks of Java development. This module is a deep dive into the syntax, standard libraries, and key mechanisms of the JDK.

* **Object-Oriented Programming (OOP)**: Inheritance, Polymorphism, Encapsulation, and Abstraction (e.g., RPG Battle Simulator, Geometry).
* **Collections Framework**: In-depth exploration of `List`, `Set`, `Map` (HashMap, TreeMap, LinkedHashMap), and custom sorting with `Comparator` & `Comparable`.
* **Functional Programming**: Modern Java features including Lambda Expressions, Method References, and the Stream API for declarative data processing.
* **Multithreading & Concurrency**: From basic `Thread` and `Runnable` to advanced synchronization (`wait`/`notify`, `Locks`, `Atomic` variables) and solving deadlocks.
* **Java I/O**: File handling, Byte vs. Character streams, Serialization, and NIO (`java.nio.file`).
* **Reflection API & Annotations**: Runtime code analysis, dynamic object creation, and building custom annotation processors (Mini-ORM).
* **Exceptions & Strings**: Robust error handling patterns and efficient text manipulation.

### 2. [java-testing](./java-testing)
A dedicated module for mastering software quality and test automation. It focuses on writing reliable, readable, and maintainable tests.

* **JUnit 5**: The standard testing framework. Examples include test lifecycle management (`@BeforeAll`, `@AfterEach`), conditional execution, and tagging.
* **AssertJ**: Using fluent assertions for better readability and detailed error reporting.
* **Test Patterns**: Testing exceptions, working with `Optional`, and verifying collection contents.
* **Test Launchers**: Programmatic execution of tests using the JUnit Platform.

### 3. [java-network](./java-network)
Exploration of network programming and client-server architecture in Java.

* **Socket Programming**: Low-level communication using `java.net.Socket` and `ServerSocket`.
* **Client-Server Interaction**: Building simple applications that exchange data over TCP/IP.

---

## Technologies & Tools

* **Language**: Java 25 (Preview features enabled in configuration)
* **Build Tool**: Apache Maven
* **Testing**: JUnit 5 (Jupiter), AssertJ
* **IDE**: IntelliJ IDEA

---

## Goal

The goal of this repository is not just to write code, but to understand *how* it works under the hood. Each package typically contains:
1.  **Theory**: Comments or READMEs explaining the "Why" and "How".
2.  **Implementation**: Practical code examples.
3.  **Practice**: Solved tasks and challenges.

Feel free to explore the modules to see my progress!