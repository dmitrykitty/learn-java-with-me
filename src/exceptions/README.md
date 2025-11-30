# Java Exception Handling

## PART I: Comprehensive Theoretical Introduction

An Exception is an event that occurs during the execution of a program that disrupts the normal flow of instructions. Java provides a robust object-oriented mechanism to handle these errors, allowing the program to gracefully recover or terminate.

### 1. The Throwable Hierarchy
All errors and exceptions in Java are subclasses of the `java.lang.Throwable` class.

* **`Error`**: Indicates serious problems that a reasonable application should not try to catch (e.g., `OutOfMemoryError`, `StackOverflowError`). These are usually abnormal conditions in the JVM.
* **`Exception`**: Indicates conditions that a reasonable application might want to catch.
    * **Checked Exceptions**: Exceptions that are checked at compile-time (e.g., `IOException`, `FileNotFoundException`). You *must* handle them (try-catch) or declare them (`throws`).
    * **Unchecked Exceptions (RuntimeExceptions)**: Exceptions that occur at runtime (e.g., `NullPointerException`, `ArrayIndexOutOfBoundsException`). They usually indicate programming logic errors. You are not forced to handle them explicitly.

### 2. Keywords
* **`try`**: Defines a block of code to be tested for errors.
* **`catch`**: Defines a block of code to be executed if an error occurs in the `try` block.
* **`finally`**: Defines a block of code to be executed regardless of whether an exception occurred or not (useful for closing resources).
* **`throw`**: Used to explicitly throw an exception.
* **`throws`**: Used in a method signature to declare that this method might throw specific exceptions.

### 3. Exception Chaining
Often, you catch one exception (e.g., `SQLException`) and re-throw it as another (e.g., a custom `DatabaseException`). It is crucial to pass the original exception as the "cause" to the new constructor so that the full stack trace is preserved.

---

## PART II: Package Content & Solutions

This package demonstrates how to handle various exception scenarios, create custom exceptions, and control program flow during errors.

### 1. Basics & Syntax (`ExceptionExample`)
* **`unsafe()`**: A method declaring that it throws Checked Exceptions (`FileNotFoundException`).
* **Multi-catch**: Demonstrates catching multiple exception types in a single block (`catch (FileNotFoundException | ArithmeticException e)`).
* **`finally`**: Shows that the finally block executes even if an exception is caught.

### 2. Custom Exceptions (`MyException`)
* **Creation**: Defines a custom class `MyException` extending `RuntimeException`.
* **Constructors**: Implements constructors that accept a message and a "cause" (another exception), enabling exception chaining.

### 3. Practice Tasks (`exceptionpractise`)
A collection of tasks covering specific error handling patterns:

* **Unchecked Exceptions**:
    * `Task1`: Demonstrates catching a `NullPointerException` when calling a method on a null object.
    * `Task2`: Handling `ArrayIndexOutOfBoundsException` when accessing an invalid array index.

* **Logic & Wrapping**:
    * `Task3`: A method `method(a, b)` that throws a custom `MyException` when dividing by zero. This wraps the low-level `ArithmeticException` (or manual check) into a domain-specific error.

* **Exception Chaining**:
    * `Task5`: Demonstrates the pattern of catching a low-level exception (`RuntimeException`) and re-throwing it wrapped in `MyException`. This preserves the original error stack trace for debugging.

* **Polymorphic Catching**:
    * `Task6`: A robust example where a method randomly throws one of several exceptions (Checked and Unchecked).
    * **Handling**: The `try-catch` block is structured from **specific** to **general**:
        1.  `FileNotFoundException` (Specific Checked)
        2.  `MyException` (Specific Custom)
        3.  `IllegalArgumentException` (Specific Unchecked)
        4.  `Exception` (Catch-all for anything else).