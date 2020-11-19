package cloud.cnki.api.feign;

import cloud.cnki.api.entity.SysOperLogEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * RemoteOperLogService
 *
 * @author durjx
 * @date 2020-11-18
 */
@FeignClient(value = "cloud-system"/*, configuration = RemoteLogConfiguration.class*/)
public interface RemoteOperLogService {
    @GetMapping("operLog/get")
    List<SysOperLogEntity> getLog();

    @PostMapping("operLog/save")
    void saveLog(@RequestBody SysOperLogEntity sysOperLog);
}
