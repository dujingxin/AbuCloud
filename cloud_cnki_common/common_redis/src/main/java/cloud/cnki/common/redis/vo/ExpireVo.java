package cloud.cnki.common.redis.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ExpireVo
 *
 * @author durjx
 * @date 2020-11-02
 */
@Data
public class ExpireVo {
    @NotBlank(message = "key不能为空")
    private  String key;
    @NotBlank(message = "expireTime不能为空")
    private  long expireTime;
}
