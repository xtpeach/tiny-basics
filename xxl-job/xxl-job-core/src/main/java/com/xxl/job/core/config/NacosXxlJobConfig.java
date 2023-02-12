package com.xxl.job.core.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClientConfiguration;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnClass(NacosDiscoveryClientConfiguration.class)
@ConditionalOnProperty(prefix = "xxl.job.executor", name = "title")
public class NacosXxlJobConfig {

    private Logger logger = LoggerFactory.getLogger(NacosXxlJobConfig.class);

    @Value("${xxl.job.accessToken:null}")
    private String accessToken;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private int port;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    /**
     * 通过nacos获取本机ip，该ip通过docker host模式可以获取正确宿主机ip
     */
    @Resource
    private NacosDiscoveryProperties instance;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAppname(appName);
        // 设置宿主机ip
        xxlJobSpringExecutor.setAddress(genAddressUrl(instance.getIp(), port));
        xxlJobSpringExecutor.setIp(instance.getIp());
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobSpringExecutor;
    }

    /**
     * 拼接 http:// ip : port
     *
     * @param ip
     * @param port
     * @return
     */
    private String genAddressUrl(String ip, int port) {
        return "http://" + ip + ":" + port;
    }

    /**
     * 针对多网卡、容器内部署等情况，可借助 "spring-cloud-commons" 提供的 "InetUtils" 组件灵活定制注册IP；
     *
     *      1、引入依赖：
     *          <dependency>
     *             <groupId>org.springframework.cloud</groupId>
     *             <artifactId>spring-cloud-commons</artifactId>
     *             <version>${version}</version>
     *         </dependency>
     *
     *      2、配置文件，或者容器启动变量
     *          spring.cloud.inetutils.preferred-networks: 'xxx.xxx.xxx.'
     *
     *      3、获取IP
     *          String ip_ = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
     */

}
