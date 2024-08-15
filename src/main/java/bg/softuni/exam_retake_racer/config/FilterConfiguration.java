package bg.softuni.exam_retake_racer.config;

import bg.softuni.exam_retake_racer.util.RequestRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FilterConfiguration {
    @Bean
    public RequestRateLimiter requestRateLimiter() {
        return new RequestRateLimiter(250, TimeUnit.MINUTES.toMillis(10));
    }
}
