package com.xtpeach.tiny.basics.common.enums.response;

import java.util.stream.Stream;

/**
 * 响应信息枚举类型
 *
 * @author xtpeach
 */
public enum ResponseCodeEnum {

    /**
     * 响应成功状态码
     */
    SUCCESS("200", "成功"),

    /**
     * 响应失败状态码
     */
    FAILURE("500", "失败");

    /**
     * 状态码
     */
    private String code;

    /**
     * 状态名称
     */
    private String name;

    ResponseCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * codeOf
     *
     * @param code
     * @return
     */
    public static ResponseCodeEnum codeOf(String code) {
        return Stream.of(values()).filter(item
                -> item.getCode().equals(code))
                .findFirst().orElse(FAILURE);
    }

    /**
     * match
     *
     * @param name
     * @return
     */
    public static ResponseCodeEnum match(String name) {
        return Stream.of(values()).filter(item
                -> item.getName().equals(name))
                .findFirst().orElse(FAILURE);
    }

}
