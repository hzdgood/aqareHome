package com.aqara.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateDifference {

    public static void main(String[] args) {

        try {
            // 输出结果
            System.out.println("1111:" + getDaysDifference("2023-05-23 12:20:32") + "天");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // chronoUnitTest();
    }

    private static long getDaysDifference(String dateTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(dateTime);
        return getDaysDifference(date);
    }

    private static long getDaysDifference(Date dateTime) {
        Date date = new Date();
        //当前时间
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime dateTime1 = LocalDateTime.ofInstant(dateTime.toInstant(), ZoneId.systemDefault());
        return ChronoUnit.HOURS.between(dateTime1, localDateTime);
    }

    private static void chronoUnitTest() {
        //当前时间
        LocalDateTime today = LocalDateTime.now();
        //2020-05-21 00:00:00:00
        LocalDateTime birthDate = LocalDateTime.of(2020, 5, 21, 0, 0, 0, 0);
        System.out.println("相差的天数: " + ChronoUnit.DAYS.between(birthDate, today));
        System.out.println("相差的时数: " + ChronoUnit.HOURS.between(birthDate, today));
        System.out.println("相差的分数: " + ChronoUnit.MINUTES.between(birthDate, today));
        System.out.println("相差的秒数: " + ChronoUnit.SECONDS.between(birthDate, today));
    }
}
