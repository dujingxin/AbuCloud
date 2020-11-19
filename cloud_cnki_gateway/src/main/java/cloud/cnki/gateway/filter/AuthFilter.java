package cloud.cnki.gateway.filter;

import cloud.cnki.core.constants.Constants;
import cloud.cnki.core.domain.Result;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/**
 * AuthFilter
 *
 * @author durjx
 * @date 2020-11-16
 */
@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    private static final String[] WHITE_LIST = {"/auth/login", "/user/register", "/system/v2/api-docs", "/swagger-ui/*"};
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> ops;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        log.info("url:{}", url);
        if (Arrays.asList(WHITE_LIST).contains(url)) {
            return chain.filter(exchange);
        }
        String accessToken = exchange.getRequest().getHeaders().getFirst("accesstoken");

        if (StringUtils.isBlank(accessToken)) {
            return setUnauthorizedResponse(exchange, "token can't null or empty string");
        }
        String user = ops.get(Constants.ACCESS_TOKEN + accessToken);
        if (StringUtils.isBlank(user)) {
            return setUnauthorizedResponse(exchange, "token verify error");
        }
        JSONObject jo = JSONObject.parseObject(user);
        String userId = jo.getString("userId");
        // 查询token信息
        if (StringUtils.isBlank(userId)) {
            return setUnauthorizedResponse(exchange, "token verify error");
        }

        // 设置userId到request里，后续根据userId，获取用户信息
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header(Constants.CURRENT_ID, userId)
                .header(Constants.CURRENT_USERNAME, jo.getString("loginName")).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String msg) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] response = null;
        response = JSON.toJSONString(Result.error(401, msg)).getBytes(StandardCharsets.UTF_8);
        DataBuffer wrap = serverHttpResponse.bufferFactory().wrap(Objects.requireNonNull(response));
        return serverHttpResponse.writeWith(Flux.just(wrap));
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
