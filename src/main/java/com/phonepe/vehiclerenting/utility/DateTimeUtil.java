package com.phonepe.vehiclerenting.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class DateTimeUtil {
    public static LocalDateTime getLocalDateTime(String datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(datetime, formatter);
    }
}
