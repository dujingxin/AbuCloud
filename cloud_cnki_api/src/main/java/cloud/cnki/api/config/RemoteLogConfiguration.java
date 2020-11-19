package cloud.cnki.api.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * RemoteLogConfiguration
 * 细粒度配置 feign 日志级别
 *
 * @author durjx
 * @date 2020-11-18
 */
public class RemoteLogConfiguration {

    @Bean
    public Logger.Level level() {
        return Logger.Level.BASIC;
    }
}
