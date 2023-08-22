package com.aqara.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimeUtil {

    public static long getDaysDifference(Date dateTime) {
        Date date = new Date();
        //当前时间
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime dateTime1 = LocalDateTime.ofInstant(dateTime.toInstant(), ZoneId.systemDefault());
        return ChronoUnit.HOURS.between(dateTime1, localDateTime);
    }

    public static long getTowDaysDifference(Date date, Date date1) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime dateTime1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault());
        return ChronoUnit.HOURS.between(dateTime1, localDateTime);
    }
}
