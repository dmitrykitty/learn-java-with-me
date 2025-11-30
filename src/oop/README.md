# Object-Oriented Programming (OOP) in Java

## PART I: Comprehensive Theoretical Introduction

Object-Oriented Programming (OOP) is a programming paradigm based on the concept of "objects", which can contain data (fields) and code (methods). Java is an inherently object-oriented language—almost everything is an object.

### 1. The 4 Pillars of OOP
To write good Java code, you must understand these four fundamental principles:

1.  **Encapsulation (Hermetyzacja)**:
    * Bundling data and methods that work on that data within one unit (class).
    * Hiding internal state using access modifiers (`private`) and requiring interaction via public methods (Getters/Setters).
    * *Benefit*: Protects data integrity (e.g., you can't set a negative age).

2.  **Inheritance (Dziedziczenie)**:
    * A mechanism where a new class derives properties and behavior from an existing class.
    * Keyword: `extends`.
    * *Benefit*: Code reusability and establishing "Is-A" relationships.

3.  **Polymorphism (Polimorfizm)**:
    * The ability of an object to take on many forms.
    * A `Laptop` is a `Laptop`, but it is also a `Computer` and an `Object`.
    * **Dynamic Binding**: The specific method implementation to be called is determined at runtime (Overriding).
    * *Benefit*: Flexibility. You can write code that works on a superclass (`Computer`), and it will work for any subclass added later.

4.  **Abstraction (Abstrakcja)**:
    * Hiding complex implementation details and showing only the essential features of the object.
    * Tools: `abstract class` and `interface`.
    * *Benefit*: Reduces complexity and focuses on *what* an object does rather than *how* it does it.

### 2. Relationships
* **Is-A**: Inheritance (A `Warrior` *is a* `Hero`).
* **Has-A**: Composition/Aggregation (A `Computer` *has a* `Ram`).

---

## PART II: Package Content & Solutions

This package demonstrates OOP principles through practical simulations like RPG battles and hardware management.

### 1. Inheritance Hierarchy (`lesson1/task1_inheritance`)
* **Computer System**: A base class `Computer` extended by `Laptop` and `Mobile`.
    * **Constructor Chaining**: Subclasses call `super()` to initialize the parent state.
    * **Composition**: A `Computer` consists of `Ram` and `Ssd` objects (Has-A relationship).
    * **Initialization Blocks**: Demonstrates the execution order of static blocks vs instance blocks during object creation.

### 2. Encapsulation Logic (`lesson1/task2`)
* **`Bankomat`**: Logic for managing money and banknotes.
    * The internal count of bills ($10, $20, $50) is `private`.
    * External code cannot modify the money count directly; it must request a withdrawal via `getMoney()`, which contains the business logic to validate the transaction.

### 3. Abstraction & Interfaces (`lesson2`, `figures`)
* **Geometry System**:
    * **`Shape`**: An abstract base class defining the contract (e.g., `area()` must be implemented).
    * **Interfaces**: `WithPerimeter` and `Internal` define specific capabilities (contracts) that different shapes implement.
    * **Polymorphism**: `ShapeRunner` calculates areas of Circles and Rectangles uniformly, treating them as generic `Shape` objects.

### 4. Advanced Project: Battle Simulation (`battle_simulation`)
A robust RPG combat system combining all OOP concepts.

* **Abstract Base**: `Hero` is an abstract class; you cannot instantiate a generic "Hero", only specific types like `Warrior` or `Mage`.
* **Generics in OOP**: `Hero<T extends Weapon>` ensures type safety. A `Warrior` is strictly typed to use `MeleeWeapon`, preventing them from equipping a `Wand`.
* **Interfaces**: The `Mortal` interface defines shared behavior (alive/dead check) for both Heroes and Enemies, allowing them to be treated similarly in the battle loop.
* **Interaction**: `TrainingGround` simulates a battle loop where polymorphic objects interact dynamically based on their specific implementations of `attackEnemy`.

### 5. Enums (`enums`)
* **`ProcessorType`**: Demonstrates that Enums are classes in Java. They can have fields, methods, and even abstract methods implemented by each constant.