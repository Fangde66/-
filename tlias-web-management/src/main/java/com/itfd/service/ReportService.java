package com.itfd.service;

import com.itfd.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    // 统计各个职位的员工人数
    JobOption countEmpJob();

    List<Map<String, Object>> getCountEmpGender();
}
