package com.dnikitin.datetimeAPI;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DateTimeExamples {
    static void main() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        long epochMilli = now.toInstant().toEpochMilli(); //amount of seconds from unit time start
        System.out.println(epochMilli);

        ZonedDateTime nowPlusDay = now.plus(1L, ChronoUnit.DAYS); //or use plusDays()

        ZonedDateTime zonedDateTime = now.truncatedTo(ChronoUnit.DAYS); //wyzerowac do dnia (czyli minuty sekundy i td = 0)
        System.out.println(zonedDateTime);
        //isBefore, isAfter
        LocalDateTime localDateTime = LocalDateTime.now(ZoneOffset.UTC); //czas UTC
        System.out.println(localDateTime);

        // Tworzymy datę: 1 czerwca 2024
        LocalDate dzienDziecka = LocalDate.of(2024, 6, 1);

        // Tworzymy czas: 13:45
        LocalTime przerwa = LocalTime.of(13, 45);
    }
}
