package com.itfd.aop;

import com.itfd.anno.AutoInfo;
import com.itfd.common.AutoFillCommon;
import com.itfd.enumration.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自动填充切公共字段
 */
@Aspect
@Component
public class AutofillAspect {
    public static final Logger log = LoggerFactory.getLogger(AutofillAspect.class);

    /**
     * 切入点
     */
    @Pointcut("execution(* com.itfd.mapper.*.*(..)) && @annotation(com.itfd.anno.AutoInfo)")
    public void autoFillPointcut() {
    }

    /**
     * 前置通知: 在通知中进行公共字段的赋值
     */
    @Before("autoFillPointcut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行数据填充...");

        // 1.获取到当前被拦截的方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); // 方法签名
        AutoInfo autoInfo = signature.getMethod().getAnnotation(AutoInfo.class); // 获取方法上的注解对象
        OperationType operationType = autoInfo.value(); // 获取数据库操作类型

        // 2.获取到当前被拦截的方法参数--实体对象
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){ // 防止空指针异常
            return;
        }

        Object object = args[0];

        // 3.准备赋值的数据
        LocalDateTime now = LocalDateTime.now();

        // 4.根据不同的操作类型，为对应的属性通过反射来赋值
        if(operationType == OperationType.INSERT){
            try {
                Method setCreatTime = object.getClass().getDeclaredMethod(AutoFillCommon.CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = object.getClass().getDeclaredMethod(AutoFillCommon.UPDATE_TIME, LocalDateTime.class);

                // 通过反射为对象属性赋值
                setCreatTime.invoke(object, now);
                setUpdateTime.invoke(object, now);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (operationType == OperationType.UPDATE){
            try {
                Method setUpdateTime = object.getClass().getDeclaredMethod(AutoFillCommon.UPDATE_TIME, LocalDateTime.class);

                // 通过反射为对象属性赋值
                setUpdateTime.invoke(object, now);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
