package fastdfs;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO
 *
 * @Author: FengJie
 * @Date: 2019/6/1 11:23
 */
public class Be {
    // 配置线程池，Spring默认线程池没有设置大小，如果出现阻塞，可能会出现OOM

    @Bean("eventThread")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数，转换是个很耗时的过程，所以直接排队执行
        executor.setCorePoolSize(1);
        // 设置最大线程数
        executor.setMaxPoolSize(1);
        // 设置队列容量
        executor.setQueueCapacity(100);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称
        executor.setThreadNamePrefix("eventThread-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }

    /**
     * 内部消息总线
     */
    @Service
    @EnableAsync
    public class EventBus implements ApplicationEventPublisherAware {
        private ApplicationEventPublisher publisher;

        @Override
        public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
            this.publisher = applicationEventPublisher;
        }

        public void add(ApplicationEvent event) {
            publisher.publishEvent(event);
        }
    }

    /**
     * 事件类
     */
    public class UploadEvent extends ApplicationEvent {
        public UploadEvent(Object source) {
            super(source);
        }
    }

    public class ConvertEvent extends ApplicationEvent {
        public ConvertEvent(Object source) {
            super(source);
        }
    }

    /**
     * 监听类
     */
    @Component
    public class UploadListener {
        /**
         * 使用自定义的线程池
         */
        @Async("eventThread")
        @EventListener
        public void process(UploadEvent event) {
        }
    }

    @Component
    public class ConvertListener {
        @EventListener
        @Async("eventThread")
        public void process(ConvertEvent event) {
        }
    }
}