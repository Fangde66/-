package com.itfd.mapper;

import com.itfd.pojo.Clazz;
import com.itfd.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 查询全体班级信息
     * @return 封装成Clazz对象的全体班级信息
     */
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz")
    List<Clazz> findAll();

    /**
     * 分页查询班级信息,根据姓名，开课时间，结课时间查询
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 根据id删除班级数据
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);
}
