package cloud.cnki.common.redis.controller;

import cloud.cnki.common.redis.util.RedisUtil;
import cloud.cnki.common.redis.vo.ExpireVo;
import cloud.cnki.common.redis.vo.RedisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * RedisController
 *
 * @author durjx
 * @date 2020-11-02
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    @Autowired
    private RedisUtil redisUtil;
    //=========redis expireTime ================
    @GetMapping("expire/{key}")
    public Long getExpire(@PathVariable String key){
        return  redisUtil.getExpire(key);
    }
    @PutMapping("expire")
    public boolean setExpire(@RequestBody ExpireVo expireVo){
        return redisUtil.expire(expireVo.getKey(),expireVo.getExpireTime());
    }

    //=========redis set ================
    @PostMapping("set")
    public boolean setValue(@RequestBody RedisVo redisVo){
       if (0 == redisVo.getExpireTime()){
           return redisUtil.set(redisVo.getKey(),redisVo.getValue());
       }
        return  redisUtil.set(redisVo.getKey(),redisVo.getValue(),redisVo.getExpireTime());
    }
    @GetMapping("set/{key}")
    public Object getSetByKey(@PathVariable String key){
        return  redisUtil.get(key);
    }
    @DeleteMapping("set/{key}")
    public void deleteSetByKey(@PathVariable String key){
        redisUtil.del(key);
    }
}
