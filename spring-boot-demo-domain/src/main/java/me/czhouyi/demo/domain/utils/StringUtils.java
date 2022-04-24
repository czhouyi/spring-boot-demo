package me.czhouyi.demo.domain.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtils
 *
 * @author czhouyi@gmail.com
 */
@Slf4j
public class StringUtils {

    private static final Pattern DOUBLE = Pattern.compile("[+\\-]?\\d+\\.?\\d*");
    private static final Pattern DATE = Pattern.compile("(\\d{0,4}[./\\-]\\d{1,2}[./\\-]\\d{1,2})");
    private static final Pattern TIME = Pattern.compile("(\\d{1,2}:\\d{1,2}:\\d{1,2})");
    private static final Pattern DATETIME = Pattern.compile(
            "(\\d{0,4}[./-]?\\d{1,2}[./-]?\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2})");


    public static boolean isEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }

    public static boolean isNumeric(String str) {
        return org.apache.commons.lang3.StringUtils.isNumeric(str);
    }

    public static boolean isDouble(String str) {
        return DOUBLE.matcher(str).matches();
    }

    public static String toSBC(String input) {
        input = input == null ? "" : input.trim();
        return input.replace("\"", "“")
                .replace(",", "，")
                .replace("(", "（")
                .replace(")", "）")
                .replace(":", "：");
    }

    public static String trim(Object o) {
        return trim(o, "");
    }

    public static String trim(Object o, String defaultString) {
        if (o == null) {
            return defaultString;
        }
        return o.toString().trim();
    }

    public static boolean isUrl(String url) {
        return isNotBlank(url) && (url.startsWith("http://") || url.startsWith("https://"));
    }

    public static boolean isBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.equalsIgnoreCase(str1, str2);
    }

    public static boolean equals(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.equals(str1, str2);
    }

    public static String sBlank(String str) {
        return isBlank(str) ? "" : str;
    }

    public static String sBlank(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String limit(String input, Integer limit) {
        input = sBlank(input);
        int len = input.length();
        if (len > limit) {
            return input.substring(0, limit);
        } else {
            return input;
        }
    }

    public static String cleanString(String input) {
        String ans = sBlank(input).trim();
        ans = ans.replaceAll("\\s", "");
        return ans;
    }

    public static String mergeSpace(String input) {
        String ans = sBlank(input).trim();
        ans = ans.replaceAll("\\s+", " ");
        return ans;
    }

    public static Object toBigDecimal(String input) {
        String ans = cleanString(input);
        ans = ans.replaceAll(",", "")
                .replaceAll("[OoQ]", "0")
                .replaceAll("l", "1");
        int pointIndex = ans.indexOf(".");
        if (pointIndex > 0) {
            String integral = ans.substring(0, Math.min(pointIndex, ans.length()));
            // 如果字符串有点，最多向后取2位
            String precision = ans.substring(pointIndex, Math.min(pointIndex + 3, ans.length()))
                    .replace(".", "");
            ans = integral;
            if (isNotBlank(precision) && isNumeric(precision)) {
                ans = ans + "." + precision;
            }
        }
        if (isEmpty(ans)) {
            return null;
        }
        try {
            if (isDouble(ans)){
                return new BigDecimal(ans);
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return input;
    }

    public static String cleanDateTime(String input) {
        String ans = mergeSpace(input);
        List<String> list = find(ans, DATETIME);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        list = find(ans, DATE);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        list = find(ans, TIME);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return ans;
    }

    public static List<String> find(CharSequence input, Pattern pattern) {
        Matcher mm = pattern.matcher(input);
        List<String> list = new ArrayList<>();
        while (mm.find()) {
            String k = mm.group();
            list.add(k);
        }
        return list;
    }

    public static void main(String[] args) {
        Object input = toBigDecimal("123.11");
        System.out.println(input);
    }

}
