package dev.tpcoder.springmongozoneddatetimedemo.config;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MongoConfigTest {

    @Test
    @DisplayName("Convert ZonedDateTime -> Date")
    void convertZonedDateTimeToDate_success_expectDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        ZonedDateTime initDate = ZonedDateTime.parse("2021-01-01T10:05:10+07:00", formatter);
        Date date = MongoConfig.ZonedDateTimeToDate.INSTANCE.convert(initDate);
        Assertions.assertEquals(initDate.toInstant().getEpochSecond(), date.toInstant().getEpochSecond());
    }

    @Test
    @DisplayName("Convert Date -> ZonedDateTime")
    void convertDateToZonedDateTime_success_expectZonedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        ZonedDateTime initDate = ZonedDateTime.parse("2021-01-01T10:05:10+07:00", formatter);
        Date date = Date.from(initDate.toLocalDateTime().toInstant(ZoneOffset.UTC));
        ZonedDateTime zonedDateTime = MongoConfig.DateToZonedDateTime.INSTANCE.convert(date);
        Assertions.assertEquals(date.toInstant().getEpochSecond(), zonedDateTime.toInstant().getEpochSecond());
    }
}
