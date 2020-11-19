package cloud.cnki.common_log;


import cloud.cnki.api.feign.RemoteOperLogService;
import cloud.cnki.common_log.aspect.OperLogAspect;
import cloud.cnki.common_log.listen.LogListener;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author durjx
 * @date 2020 11/18
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogAutoConfiguration {
    private final RemoteOperLogService logService;

    @Bean
    public LogListener sysOperLogListener() {
        return new LogListener(logService);
    }

    @Bean
    public OperLogAspect operLogAspect() {
        return new OperLogAspect();
    }
}