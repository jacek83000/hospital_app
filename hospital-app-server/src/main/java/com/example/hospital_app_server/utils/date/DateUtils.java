package com.example.hospital_app_server.utils.date;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {
    private DateUtils() {}

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getDateXDaysLaterFromNow(int numberOfDays) {
        LocalDate now = LocalDate.now();
        return asDate(now.plusDays(numberOfDays));
    }
}
