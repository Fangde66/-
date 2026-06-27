package com.itfd.pojo;

import java.time.LocalDate;
import java.util.Objects;

public class EmpExpr {
    private Integer id; //ID
    private Integer empId; //员工ID
    private LocalDate begin; //开始时间
    private LocalDate end; //结束时间
    private String company; //公司名称
    private String job; //职位

    @Override
    public String toString() {
        return "EmpExpr{" +
                "id=" + id +
                ", empId=" + empId +
                ", begin=" + begin +
                ", end=" + end +
                ", company='" + company + '\'' +
                ", job='" + job + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmpExpr empExpr = (EmpExpr) o;
        return Objects.equals(id, empExpr.id) && Objects.equals(empId, empExpr.empId) && Objects.equals(begin, empExpr.begin) && Objects.equals(end, empExpr.end) && Objects.equals(company, empExpr.company) && Objects.equals(job, empExpr.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empId, begin, end, company, job);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
