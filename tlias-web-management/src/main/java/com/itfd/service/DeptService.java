package com.itfd.service;

import com.itfd.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void deleteByID(Integer id);

    void add(Dept dept);

    Dept getById(Integer id);

    void updateById(Dept dept);
}
