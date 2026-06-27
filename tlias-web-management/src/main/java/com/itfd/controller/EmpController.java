package com.itfd.controller;

import com.itfd.pojo.Emp;
import com.itfd.pojo.EmpQueryParam;
import com.itfd.pojo.PageResult;
import com.itfd.pojo.Result;
import com.itfd.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/emps")
@RestController
public class EmpController {

    private static final Logger log = LoggerFactory.getLogger(EmpController.class);

    // 设置redis中营业状态的key名
    private static final String BUSINESS_STATUS_KEY = "business_status";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询：{}", empQueryParam);
        PageResult<Emp> pageResult =  empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 删除员工-数组
     */
//    @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("删除员工：{}", Arrays.toString(ids));
//        return Result.success();
//    }
    /**
     * 删除员工-list
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){ // @RequestParam接收请求参数
        log.info("删除员工：{}", ids);
        empService.deleteById(ids);
        return Result.success();
    }

    /**
     * 根据id查询员工所有信息
     * return 员工信息封装成Emp对象响应给前端
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){ // 路径参数
        log.info("查询员工：{}",id);
        Emp emp = empService.getInfo(id); // id为主键，应只有一个Emp对象，故无需集合封装
        return Result.success(emp);
    }

    /**
     * 修改员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工:{}", emp);
        empService.updateById(emp);
        return Result.success();
    }

    /**
     * 设置公司营业状态，1为上班中，0为下班中
     */
    @PutMapping("/shop/{status}")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置公司营业状态：{}", status == 1 ? "上班中" : "下班中");
        // 存储到redis中
        redisTemplate.opsForValue().set(BUSINESS_STATUS_KEY, status);
        return Result.success();
    }

    /**
     * 获取公司营业状态
     */
    @GetMapping("/shop/status")
    public Result getStatus(){
        log.info("获取公司营业状态");
        Integer status = (Integer) redisTemplate.opsForValue().get(BUSINESS_STATUS_KEY);
        return Result.success(status);
    }
}
