package me.czhouyi.demo.domain.utils;

public class Sequence {
    private long lastTimestamp = -1L;
    private long sequence = 0L;

    public synchronized long nextSeq() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format(
                            "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        if (timestamp == lastTimestamp) {
            long sequenceBits = 12L;
            long sequenceMask = ~(-1L << sequenceBits);
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;
        return sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence();
        long s = System.currentTimeMillis();
        for (int i = 0; i < 260000; i++) {
            System.out.println(sequence.nextSeq());
        }
        System.out.println(System.currentTimeMillis() - s);
    }
}
