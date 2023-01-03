package com.xxl.job.admin.core.route;

import com.xxl.job.admin.core.route.strategy.*;

/**
 * Created by xuxueli on 17/3/10.
 */
public enum ExecutorRouteStrategyEnum {

    FIRST("FIRST", new ExecutorRouteFirst()),
    LAST("LAST", new ExecutorRouteLast()),
    ROUND("ROUND", new ExecutorRouteRound()),
    RANDOM("RANDOM", new ExecutorRouteRandom()),
    CONSISTENT_HASH("CONSISTENT HASH", new ExecutorRouteConsistentHash()),
    LEAST_FREQUENTLY_USED("LEAST FREQUENTLY USED", new ExecutorRouteLFU()),
    LEAST_RECENTLY_USED("LEAST RECENTLY USED", new ExecutorRouteLRU()),
    FAILOVER("FAILOVER", new ExecutorRouteFailover()),
    BUSYOVER("BUSYOVER", new ExecutorRouteBusyover()),
    WEIGHT("WEIGHT", new ExecutorRouteWeight()),
    SHARDING_BROADCAST("SHARDING BROADCAST", null);

    ExecutorRouteStrategyEnum(String title, ExecutorRouter router) {
        this.title = title;
        this.router = router;
    }

    private String title;
    private ExecutorRouter router;

    public String getTitle() {
        return title;
    }
    public ExecutorRouter getRouter() {
        return router;
    }

    public static ExecutorRouteStrategyEnum match(String name, ExecutorRouteStrategyEnum defaultItem){
        if (name != null) {
            for (ExecutorRouteStrategyEnum item: ExecutorRouteStrategyEnum.values()) {
                if (item.name().equals(name)) {
                    return item;
                }
            }
        }
        return defaultItem;
    }

}
