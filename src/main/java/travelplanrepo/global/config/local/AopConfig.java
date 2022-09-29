package travelplanrepo.global.config.local;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import travelplanrepo.global.common.logtracer.LogTrace;
import travelplanrepo.global.common.logtracer.LogTraceAspect;

@Configuration
@RequiredArgsConstructor
public class AopConfig {

    private final LogTrace logTrace;

    @Bean
    @ConditionalOnProperty(value = "logTracer", havingValue = "true")
    public LogTraceAspect logTraceAspect() {
        return new LogTraceAspect(logTrace);
    }
}
