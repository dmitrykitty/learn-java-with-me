# Java Testing (JUnit 5 & AssertJ)

## PART I: Comprehensive Theoretical Introduction

Testing is a crucial phase in the software development lifecycle. In the Java ecosystem, **JUnit 5** is the de facto standard for writing unit tests, while **AssertJ** provides a fluent API for writing rich and readable assertions.

### 1. JUnit 5 Architecture
Unlike previous versions, JUnit 5 is composed of three sub-projects:
1.  **JUnit Platform**: The foundation for launching testing frameworks on the JVM. It defines the `TestEngine` API.
2.  **JUnit Jupiter**: The combination of the new programming model (annotations like `@Test`) and extension model.
3.  **JUnit Vintage**: Provides a `TestEngine` for running older JUnit 3 and 4 based tests (backward compatibility).

### 2. Test Lifecycle
JUnit manages the lifecycle of the test class. By default, a new instance of the test class is created **for each** test method (`Lifecycle.PER_METHOD`) to ensure isolation.
* **`@BeforeAll`**: Executed once before all tests. Must be `static` (unless lifecycle is `PER_CLASS`).
* **`@BeforeEach`**: Executed before every single test method.
* **`@Test`**: Denotes the actual test method.
* **`@AfterEach`**: Executed after every single test method.
* **`@AfterAll`**: Executed once after all tests are finished.

### 3. Assertions (Validation)
* **JUnit Standard**: Basic assertions like `assertEquals(expected, actual)`, `assertTrue(condition)`.
* **AssertJ (Fluent)**: Allows chaining assertions that read like English sentences.
    * *Example*: `assertThat(users).hasSize(2).contains(john);`
    * *Benefit*: Better readability and much more descriptive error messages when tests fail.

---

## PART II: Package Content & Solutions

This module demonstrates how to test a simple business service (`UserService`) using modern testing techniques.

### 1. The System Under Test (`com.dnikitin.service`)
* **`UserService`**: A simple service class that manages a list of `User` objects.
    * It contains logic to prevent adding `null` users (throws `NullPointerException`).
    * It uses `Optional` when retrieving users by name to handle cases where a user doesn't exist.

### 2. JUnit 5 Features (`UserServiceTest`)
This class demonstrates configuration and structure:

* **Lifecycle Configuration**:
    * `@TestInstance(Lifecycle.PER_CLASS)`: Changes behavior so one test class instance is created for all tests. This allows `@BeforeAll` and `@AfterAll` methods to be **non-static**.
* **Ordering**:
    * `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)`: Allows defining a specific execution order using `@Order(1)`.
* **Organization**:
    * **`@Nested`**: Used to create inner classes (e.g., `UsersListTest`) that group related tests together hierarchically. This improves report readability.
    * **`@DisplayName`**: Provides custom, human-readable names for tests instead of method names.
    * **`@Tag`**: Labels tests (e.g., `"exception"`, `"userlist"`) so they can be filtered and run selectively (e.g., via Maven or a Launcher).

### 3. Assertion Styles Demonstrated
The tests compare standard JUnit assertions vs. AssertJ.

* **Grouped Assertions (`assertAll`)**:
    * JUnit 5 feature that executes *all* assertions in the block, even if the first one fails.
    * *Code*: `assertAll(() -> assertEquals(...), () -> assertThat(...))`
* **Exception Testing**:
    * **JUnit style**: `assertThrows(NullPointerException.class, () -> service.add(null))`
    * **AssertJ style**:
        ```java
        assertThatThrownBy(() -> service.add(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("User is NULL")
            .hasNoCause();
        ```
* **Collection Testing (AssertJ)**:
    * Verifying list content regardless of order: `.containsExactlyInAnyOrderElementsOf(...)`.

### 4. Optional Handling
Demonstrates how to test methods returning `Optional<T>`:
* Using `.ifPresent()` within the test to unwrap the value and perform assertions inside the consumer.

### 5. Programmatic Execution (`TestLauncher`)
* **`JUnit Platform Launcher`**: Demonstrates how to run tests via code (main method) rather than a build tool (Maven/Gradle).
* **Capabilities**:
    * **Discovery**: Selecting specific packages (`selectPackage`) or classes.
    * **Filtering**: Running only tests with specific tags (`TagFilter.includeTags("exception")`).
    * **Listeners**: Attaching a `SummaryGeneratingListener` to capture and print test execution results to the console.