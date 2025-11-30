# Java Date & Time API

## PART I: Comprehensive Theoretical Introduction

Prior to Java 8, date and time handling in Java was problematic. The old classes (`java.util.Date`, `java.util.Calendar`) were mutable (not thread-safe), had confusing design (months indexed from 0), and lacked clarity.

The modern **Date and Time API** (package `java.time`, JSR-310) fixes these issues. It is **immutable**, **thread-safe**, and based on the ISO calendar system.

### 1. Machine Time vs. Human Time
The API makes a clear distinction between how machines measure time and how humans perceive it.

* **Machine Time (`Instant`)**: Represents a single point on the timeline. It is a count of seconds/nanoseconds from the Unix Epoch (1970-01-01). It is timezone-agnostic (always UTC).
* **Human Time**: Represents dates and times as seen on a wall clock or calendar.
    * **`LocalDate`**: Date without time (e.g., Birthday, `2023-10-05`).
    * **`LocalTime`**: Time without date (e.g., Alarm clock, `07:30`).
    * **`LocalDateTime`**: Date and Time, but *without* timezone context. It is just a description ("Meeting on Oct 5th at 10 AM").
    * **`ZonedDateTime`**: Full date-time with specific time-zone rules (e.g., daylight saving transitions). It links a `LocalDateTime` with a `ZoneId`.

### 2. Period vs. Duration
Calculating the amount of time between two points:
* **`Period`**: Uses date-based units (Years, Months, Days). Used with `LocalDate`.
* **`Duration`**: Uses time-based units (Hours, Minutes, Seconds, Nanos). Used with `Instant` or `LocalTime`.

### 3. Manipulation & Adjusters
Since objects are immutable, methods like `plusDays(1)` do not modify the object but return a **new copy**.
* **`DateTimeFormatter`**: Thread-safe alternative to `SimpleDateFormat` for parsing strings into dates and formatting dates into strings.
* **`TemporalAdjuster`**: A strategy pattern for complex date math (e.g., "Find the next Friday", "Find the last day of the month").

---

## PART II: Package Content & Solutions

This package contains solutions to common date/time manipulation problems, demonstrating the power of the new API.

### 1. General Usage (`DateTimeExamples`)
* **Basics**: Creating instances of `ZonedDateTime`, `LocalDateTime`, and `LocalDate`.
* **Manipulation**: Using `plus()`, `minus()`, and `truncatedTo()` to modify time values.

### 2. Practical Tasks (`practise`)
A collection of solved tasks covering parsing, formatting, and calculations:

* **`DateTimeTaskSolutions`**:
    * **Formatting/Parsing**: Converting strings like `"26-03-1968T09:24"` into objects using custom patterns (`DateTimeFormatter`).
    * **Calculations**: Using `ChronoUnit.DAYS.between()` to count days and `Duration.between()` to count seconds between two dates.
    * **Time Zones**: Converting a `LocalDateTime` to an `Instant` by applying a specific timezone (e.g., `"America/Chicago"`) to handle offsets correctly.

* **Custom Logic**:
    * **`TemporalAdjusterExample`**: Implements a custom `TemporalAdjuster` using a lambda expression.
    * **Scenario**: The logic determines whether a given date is closer to the *previous* January 1st or the *next* January 1st and adjusts the date accordingly. This demonstrates how to encapsulate complex business rules into reusable date adjusters.