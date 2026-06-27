package com.itfd.pojo;

import java.util.List;
import java.util.Objects;

/**
 * 职位信息数据统计类
 */
public class JobOption {
    private List jobList; // 职位名称
    private List dataList; // 每个职位对应的数量

    public JobOption() {
    }

    public JobOption(List jobList, List dataList) {
        this.jobList = jobList;
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "JobOption{" +
                "jobList=" + jobList +
                ", dataList=" + dataList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JobOption jobOption = (JobOption) o;
        return Objects.equals(jobList, jobOption.jobList) && Objects.equals(dataList, jobOption.dataList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobList, dataList);
    }

    public List getJobList() {
        return jobList;
    }

    public void setJobList(List jobList) {
        this.jobList = jobList;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }
}
