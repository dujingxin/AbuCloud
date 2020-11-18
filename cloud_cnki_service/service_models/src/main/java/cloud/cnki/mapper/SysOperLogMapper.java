package cloud.cnki.mapper;

import cloud.cnki.api.entity.SysOperLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 操作日志记录 Mapper 接口
 *
 * @author durjx
 * @since 2020-11-17
 */
@Repository
public interface SysOperLogMapper extends BaseMapper<SysOperLogEntity> {

}
