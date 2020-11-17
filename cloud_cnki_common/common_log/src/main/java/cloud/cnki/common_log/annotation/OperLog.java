package cloud.cnki.common_log.annotation;

import cloud.cnki.common_log.enums.BusinessType;

import java.lang.annotation.*;

/**
 * OperLog
 * 记录操作日志注解
 * @author durjx
 * @date 2020-11-17
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {
    /**
     * 模块名称
     */
    public String MODEL_NAME() default "";

    /**
     * 操作类型
     */
    public BusinessType BUSINESS_TYPE() default  BusinessType.OTHER;

    /**
     * 是否保存请求的参数
     */
    public boolean SAVE_REQUEST_DATA() default  true;
}
