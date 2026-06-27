package com.itfd.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 分页查询前端响应过来的请求参数
 */
public class ClazzQueryParam {
    private Integer page = 1; // 页码,默认1
    private Integer pageSize = 10; // 每页数据量,默认10
    private String name; // 班级名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 结课时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 开课时间

    public ClazzQueryParam() {
    }

    public ClazzQueryParam(Integer page, Integer pageSize, String name, LocalDate begin, LocalDate end) {
        this.page = page;
        this.pageSize = pageSize;
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "ClazzQueryParam{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", name='" + name + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClazzQueryParam that = (ClazzQueryParam) o;
        return Objects.equals(page, that.page) && Objects.equals(pageSize, that.pageSize) && Objects.equals(name, that.name) && Objects.equals(begin, that.begin) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, name, begin, end);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
