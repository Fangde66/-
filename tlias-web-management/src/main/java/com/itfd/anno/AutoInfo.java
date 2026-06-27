package com.itfd.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，标记要进行自动填充的方法
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) // 运行时生效
public @interface AutoInfo {
    com.itfd.enumration.OperationType value();
}
