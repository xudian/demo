package com.aladen.common.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

@Configuration
@EnableAsync
public class ExecutorConfig {

    @Bean("taskExecutor")
    public ExecutorService taskExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("task-executor-thread-%d").build();

        ExecutorService executor = new ThreadPoolExecutor(10,
                50,
                10L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),
                threadFactory,
                new ThreadPoolExecutor.DiscardPolicy());
        return executor;
    }
}
