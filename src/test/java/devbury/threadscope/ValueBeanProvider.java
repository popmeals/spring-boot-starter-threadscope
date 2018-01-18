package devbury.threadscope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class ValueBeanProvider {

    @Bean
    @Scope(WebApplicationContext.SCOPE_REQUEST)
    public ValueBean valueBean() {
        return new ValueBean();
    }

    public class ValueBean {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
