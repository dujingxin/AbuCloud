package cloud.cnki.common_log.event;

import cloud.cnki.api.entity.SysOperLogEntity;
import org.springframework.context.ApplicationEvent;

/**
 * SysOperLogEvent
 *
 * @author durjx
 * @date 2020-11-18
 */
public class SysOperLogEvent extends ApplicationEvent {
    public SysOperLogEvent(SysOperLogEntity operLog) {
        super(operLog);
    }
}
