# Java Reflection API & Annotations

## PART I: Comprehensive Theoretical Introduction

Reflection is a powerful mechanism that allows a Java program to examine or modify its own internal properties at runtime. For example, it allows a Java class to obtain the names of all its members and display them.

### 1. The Power of Introspection
Normally, you need to know the class name and method names at compile time to write code. With Reflection, you can:
* **Instantiate** new objects, invoke methods, and get/set field values using their names as Strings.
* **Access Private Members**: Breaking standard encapsulation rules using `setAccessible(true)`.
* **Analyze Capabilities**: Check what methods or interfaces a class implements dynamically.

### 2. Why use it?
While you shouldn't use Reflection for standard business logic (it's slower and less safe), it is the backbone of almost every modern Java Framework:
* **Spring**: Uses reflection to inject dependencies (Dependency Injection) and create beans.
* **Hibernate**: Uses reflection to map Java objects to Database tables.
* **JUnit**: Uses reflection to find methods annotated with `@Test` and run them.

### 3. Annotations (Metadata)
Annotations provide data about a program that is not part of the program itself.
* **They have no direct effect** on the operation of the code they annotate.
* **Retention Policy**: Determines how long the annotation is kept.
    * `SOURCE`: Discarded by compiler (e.g., `@Override`).
    * `CLASS`: Recorded in class file but ignored by VM at runtime.
    * `RUNTIME`: Available at runtime for Reflection (Crucial for frameworks).

---

## PART II: Package Content & Solutions

This package demonstrates how to build your own mini-frameworks using Reflection and Annotations.

### 1. Basics of Introspection (`lesson1`)
* **Inspecting Classes**: `ReflectionAPIExample` shows how to obtain the `Class` object (`User.class`), inspect the inheritance hierarchy (superclasses), and list all methods and fields.
* **Metadata**: `User` class extends `Person` and implements interfaces to provide a rich subject for inspection.

### 2. Dynamic Manipulation (`constructors`)
* **Creating Instances**: `TestConstructors` demonstrates how to create an object instance dynamically using `Constructor.newInstance()` instead of the `new` keyword.
* **Breaking Encapsulation**: Shows how to access `private` fields and invoke private methods by setting `field.setAccessible(true)`, bypassing standard access control checks.

### 3. Custom Annotations & Validation (`annotations`)
* **Creating Annotations**: Definition of `@MinAge` with a default value and `RUNTIME` retention.
* **Processing Logic**: `AnnotationValidator` acts as the "engine". It takes any object, scans its fields for the `@MinAge` annotation, and validates the value at runtime. This mimics how **Bean Validation** (Hibernate Validator) works.

### 4. Practical Project: Mini-ORM (`practise/task1`)
* **Concept**: Building a simplified Object-Relational Mapper (like Hibernate).
* **Mapping**: Uses `@Table` to map a class to a DB table and `@Column` to map fields to columns.
* **SQL Generation**: `DataBaseUtils` contains the core logic. It accepts *any* object, uses reflection to read its annotated fields and values, and dynamically constructs a valid SQL `INSERT INTO...` query string. This automation eliminates manual SQL writing for standard operations.