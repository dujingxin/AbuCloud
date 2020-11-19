package cloud.cnki.common_log.aspect;

import cloud.cnki.api.entity.SysOperLogEntity;
import cloud.cnki.common_log.annotation.OperLog;
import cloud.cnki.common_log.enums.BusinessStatus;
import cloud.cnki.common_log.event.SysOperLogEvent;
import cloud.cnki.core.constants.Constants;
import cloud.cnki.core.utils.AddressUtils;
import cloud.cnki.core.utils.IpUtils;
import cloud.cnki.core.utils.StringUtils;
import cloud.cnki.core.utils.spring.SpringContextHolder;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OperLogAspect
 * 操作日志切面
 *
 * @author durjx
 * @date 2020-11-17
 */
@Aspect
@Component
@Slf4j
public class OperLogAspect {
    /**
     * 织入点
     */
    @Pointcut("@annotation(cloud.cnki.common_log.annotation.OperLog)")
    public void logPointCut() {
    }

    /**
     * 处理请求完成后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    /**
     * 请求异常
     *
     * @param joinPoint 切点
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    private void handleLog(JoinPoint joinPoint, Exception exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        OperLog operLog = method.getAnnotation(OperLog.class);
        if (null == operLog) {
            return;
        }
        SysOperLogEntity sysOperLog = new SysOperLogEntity();
        try {
            ServletRequestAttributes servletRequest = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            sysOperLog.setStatus(BusinessStatus.SUCCESS.ordinal());

            HttpServletRequest request = servletRequest.getRequest();
            String ipAddr = IpUtils.getIpAddr(request);
            sysOperLog.setOperIp(ipAddr);
            sysOperLog.setOperUrl(request.getRequestURI());
            sysOperLog.setOperLocation(AddressUtils.getRealAddressByIP(ipAddr));
            String userName = request.getHeader(Constants.CURRENT_USERNAME);
            sysOperLog.setOperName(userName);
            if (null != exception) {
                sysOperLog.setStatus(BusinessStatus.FAIL.ordinal());
                sysOperLog.setErrorMsg(StringUtils.substring(exception.getMessage(), 0, 2000));
            }
            //设置方法
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            sysOperLog.setMethod(className + "." + methodName + "()");
            sysOperLog.setRequestMethod(request.getMethod());

            Object[] args = joinPoint.getArgs();
            getControllerMethodDescription(operLog, sysOperLog, args);
            // 发布事件
            SpringContextHolder.publishEvent(new SysOperLogEvent(sysOperLog));
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    public void getControllerMethodDescription(OperLog log, SysOperLogEntity operLog, Object[] args) throws Exception {
        // 设置action动作
        operLog.setBusinessType(log.BUSINESS_TYPE().ordinal());
        // 设置标题
        operLog.setTitle(log.MODEL_NAME());
        // 是否需要保存request，参数和值
        if (log.SAVE_REQUEST_DATA()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(operLog, args);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     */
    private void setRequestValue(SysOperLogEntity operLog, Object[] args) {
        List<?> param = new ArrayList<>(Arrays.asList(args)).stream().filter(p -> !(p instanceof ServletResponse))
                .collect(Collectors.toList());
        log.debug("args:{}", param);
        String params = JSON.toJSONString(param, true);
        operLog.setOperParam(StringUtils.substring(params, 0, 2000));
    }
}
