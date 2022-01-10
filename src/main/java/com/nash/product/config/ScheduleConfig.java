package com.nash.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Description：定时任务配置
 * Author: zhujian from nashcloud
 * Date: Created in 2021/8/1 7:58 下午
 */
@Configuration
@EnableAsync
public class ScheduleConfig {
    /**
     * 线程池常规数量
     */
    private int corePoolSize = 10;
    /**
     * 线程池最大数量
     */
    private int maxPoolSize = 50;
    /**
     * 队列容量
     */
    private int queueCapacity = 10;
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }
}
