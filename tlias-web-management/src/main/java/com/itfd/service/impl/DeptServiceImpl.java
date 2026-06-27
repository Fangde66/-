package com.itfd.service.impl;

import com.itfd.mapper.DeptMapper;
import com.itfd.pojo.Dept;
import com.itfd.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

//    @Autowired
//    private RedisTemplate redisTemplate;

    @Override
    @Cacheable(value = "dept", key = "'list'")
    public List<Dept> findAll() {
//        // 1.缓存中的key名
//        String key = "dept_list";
//        // 2.从缓存中获取数据（存入缓存是什么数据类型，取出就是什么数据类型）
//        List<Dept> deptLists = (List<Dept>) redisTemplate.opsForValue().get(key);
//        if(deptLists != null && deptLists.size() > 0){
//            // 3.缓存中有数据，直接读取缓存数据返回
//            return deptLists;
//        }
//        // 4.缓存中没有数据，查询数据库
//        deptLists = deptMapper.findAll();
//        // 5.将数据库查询结果存入缓存
//        redisTemplate.opsForValue().set(key, deptLists);
//        return deptLists;
        return deptMapper.findAll();
    }

    @Override
    @CacheEvict(value = "dept", key = "'list'")
    public void deleteByID(Integer id) {
        // 删除缓存
//        redisTemplate.delete("dept_list");
        deptMapper.deleteByID(id);
    }

    @Override
    @CacheEvict(value = "dept", key = "'list'")
    public void add(Dept dept) {
        // 补全update_time和create_time 交给springAOP
//        dept.setCreateTime(LocalDateTime.now());
//        dept.setUpdateTime(LocalDateTime.now());
        // 删除缓存
//        redisTemplate.delete("dept_list");
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    @CacheEvict(value = "dept", key = "'list'")
    public void updateById(Dept dept) {
        // 修改更新时间 交给springAOP
//        dept.setUpdateTime(LocalDateTime.now());
        // 删除缓存
//        redisTemplate.delete("dept_list");
        deptMapper.updateById(dept);
    }

}
