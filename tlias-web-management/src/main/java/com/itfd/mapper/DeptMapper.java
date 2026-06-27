package com.itfd.mapper;

import com.itfd.anno.AutoInfo;
import com.itfd.anno.Log;
import com.itfd.enumration.OperationType;
import com.itfd.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询全体部门信息
     * @return 封装成Dept对象的全体部门信息
     */
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    /**
     * 根据id删除部门数据
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteByID(Integer id);

    @AutoInfo(value = OperationType.INSERT)
    @Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    @AutoInfo(value = OperationType.UPDATE)
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void updateById(Dept dept);
}
