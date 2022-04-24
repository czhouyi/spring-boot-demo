package me.czhouyi.demo.domain.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtils
 *
 * @author czhouyi@gmail.com
 */
@Slf4j
public class DateUtils {

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE1_FORMATTER = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_TIME1_FORMATTER = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat DATE_TIME_NS_FORMATTER = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static String formatDate(Date date) {
        return DATE_FORMATTER.format(date);
    }

    public static String formatDate1(Date date) {
        return DATE1_FORMATTER.format(date);
    }

    public static String formatDateTime(Date date) {
        return DATE_TIME_FORMATTER.format(date);
    }

    public static String formatDateTime1(Date date) {
        return DATE_TIME1_FORMATTER.format(date);
    }

    public static String formatDateTimeNs(Date date) {
        return DATE_TIME_NS_FORMATTER.format(date);
    }

    public static Date parseDate(String dateStr) {
        try {
            return DATE_FORMATTER.parse(dateStr);
        } catch (ParseException e) {
            log.warn(e.getMessage(), e);
            throw new RuntimeException("日期格式不正确");
        }
    }

    public static Date parseDateTime(String dateStr) {
        try {
            return DATE_TIME_FORMATTER.parse(dateStr);
        } catch (ParseException e) {
            log.warn(e.getMessage(), e);
            throw new RuntimeException("日期格式不正确");
        }
    }

    public static Date addDay(Date date, Integer delta) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, delta);
        return calendar.getTime();
    }

}
