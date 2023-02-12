package com.xxl.job.core.registry;

public interface UpdateWeightManager {

    String EXECUTOR_WEIGHT = "executor_weight";
    String EXECUTOR_TITLE = "executor_title";
    String EXECUTOR_HANDLER = "executor_handler";

    /**
     * 添加 handler
     *
     * @param handler
     */
    void addXxlJobHandlerList(String handler);

}
