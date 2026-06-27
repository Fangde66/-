package com.itfd.controller;

import com.itfd.pojo.Emp;
import com.itfd.pojo.Login;
import com.itfd.pojo.Result;
import com.itfd.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    public static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录：{}", emp);
        Login login = empService.login(emp);
        if(login == null){
            return Result.error("用户名或密码错误");
        }
        return Result.success(login);
    }
}
