package com.itfd.service;

import com.itfd.pojo.Emp;
import com.itfd.pojo.EmpQueryParam;
import com.itfd.pojo.Login;
import com.itfd.pojo.PageResult;

import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     * @return PageResult类型data
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void deleteById(List<Integer> ids);

    Emp getInfo(Integer id);

    void updateById(Emp emp);

    Login login(Emp emp);
}
