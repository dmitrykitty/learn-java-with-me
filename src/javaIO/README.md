# Java Input/Output (I/O)

## PART I: Comprehensive Theoretical Introduction

Java I/O is a vast API designed to handle the input and output of data (files, network, pipes). To use it effectively, you must understand not just "how to read a file", but how the classes are structured to prevent resource leaks.

### 1. Class Hierarchy & Interfaces
Understanding the hierarchy helps you see the "big picture" and use polymorphism effectively.

**Key Interfaces:**
* **`AutoCloseable`** (Java 7): The root interface for any resource that needs to be closed (files, sockets, database connections).
    * **Contract**: Defines the `close()` method.
    * **Benefit**: Allows the object to be used in a **`try-with-resources`** block, ensuring automatic cleanup even if an exception occurs.
* **`Closeable`**: Extends `AutoCloseable`. Specific to I/O streams.
* **`Flushable`**: Implemented by Output Streams and Writers. Forces buffered output bytes to be written out to the underlying stream.

**The Four Pillars (Abstract Base Classes):**
All I/O classes in Java inherit from one of these four parents. They all implement `Closeable`.

1.  **`InputStream`**: Base class for reading raw bytes.
2.  **`OutputStream`**: Base class for writing raw bytes.
3.  **`Reader`**: Base class for reading characters.
4.  **`Writer`**: Base class for writing characters.

### 2. The Stream Concept
At its core, Java I/O is based on the concept of a **Stream**. A stream is a continuous, sequential flow of data that you can open, read/write, and **must close**.

### 3. The Great Divide: Bytes vs. Characters
The most important distinction in Java I/O is *what* the stream carries.

| Feature | **Byte Streams** (Low Level) | **Character Streams** (High Level) |
| :--- | :--- | :--- |
| **Unit of Data** | 8-bit Bytes (Binary) | 16-bit Unicode Characters |
| **Usage** | Images, Audio, Videos, serialized Objects. | Text files (.txt, .xml, .json, source code). |
| **Abstract Base** | `InputStream`, `OutputStream` | `Reader`, `Writer` |
| **Handling** | Does not understand "letters". | Handles **Encoding** (UTF-8, ASCII) automatically. |

### 4. The Decorator Pattern (Buffering)
Raw streams (like `FileInputStream`) read/write one byte at a time, which is extremely slow because each operation requires a System Call (Disk I/O).
To fix this, Java uses **Buffered Streams** (`BufferedInputStream`, `BufferedReader`). They wrap the raw stream and process data in large chunks (memory buffers), drastically reducing disk access.

### 5. Legacy I/O vs. NIO (New I/O)
* **`java.io.File` (Legacy)**: Represents a file path. It had limitations (poor error handling, no symbolic link support).
* **`java.nio.file` (Java 7+)**: Introduced `Path`, `Paths`, and the utility class `Files`. It is modern, cleaner, and generally preferred for file system operations.

---

## PART II: Package Content & Solutions

This package explores the different ways to handle data in Java, from raw bytes to high-level text analysis.

### 1. Byte Streams (`input_stream`, `outputstream`)
* **Reading Raw Data**: `InputStreamExample` demonstrates reading a file byte-by-byte using `FileInputStream`. It highlights the importance of closing the stream (implementing `Closeable`) to avoid memory leaks.
* **Writing Data**: `OutputStreamExample` shows how to write bytes to a file using `FileOutputStream`. It demonstrates the **append mode** (adding to the end of a file instead of overwriting).

### 2. Character Streams (`reader`, `wirter`)
* **Efficient Reading**: `ReaderExample` uses `BufferedReader` combined with `FileReader`. It showcases the modern `lines()` method which returns a Stream of strings, allowing for functional processing of text.
* **Writing Text**: `WriterExample` uses `BufferedWriter` to write strings and new lines efficiently.

### 3. File System Operations (`files`, `input_output_streams_File`)
* **Legacy Approach**: `FileRunner` explores the old `java.io.File` class—checking existence, permissions (`canExecute`), and creating directories (`mkdirs`).
* **Modern Approach (NIO)**: `FilesExample` demonstrates `java.nio.file.Files`. It uses static utility methods like `Files.write` and `Files.newBufferedWriter` for cleaner code.

### 4. Object Serialization (`serializable`)
* **Persisting Objects**: `SerializableDeserializable` saves a Java object (`Person`) to a `.out` file using `ObjectOutputStream` and reads it back using `ObjectInputStream`.
* **The `transient` Keyword**: The `Person` class implements `Serializable` but marks the `name` field as `transient`. This means the name is **excluded** from serialization (e.g., for security reasons), so it will be `null` upon deserialization.

### 5. Practical Tools (`practice`)
This package contains real-world utilities built using Java I/O:

* **Code Refactoring Tool**: `CodeEditor` reads a `.java` source file as a string and replaces access modifiers (e.g., changing `public` to `private`).
* **Text Analysis**:
    * `WordStructure`: Uses `Scanner` (which is also `Closeable`) to find words starting with vowels or detecting word chains.
    * `Counter`: Calculates statistics like digit sequences in lines or letter frequency distributions.
* **Text Manipulation**: `TextEditor` reads a file and writes its content backwards (line by line) to another file using Stream API.