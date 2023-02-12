package com.xxl.admin.core.route.strategy;

import com.google.common.collect.Maps;
import com.xxl.admin.core.conf.XxlJobAdminConfig;
import com.xxl.admin.core.route.ExecutorRouter;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;
import org.apache.commons.collections4.MapUtils;

import java.util.List;
import java.util.Map;

public class ExecutorRouteWeight extends ExecutorRouter {

    /**
     * 路径负载
     */
    public static Map<String, Integer> addressWeight = Maps.newConcurrentMap();

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        XxlJobAdminConfig.getAdminConfig().getXxlJobRegistryDao();
        String address = addressList.get(0);
        if (addressList.size() > 1) {
            for (int i = 1; i < addressList.size(); i++) {
                if (MapUtils.getIntValue(addressWeight, address, 0) >
                        MapUtils.getIntValue(addressWeight, addressList.get(i), 0)) {
                    address = addressList.get(i);
                }
            }
        }
        return new ReturnT<String>(address);
    }

}
