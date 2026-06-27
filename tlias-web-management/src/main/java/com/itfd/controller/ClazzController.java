package com.itfd.controller;

import com.itfd.pojo.Clazz;
import com.itfd.pojo.ClazzQueryParam;
import com.itfd.pojo.PageResult;
import com.itfd.pojo.Result;
import com.itfd.service.ClazzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clazzs")
@RestController // @Controller + @ResponseBody
public class ClazzController {
    // 日志
    public static final Logger log = LoggerFactory.getLogger(ClazzController.class);
    @Autowired
    private ClazzService clazzService;

    /**
     * 查询所有班级信息
     */
    @GetMapping("/list")
    public Result findAll(){
        log.info("查询所有班级信息");
        List<Clazz> clazzes = clazzService.findAll();
        return Result.success();
    }

    /**
     * 分页条件查询班级信息
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询：{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 删除班级
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级：{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }
}
