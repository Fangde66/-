package com.itfd.controller;

import com.itfd.anno.Log;
import com.itfd.pojo.Dept;
import com.itfd.pojo.Result;
import com.itfd.service.DeptService;
import com.itfd.service.impl.DeptServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    // 定义日志记录对象
    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    // Controller层的返回值决定我们最终要给前端响应什么样的数据
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result list(){
//        System.out.println("查询全部部门数据：");
        log.info("查询全部部门数据：");
        List<Dept> deptLists = deptService.findAll();
        return Result.success(deptLists);
    }

    /**
     * 根据id删除部门数据
     */
    @DeleteMapping
    @Log
    public Result delete(Integer id){
//        System.out.println("根据id删除部门：" + id);
        log.info("根据id删除部门：{}" ,id);
        deptService.deleteByID(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @PostMapping
    @Log
    public Result add(@RequestBody Dept dept){
//        System.out.println("new department:" + dept);
        log.info("新增部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
//        System.out.println("id:" + id);
        log.info("根据id查询部门:{}", id);
        Dept dept =  deptService.getById(id); // 返回数据应只有一条，所以有返回类型，再传给Result返回成功的json对象
        return Result.success(dept);
    }

    /**
     * 根据id修改部门
     */
    @PutMapping
    @Log
    public Result updateById(@RequestBody Dept dept){
        log.info("修改部门:{}", dept);
        deptService.updateById(dept);
        return Result.success();
    }
}














