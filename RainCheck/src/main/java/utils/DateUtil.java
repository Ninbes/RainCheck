package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static String getToday() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return today.format(formatter);
    }

    public static String getSevenDaysFromToday() {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysFromToday = today.plusDays(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return sevenDaysFromToday.format(formatter);
    }

    public static String getFourteenDaysFromToday() {
        LocalDate today = LocalDate.now();
        LocalDate fourteenDaysFromToday = today.plusDays(13);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fourteenDaysFromToday.format(formatter);
    }

    public static long getSecondsUntilApiReset() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.plusDays(1).truncatedTo(ChronoUnit.DAYS);
        return ChronoUnit.SECONDS.between(now, midnight);
    }
}
