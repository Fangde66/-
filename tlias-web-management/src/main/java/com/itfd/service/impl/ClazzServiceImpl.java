package com.itfd.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itfd.mapper.ClazzMapper;
import com.itfd.pojo.Clazz;
import com.itfd.pojo.ClazzQueryParam;
import com.itfd.pojo.PageResult;
import com.itfd.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        // 2.执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        // 3.解析查询结果，并封装
        Page<Clazz> p = (Page<Clazz>) clazzList;

        // 循环遍历p，若当前时间大于结课时间则status为已结课
        p.forEach(clazz -> {
            if (clazz.getEndDate().isBefore(LocalDate.now())){
                clazz.setStatus("已结课");
            }
        });
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}
