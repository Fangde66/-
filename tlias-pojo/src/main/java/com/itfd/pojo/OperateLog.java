package com.itfd.pojo;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 对应数据库表中操作日志表
 * 用于封装操作日志表的每条数据
 */
public class OperateLog {
    private Integer id; //ID
    private Integer operateEmpId; //操作人ID
    private LocalDateTime operateTime; //操作时间
    private String className; //操作类名
    private String methodName; //操作方法名
    private String methodParams; //操作方法参数
    private String returnValue; //操作方法返回值
    private Long costTime; //操作耗时

    public OperateLog() {
    }

    public OperateLog(Integer id, Integer operateEmpId, LocalDateTime operateTime, String className, String methodName, String methodParams, String returnValue, Long costTime) {
        this.id = id;
        this.operateEmpId = operateEmpId;
        this.operateTime = operateTime;
        this.className = className;
        this.methodName = methodName;
        this.methodParams = methodParams;
        this.returnValue = returnValue;
        this.costTime = costTime;
    }

    @Override
    public String toString() {
        return "OperateLog{" +
                "id=" + id +
                ", operateEmpId=" + operateEmpId +
                ", operateTime=" + operateTime +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", methodParams='" + methodParams + '\'' +
                ", returnValue='" + returnValue + '\'' +
                ", costTime=" + costTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OperateLog that = (OperateLog) o;
        return Objects.equals(id, that.id) && Objects.equals(operateEmpId, that.operateEmpId) && Objects.equals(operateTime, that.operateTime) && Objects.equals(className, that.className) && Objects.equals(methodName, that.methodName) && Objects.equals(methodParams, that.methodParams) && Objects.equals(returnValue, that.returnValue) && Objects.equals(costTime, that.costTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operateEmpId, operateTime, className, methodName, methodParams, returnValue, costTime);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperateEmpId() {
        return operateEmpId;
    }

    public void setOperateEmpId(Integer operateEmpId) {
        this.operateEmpId = operateEmpId;
    }

    public LocalDateTime getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }
}

