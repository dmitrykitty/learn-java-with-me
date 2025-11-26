package datetimeAPI.practise;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;

import static java.time.temporal.TemporalAdjusters.*;

public class TemporalAdjusterExample {
    static void main() {
        LocalDate now = LocalDate.now();

        LocalDate localDateTime = now.with(temporal -> temporal.plus(42L, ChronoUnit.DAYS));

        TemporalAdjuster nearestJan1 = temporal -> {
            LocalDate date = LocalDate.from(temporal);
            LocalDate curJan1 = date.with(firstDayOfYear());
            LocalDate nextJan1 = date.with(firstDayOfNextYear());

            return ChronoUnit.DAYS.between(curJan1, date) > ChronoUnit.DAYS.between(date, nextJan1)
                    ? nextJan1
                    : curJan1;
        };

        LocalDate jan1 = now.with(nearestJan1);
        System.out.println(jan1);
    }
}
