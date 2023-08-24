package com.spotifest.util;

import com.spotifest.model.DateInfo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateUtil {
    public static DateInfo getCurrentDateInfo() {
        LocalDate currentDate = LocalDate.now();

        Month currentMonth = currentDate.getMonth();
        String month = currentMonth.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

        DayOfWeek dayOneDay = currentDate.getDayOfWeek();
        String dayOne = dayOneDay.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int dayOneDate = currentDate.getDayOfMonth();

        DayOfWeek dayTwoDay = currentDate.getDayOfWeek().plus(1);
        String dayTwo = dayTwoDay.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int dayTwoDate = currentDate.getDayOfMonth() + 1;

        DayOfWeek dayThreeDay = currentDate.getDayOfWeek().plus(2);
        String dayThree = dayThreeDay.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int dayThreeDate = currentDate.getDayOfMonth() + 2;

        int year = currentDate.getYear();

        return new DateInfo(month, year, dayOne, dayOneDate, dayTwo, dayTwoDate, dayThree, dayThreeDate);
    }

}
