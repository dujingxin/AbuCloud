package cloud.cnki.common.redis.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * RedisVo
 *
 * @author durjx
 * @date 2020-11-02
 */
@Data
public class RedisVo {
    @NotBlank(message = "key不能为空")
    private String key;
    @NotBlank(message = "value不能为空")
    private Object value;
    @NotBlank(message = "expireTime不能为空")
    private long expireTime;
}
