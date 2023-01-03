package com.xxl.job.core.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author xtpeach
 */
@Configuration
@EnableAsync
public class TaskPoolConfig {

    /**
     * 配置核心线程数
     */
    @Value("${async.executor.thread.core_pool_size:10}")
    private int corePoolSize;

    /**
     * 配置最大线程数
     */
    @Value("${async.executor.thread.max_pool_size:50}")
    private int maxPoolSize;

    /**
     * 配置队列大小
     */
    @Value("${async.executor.thread.queue_capacity:200}")
    private int queueCapacity;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    @Value("${async.executor.thread.keep_alive_seconds:60}")
    private int keepAliveSeconds;

    /**
     * 配置线程池中的线程的名称前缀
     */
    @Value("${async.executor.thread.name.prefix:async-executor--}")
    private String namePrefix;

    /**
     * 设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
     */
    @Value("${async.executor.thread.name.awaitTerminationSeconds:60}")
    private int awaitTerminationSeconds;

    /**
     * 配置线程池实例对象
     *
     * @return
     */
    @Bean("asyncTaskExecutor")
    public Executor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 配置队列大小
        executor.setQueueCapacity(queueCapacity);
        // 线程池维护线程所允许的空闲时间
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(namePrefix);
        // 用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(awaitTerminationSeconds);
        // 使用预定义的异常处理类
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

}