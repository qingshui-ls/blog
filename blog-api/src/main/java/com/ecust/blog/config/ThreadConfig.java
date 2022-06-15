package com.ecust.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @authour shuangliang
 * @date 2022/6/4 - 21:46
 */
@Configuration
@EnableAsync //开启多线程
public class ThreadConfig {
    @Bean("taskExecutor")
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(5);
        // 设置最大线程数
        executor.setMaxPoolSize(20);
        // 设置线程从活跃时间
        executor.setKeepAliveSeconds(60);
        // 设置队列大小
        executor.setQueueCapacity(Integer.MAX_VALUE);
        // 设置默认线程名称
        executor.setThreadNamePrefix("轻水的博客项目");
        // 等待所以任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 执行初始化
        executor.initialize();
        return executor;
    }
}
