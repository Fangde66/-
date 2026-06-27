package com.itfd.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itfd.mapper.EmpExprMapper;
import com.itfd.mapper.EmpMapper;
import com.itfd.pojo.*;
import com.itfd.service.EmpService;
import com.itfd.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmpServiceImpl implements EmpService {

    private static final Logger log = LoggerFactory.getLogger(EmpServiceImpl.class);
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        // 1.调用mapper接口，查询总记录数
//        Long total = empMapper.count();
//
//        // 2.调用mapper接口，查询结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//
//        // 3.封装结果 PageResult
//        return new PageResult<Emp>(total, rows);
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam){
        // 1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        // 2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);

        // 3.解析查询结果，并封装
        Page<Emp> p = (Page<Emp>) empList; // Page父类是ArrayList，ArrayList父类是List，所以可以直接强转
        return new PageResult<Emp>(p.getTotal(),p.getResult());

    }

    @Transactional(rollbackFor = {Exception.class}) // 事务管理
    @Override
    public void save(Emp emp) {
        // 1.新增员工基本信息 交给springAOP
//        emp.setCreateTime(LocalDateTime.now());
//        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp); // 通过主键返回为他的id赋值

        // 2.新增员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){ // 判断集合是否为空
            // 遍历集合，为empId赋值
            exprList.forEach(empExpr ->
                    empExpr.setEmpId(emp.getId()));

            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public void deleteById(List<Integer> ids) {
        // 1.根据id删除员工基本信息
        empMapper.deleteById(ids);

        // 2.根据id删除员工工作经历信息
        empExprMapper.deleteByEmpId(ids);
    }

    @Transactional(rollbackFor = Exception.class) //使用多条SQL语句，需要事务回滚
    @Override
    public void updateById(Emp emp) {
        // 1.根据ID修改员工基本信息
//        emp.setUpdateTime(LocalDateTime.now()); // 更新修改时间 交给springAOP
        empMapper.updateById(emp);

        // 2.根据ID修改员工工作经历信息（先修改，再删除）
        // 删除原本工作经历
        empExprMapper.deleteByEmpId(Arrays.asList(emp.getId())); // Arrays.asList将其转变为list集合
        //添加新传递过来的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            // 为这段员工工作经历绑定对应员工id
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public Login login(Emp emp) {
        // 将传递过来的密码进行md5加密后在匹配
        emp.setPassword(DigestUtils.md5DigestAsHex(emp.getPassword().getBytes()));

        Emp emp1 = empMapper.getByUsernameAndPassword(emp);

        if(emp1 != null){
            log.info("员工登录成功：{}", emp1);
            // 生成 token
            Map<String, Object> clamis = new HashMap<>();
            clamis.put("id", emp1.getId()); // 添加自定义信息
            clamis.put("username", emp1.getUsername());
            String token = JwtUtils.generateJwt(clamis);
            return new Login(emp1.getId(), emp1.getUsername(), emp1.getName(),token);
        }
        return null;
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);
    }
}
