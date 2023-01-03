package com.xtpeach.tiny.basics.common.util.spec;

/**
 * Specification 获取like匹配字符串
 *
 * @author xtpeach
 */
public class SpecLikeStringUtil {

    /**
     * %| str |%
     *
     * @param str
     * @return
     */
    public static String getLikeStringFullMatch(String str) {
        return "%".concat(str).concat("%");
    }

    /**
     * %| str
     *
     * @param str
     * @return
     */
    public static String getLikeStringPreFix(String str) {
        return "%".concat(str);
    }

    /**
     * str |%
     *
     * @param str
     * @return
     */
    public static String getLikeStringSubFix(String str) {
        return str.concat("%");
    }

}
