package com.xtpeach.tiny.basics.common.enums.outcome;

import java.util.stream.Stream;

/**
 * 脚本或程序执行结果
 *
 * @author xtpeach
 */
public enum OutcomeEnum {

    /**
     * 执行成功
     */
    SUCCESS("1", "执行成功"),

    /**
     * 执行异常
     */
    EXECUTION("9", "执行异常"),

    /**
     * 执行失败
     */
    FAILURE("0", "执行失败");

    /**
     * 状态码
     */
    private String code;

    /**
     * 状态名称
     */
    private String name;

    OutcomeEnum(String code, String name) {
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
    public static OutcomeEnum codeOf(String code) {
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
    public static OutcomeEnum match(String name) {
        return Stream.of(values()).filter(item
                -> item.getName().equals(name))
                .findFirst().orElse(FAILURE);
    }

}
