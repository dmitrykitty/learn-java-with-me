
# Java Date & Time API Tasks

1.  Create a `LocalDateTime` object representing the date **June 25, 2020, at 19:47**. Using this object, create another `LocalDateTime` object representing a date **3 months after** the first one. Print the `LocalDate` and `LocalTime` contained within it to the console.

2.  Create a `LocalDate` object representing **today's date**. Convert this date into a string with the format `"05.05.2017"` (dd.MM.yyyy). Print this string to the console.

3.  Given a string `"26-03-1968T09:24"`. obtain a `LocalDateTime` object representing the date parsed from this string.

4.  Use the `LocalDateTime` object from the previous task, convert it into an `Instant` object, specifying the time zone `"America/Chicago"`. Print the number of milliseconds passed since the epoch.

5.  Create a `LocalDate` object representing **today's date**. Create another `LocalDate` object representing the date **July 7, 2018**. Using these created objects, calculate the number of **days** between them.

6.  Given the two `LocalDate` objects from the previous task, calculate the number of **seconds** between midnight of the first date and midnight of the second date.

7.  Create an `Instant` object. Print it to the console. Then, create a `ZonedDateTime` object based on the previous object using the time zone `"Africa/Cairo"`.

8.  Write your own implementation of the `TemporalAdjuster` interface that adds **42 days** to the date.
