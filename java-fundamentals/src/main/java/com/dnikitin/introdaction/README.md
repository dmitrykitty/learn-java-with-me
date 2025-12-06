# Introduction to Java Classes & Objects

## PART I: Comprehensive Theoretical Introduction

Java is a strictly Object-Oriented language. Unlike C++ or Python, you cannot write code outside of a class. Every piece of logic must be encapsulated within a **Class**.

### 1. The Cosmic Superclass: `java.lang.Object`
In Java, **every single class** implicitly extends the `Object` class. If you don't write `extends`, Java assumes `extends Object`. This means every object in Java inherits a common set of methods defined in `Object`.

**Key Inherited Methods:**
* **`toString()`**: Returns a string representation of the object. Default is `ClassName@HashHex`. Usually overridden to print field values.
* **`equals(Object o)`**: Checks if two objects are "equal". Default behavior is reference equality (`==`).
* **`hashCode()`**: Returns an integer hash code (memory address by default). Crucial for HashMaps.
* **`getClass()`**: Returns the runtime class of the object (Reflection).
* **`wait()` / `notify()`**: Primitives for multithreading (synchronization).

### 2. Anatomy of a Class
A class is a blueprint for creating objects (instances).
* **Fields (Attributes)**: The state of the object.
* **Methods (Behavior)**: What the object can do.
* **Constructors**: Special code blocks used to initialize a new object.

### 3. Static vs. Instance
* **Instance Members**: Belong to a specific object. `computer1.ram` can be different from `computer2.ram`.
* **Static Members**: Belong to the **Class** itself. Shared by all instances. `Computer.count` would be the same for everyone.

---

## PART II: Package Content & Solutions

This package explores the basic syntax of defining classes, creating objects, and managing their state.

### 1. Class Structure & Modifiers (`lesson2`)
* **`Computer`**: A comprehensive example of a Java class.
    * **Constructors**: Shows constructor overloading (multiple ways to create an object) and the use of `this` keyword.
    * **Access Modifiers**: Demonstrates `private` (internal state), `public`, and package-private access levels.
    * **Static**: Uses a `static int count` variable to track how many Computer objects have been created globally.
* **Composition**: The `Computer` class contains a `MotherBoard` object, demonstrating the "Has-A" relationship (A Computer *has a* Motherboard).
* **Execution**: `ComputerRunner` instantiates objects using the `new` keyword and accesses static methods via the class name (`Computer.getCount()`).

### 2. Logic & Encapsulation (`lesson9`)
* **Task**: Create a class to manage time intervals (hours, minutes, seconds).
* **`TimeInterval`**:
    * **Data Hiding**: Fields are `private`, preventing invalid direct modification.
    * **Logic**: Methods like `getTotalSeconds()` encapsulate the math required to convert time units.
    * **Object Interaction**: The `sum(TimeInterval t1, TimeInterval t2)` method demonstrates how objects of the same type interact to produce a new result.