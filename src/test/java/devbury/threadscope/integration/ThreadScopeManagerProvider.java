package devbury.threadscope.integration;

import devbury.threadscope.ThreadScopeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequestEvent;

@Configuration
class ThreadScopeManagerProvider {

    @Autowired
    public ThreadScopeManagerProvider() {
    }

    @Bean
    public ThreadScopeManager threadScopeManager() {
        return new ThreadScopeManager() {
            @Override
            public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
                ServletRequestListenerTest.requestDestroyedCalled = true;
                super.requestDestroyed(servletRequestEvent);
            }
        };
    }
}
