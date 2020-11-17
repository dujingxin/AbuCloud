package cloud.cnki.common_log.aspect;

import cloud.cnki.common_log.annotation.OperLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * OperLogAspect
 * 操作日志切面
 * @author durjx
 * @date 2020-11-17
 */
@Aspect
@Component
@Slf4j
class OperLogAspect {
    /**
     * 织入点
     */
    @Pointcut("@annotation(cloud.cnki.common_log.annotation.OperLog)")
    public void logPointCut(){
    }

    /**
     * 处理请求完成后执行
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint){
        handleLog(joinPoint, null);
    }

    /**
     * 请求异常
     * @param joinPoint 切点
     */
    @AfterThrowing(pointcut = "logPointCut()")
    public void doAfterThrowing(JoinPoint joinPoint){

    }

    private void handleLog(JoinPoint joinPoint, Exception exception) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        OperLog operLog = method.getAnnotation(OperLog.class);
        if (null==operLog) {
            return ;
        }


    }
}
