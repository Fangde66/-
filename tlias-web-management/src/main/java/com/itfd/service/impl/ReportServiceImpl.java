package com.itfd.service.impl;

import com.itfd.mapper.EmpMapper;
import com.itfd.pojo.JobOption;
import com.itfd.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    EmpMapper empMapper;
    @Override
    public JobOption countEmpJob() {
        // 1.调用mapper方法获取职位统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData(); // map:(positon:'',num:1)...

        // 封装统计结果
        // 遍历lsit集合，封装每个map集合的key值字段对应的值
        List<Object> position = list.stream().map(jobData -> jobData.get("position")).toList();
        List<Object> num = list.stream().map(jobData -> jobData.get("num")).toList();
        return new JobOption(position, num);
    }

    @Override
    public List<Map<String, Object>> getCountEmpGender() {
        return empMapper.countEmpGenderData();
    }
}
