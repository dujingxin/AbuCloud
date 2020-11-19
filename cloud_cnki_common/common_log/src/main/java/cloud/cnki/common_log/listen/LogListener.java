package cloud.cnki.common_log.listen;

import cloud.cnki.api.entity.SysOperLogEntity;
import cloud.cnki.api.feign.RemoteOperLogService;
import cloud.cnki.common_log.event.SysOperLogEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * LogListener
 *
 * @author durjx
 * @date 2020-11-18
 */
@AllArgsConstructor
@Slf4j
public class LogListener {
    final RemoteOperLogService remoteOperLogService;

    @Async
    @Order
    @EventListener(SysOperLogEvent.class)
    public void listenOperLog(SysOperLogEvent event) {
        SysOperLogEntity sysOperLog = (SysOperLogEntity) event.getSource();
        remoteOperLogService.saveLog(sysOperLog);
        log.info("远程操作日志记录成功：{}", sysOperLog);
    }

    @Async
    @Order
    @EventListener(SysOperLogEvent.class)
    public CompletableFuture<List<SysOperLogEntity>> getLog() {
        List<SysOperLogEntity> logs = remoteOperLogService.getLog();
        log.info("远程查询日志记录成功：{}", logs);
        return CompletableFuture.completedFuture(logs);
    }
}
