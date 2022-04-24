package me.czhouyi.demo.domain.utils;

import java.util.Date;

/**
 * NoUtils
 *
 * @author czhouyi@gmail.com
 */
public class NoUtils {

    static Sequence sequence;

    static {
        sequence = new Sequence();
    }

    public static String generate(String prefix) {
        return String.format("%s%s%03d", prefix, DateUtils.formatDateTimeNs(new Date()), sequence.nextSeq() + 1);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(generate("ORDER"));
        }
    }

}
