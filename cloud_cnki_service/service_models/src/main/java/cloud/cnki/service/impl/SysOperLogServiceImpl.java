package cloud.cnki.service.impl;

import cloud.cnki.feign.entity.SysOperLogEntity;
import cloud.cnki.mapper.SysOperLogMapper;
import cloud.cnki.service.SysOperLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 操作日志记录 接口实现
 *
 * @author durjx
 * @since 2020-11-17
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLogEntity> implements SysOperLogService {

}
