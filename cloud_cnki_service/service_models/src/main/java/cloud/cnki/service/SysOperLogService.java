package cloud.cnki.service;


import cloud.cnki.api.entity.SysOperLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 操作日志记录 接口
 *
 * @author durjx
 * @since 2020-11-17
 */
public interface SysOperLogService extends IService<SysOperLogEntity> {

    List<SysOperLogEntity> getAllLog();
}
