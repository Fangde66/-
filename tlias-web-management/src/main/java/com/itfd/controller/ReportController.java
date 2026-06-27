package com.itfd.controller;

import com.itfd.pojo.JobOption;
import com.itfd.pojo.Result;
import com.itfd.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/report")
@RestController
public class ReportController {

    private static final Logger log = LoggerFactory.getLogger(ReportController.class);
    @Autowired
    ReportService reportService;
    /**
     * 统计员工各职位总人数
     */
    @GetMapping("/empJobData")
    public Result getCountEmpJob(){
        log.info("统计员工各职位人数");
        JobOption jobOption = reportService.countEmpJob();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别信息
     */
    @GetMapping("/empGenderData")
    public Result getCountEmpGender(){
        log.info("统计员工不同性别人数");
        List<Map<String, Object>> list = reportService.getCountEmpGender();
        return Result.success(list);
    }
}
