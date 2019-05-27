package com.mmolegion.core.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Time {

    public static Date addDays(int numDays) {
        LocalDateTime date = LocalDateTime.now().plusDays(numDays);
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date addHours(int numHours) {
        LocalDateTime date = LocalDateTime.now().plusHours(numHours);
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date addMinutes(int numMinutes) {
        LocalDateTime date = LocalDateTime.now().plusMinutes(numMinutes);
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date minusDays(int numDays) {
        LocalDateTime date = LocalDateTime.now().minusDays(numDays);
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }
}
