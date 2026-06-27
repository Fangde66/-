package com.itfd.service;

import com.itfd.pojo.Clazz;
import com.itfd.pojo.ClazzQueryParam;
import com.itfd.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    List<Clazz> findAll();

    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);
}
