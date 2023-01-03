
package com.xxl.job.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * os utils
 *
 */
public class OSUtils {

    private static final Logger logger = LoggerFactory.getLogger(OSUtils.class);

    private static final SystemInfo SI = new SystemInfo();

    private static final String TWO_DECIMAL = "0.00";

    private static HardwareAbstractionLayer hal = SI.getHardware();

    private static final int CPU_FACTOR = 50;

    private static final int MEMORY_FACTOR = 50;

    private OSUtils() {
    }

    /**
     * get memory usage Keep 2 decimal
     * 
     * @return percent %
     */
    private static double memoryUsage() {
        GlobalMemory memory = hal.getMemory();
        double memoryUsage = (memory.getTotal() - memory.getAvailable()) * 0.1
                / memory.getTotal() * 10;

        DecimalFormat df = new DecimalFormat(TWO_DECIMAL);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(df.format(memoryUsage));
    }

    /**
     * get cpu usage
     *
     * @return cpu usage
     */
    private static double cpuUsage() {
        CentralProcessor processor = hal.getProcessor();
        double cpuUsage = processor.getSystemCpuLoad(0L);

        DecimalFormat df = new DecimalFormat(TWO_DECIMAL);
        df.setRoundingMode(RoundingMode.HALF_UP);

        return Double.parseDouble(df.format(cpuUsage));
    }

    public static int calculateWeight() {
        try {
            return (int) (cpuUsage() * CPU_FACTOR + memoryUsage() * MEMORY_FACTOR);
        } catch (Exception e) {
            logger.error("获取机器性能失败", e);
            return 100;
        }
    }

}
