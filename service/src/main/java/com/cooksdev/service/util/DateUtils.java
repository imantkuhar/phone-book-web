package com.cooksdev.service.util;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

public class DateUtils {

    private DateUtils() {}

    public static LocalDateTime nowUtc() {
        return LocalDateTime.ofInstant(ZonedDateTime.now().toInstant(), UTC);
    }
}
