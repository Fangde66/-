package com.itfd.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfd.mapper.OperateLogMapper;
import com.itfd.pojo.OperateLog;
import com.itfd.utils.CurrentHolder;
import com.itfd.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

/**
 * 日志记录切面
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;
    public static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    //用于 Java 对象和 JSON 之间的转换。
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 环绕通知，拦截带有@Log注解的方法
     */
    @Around("@annotation(com.itfd.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 执行目标方法
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            // 记录结束时间
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;

            // 记录操作日志
            recordOperateLog(joinPoint, result, costTime);
        }

        return result;
    }

    /**
     * 记录操作日志到数据库
     */
    private void recordOperateLog(ProceedingJoinPoint joinPoint, Object result, long costTime) {
        try {
            // 获取请求对象
            ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes == null) {
                return;
            }

            HttpServletRequest request = attributes.getRequest();

            // 从请求中获取操作人ID（假设在登录时已存入session或token中）
            Integer operateEmpId = getOperateEmpId(request);

            // 构建操作日志对象
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateEmpId(operateEmpId);
            operateLog.setOperateTime(LocalDateTime.now());
            operateLog.setClassName(joinPoint.getTarget().getClass().getName());
            operateLog.setMethodName(joinPoint.getSignature().getName());

            // 将方法参数转换为JSON字符串
            try {
                operateLog.setMethodParams(objectMapper.writeValueAsString(joinPoint.getArgs()));
            } catch (Exception e) {
                operateLog.setMethodParams("参数序列化失败");
            }

            // 将返回值转换为JSON字符串
            try {
                operateLog.setReturnValue(objectMapper.writeValueAsString(result));
            } catch (Exception e) {
                operateLog.setReturnValue("返回值序列化失败");
            }

            operateLog.setCostTime(costTime);

            // 插入数据库
            operateLogMapper.insert(operateLog);
        } catch (Exception e) {
            // 日志记录失败不应影响业务逻辑
            e.printStackTrace();
        }
    }

    /**
     * 从请求中获取操作人ID
     * 这里需要根据你的实际登录逻辑来实现
     */
    private Integer getOperateEmpId(HttpServletRequest request) {
        return CurrentHolder.getCurrentId();
    }
}
