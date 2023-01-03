package com.xtpeach.tiny.basics.common.module.core;

import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * UUID 生成器
 */
public class UUIDStrGenerator {

    private UUIDStrGenerator() {

    }

    private static final AtomicInteger counter;
    private static final int JVM;
    private static final int IP;

    static {
        counter = new AtomicInteger(0);
        JVM = (int) (System.currentTimeMillis() >>> 8);
        int ipadd;
        try {
            ipadd = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }

    private static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (bytes[i] & 0xff);
        }
        return result;
    }

    private static short getCount() {
        counter.compareAndSet(Short.MAX_VALUE, 0);
        return (short) counter.incrementAndGet();
    }

    private static String format(int intValue) {
        return StringUtils.leftPad(Integer.toHexString(intValue), 8, "0");
    }

    private static String format(short shortValue) {
        return StringUtils.leftPad(Integer.toHexString(shortValue), 4, "0");
    }

    public static String generate() {
        long now = System.currentTimeMillis();
        short hiTime = (short) (now >>> 32);
        int loTime = (int) now;
        StringBuilder sb = new StringBuilder(32)
                .append(format(hiTime))
                .append(format(loTime))
                .append(format(IP))
                .append(format(JVM))
                .append(format(getCount()));
        sb.setCharAt(0, 'f');
        return sb.toString();
    }

}
