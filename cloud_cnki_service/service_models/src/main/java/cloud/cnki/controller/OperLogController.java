package cloud.cnki.controller;

import cloud.cnki.api.entity.SysOperLogEntity;
import cloud.cnki.common_log.annotation.OperLog;
import cloud.cnki.common_log.enums.BusinessType;
import cloud.cnki.service.SysOperLogService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OperLogController
 *
 * @author durjx
 * @date 2020-11-17
 */
@Api(tags = "用户信息处理")
@RestController
@RequestMapping("operLog")
public class OperLogController {

    @Autowired
    private SysOperLogService operLogService;

    /**
     * 新增保存操作日志记录
     */
    @PostMapping("save")
    public void addSave(@RequestBody SysOperLogEntity sysOperLog) {
        operLogService.save(sysOperLog);
    }

    @GetMapping("get")
    public List<SysOperLogEntity> getLog() {
        return operLogService.getAllLog();
    }

    @GetMapping("test1")
    @Operation(description = "测试日志系统")
    @OperLog(MODEL_NAME = "测试日志系统", BUSINESS_TYPE = BusinessType.IMPORT)
    public String test1() {
        return "测试diaoyong";
    }
}
