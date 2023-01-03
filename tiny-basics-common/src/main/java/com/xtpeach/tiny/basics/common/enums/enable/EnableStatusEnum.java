package com.xtpeach.tiny.basics.common.enums.enable;

import java.util.stream.Stream;

/**
 * 是否生效枚举类型
 *
 * @author xtpeach
 */
public enum EnableStatusEnum {

    /**
     * 生效状态码
     */
    ENABLED(true, "生效"),

    /**
     * 失效状态码
     */
    DISABLED(false, "失效");

    /**
     * 状态码
     */
    private Boolean enableStatus;

    /**
     * 状态名称
     */
    private String name;

    EnableStatusEnum(Boolean enableStatus, String name) {
        this.enableStatus = enableStatus;
        this.name = name;
    }

    public Boolean getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Boolean enableStatus) {
        this.enableStatus = enableStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * enableStatusOf
     *
     * @param enableStatus
     * @return
     */
    public static EnableStatusEnum enableStatusOf(Boolean enableStatus) {
        return Stream.of(values()).filter(item
                -> item.getEnableStatus().booleanValue() == enableStatus.booleanValue())
                .findFirst().orElse(DISABLED);
    }

    /**
     * match
     *
     * @param name
     * @return
     */
    public static EnableStatusEnum match(String name) {
        return Stream.of(values()).filter(item
                -> item.getName().equals(name))
                .findFirst().orElse(DISABLED);
    }

}
