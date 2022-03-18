package devbury.threadscope;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SchedulerConfiguration {

    @Autowired
    private ThreadScopeManager threadScopeManager;

    @Autowired
    private ThreadScopeProperties threadScopeProperties;

    @Bean
    @ConditionalOnMissingBean(AsyncUncaughtExceptionHandler.class)
    public AsyncUncaughtExceptionHandler defaultAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(ThreadScopePropagatingScheduler.class)
    public ThreadScopePropagatingScheduler defaultThreadScopePropagatingScheduler() {
        ThreadScopePropagatingScheduler threadScopePropagatingScheduler = new ThreadScopePropagatingScheduler
                (threadScopeManager);
        threadScopePropagatingScheduler.setPoolSize(threadScopeProperties.getPoolSize());
        threadScopePropagatingScheduler.setThreadNamePrefix(threadScopeProperties.getThreadNamePrefix());
        return threadScopePropagatingScheduler;
    }
}
