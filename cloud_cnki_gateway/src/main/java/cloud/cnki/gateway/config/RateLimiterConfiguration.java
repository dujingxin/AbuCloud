package cloud.cnki.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * RateLimiterConfiguration
 *
 * @author durjx
 * @date 2020-11-09
 */
@Configuration
public class RateLimiterConfiguration {

    @Bean
    public KeyResolver remoteAddressKeyResolver(){
        return  exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
