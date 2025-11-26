package datetimeAPI.practise;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeTaskSolutions {
    static void main() {
        /*
         * Create a `LocalDateTime` object representing the date **June 25, 2020, at 19:47**. Using this object,
         * create another `LocalDateTime` object representing a date **3 months after** the first one.
         * Print the `LocalDate` and `LocalTime` contained within it to the console.
         */
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 25, 19, 47);
        System.out.println(localDateTime);

        LocalDateTime nextLocalTimeDate = localDateTime.plusMonths(3L);
        System.out.println(nextLocalTimeDate.toLocalTime());
        System.out.println(nextLocalTimeDate.toLocalDate());

        System.out.println(changeDateFormat(LocalDateTime.now()));
        LocalDateTime dateTimeFromString = getDateTimeFromString("26-03-1968T09:24");

        Instant instant = dateTimeFromString.toInstant(ZoneId.of("America/Chicago").getRules().getOffset(dateTimeFromString));
        //time to utc
        System.out.println(instant);
        System.out.println(instant.toEpochMilli()); //amount of sec from unix time (ujemna bo przed 1970)

        LocalDate localDate = LocalDate.of(2018, 6, 17);
        System.out.println(daysBetweenTwoDates(localDate,LocalDate.now()));

        System.out.println(secondsBetweenTwoMidNights(localDate, LocalDate.now()));

        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1); //2025-11-26T19:58:55.549129500
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime); //2025-11-26T19:58:55.549129500+01:00
        Instant instantNow = Instant.now();
        System.out.println(instantNow); //2025-11-26T18:58:55.549129500Z
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime); //2025-11-26T19:58:55.549129500+01:00[Europe/Warsaw]

        ZonedDateTime zonedDateTime1 = instantNow.atZone(ZoneId.of("Africa/Cairo"));
        System.out.println(zonedDateTime1);
    }

    /**
     * Create a `LocalDate` object representing **today's date**. Convert this date into a string with the
     * format `"05.05.2017"` (dd.MM.yyyy). Print this string to the console.
     */
    private static String changeDateFormat(LocalDateTime date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return date.format(dateTimeFormatter);
    }

    /**
     * Given a string `"26-03-1968T09:24"`. obtain a `LocalDateTime` object representing
     * the date parsed from this string.
     */
    private static LocalDateTime getDateTimeFromString(String string) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm"); //'T' because not special symbol
        return LocalDateTime.parse(string, dateTimeFormatter);
    }

    /**
     * Create a `LocalDate` object representing **today's date**. Create another `LocalDate` object representing the
     * date **July 7, 2018**. Using these created objects, calculate the number of **days** between them.
     */
    private static long daysBetweenTwoDates(LocalDate date1, LocalDate date2) {
        return ChronoUnit.DAYS.between(date1, date2);
        //return Period.between(date1, date2).getDays(); //zwraca ilosc dni bez uwzglednienia mies i roku
    }

    /**
     *Given the two `LocalDate` objects from the previous task, calculate the number of **seconds** between midnight of
     * the first date and midnight of the second date.
     */
    private static long secondsBetweenTwoMidNights(LocalDate date1, LocalDate date2){ //234921600
        return Duration.between(date1.atStartOfDay(), date2.atStartOfDay()).getSeconds();
        //lub mozna uzyc truncatedTo() jezeli mamy DateTime
        //lub ChronoUnit.DAYS.between()
    }
}
