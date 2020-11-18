package cloud.cnki.controller;

import cloud.cnki.feign.entity.SysOperLogEntity;
import cloud.cnki.service.SysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * OperLogController
 *
 * @author durjx
 * @date 2020-11-17
 */
@RestController
@RequestMapping("operLog")
public class OperLogController {

    @Autowired
    private SysOperLogService operLogService;

    /**
     * 新增保存操作日志记录
     */
    @PostMapping("save")
    public void addSave(@RequestBody SysOperLogEntity sysOperLog)
    {
        operLogService.save(sysOperLog);
    }
    @GetMapping("get")
    public Object getLog(){
        return operLogService.getById(1).toString();
    }
}
