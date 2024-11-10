package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String getToday() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return today.format(formatter);
    }

    public static String getSevenDaysFromToday() {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysFromToday = today.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return sevenDaysFromToday.format(formatter);
    }

    public static String getFourteenDaysFromToday() {
        LocalDate today = LocalDate.now();
        LocalDate fourteenDaysFromToday = today.plusDays(14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fourteenDaysFromToday.format(formatter);
    }
}