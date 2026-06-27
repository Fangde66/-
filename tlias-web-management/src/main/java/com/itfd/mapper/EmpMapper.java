package com.itfd.mapper;

import com.itfd.anno.AutoInfo;
import com.itfd.enumration.OperationType;
import com.itfd.pojo.Emp;
import com.itfd.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 操作员工基本信息
 */
@Mapper
public interface EmpMapper {
    // --------------------------原始分页查询---------------------------
//    /**
//     * 查询总记录数
//     * @return
//     */
//    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
//    public Long count();
//
//    /**
//     * 分页查询
//     * @param start 起始索引
//     * @param pageSize 每页展示记录数
//     */
//    @Select("select emp.*, dept.name deptName from emp left join dept on emp.dept_id = dept.id " +
//            "order by emp.update_time desc limit #{start}, #{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    /**
      * PageHelper分页查询
      */
//    @Select("select emp.*, dept.name deptName from emp left join dept on emp.dept_id = dept.id order by emp.update_time desc")

    /**
     * 根据条件分页查询
     * @param empQueryParam
     * @return
     */
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 保存员工基本数据和工作经历数据
     * @param emp
     */
    @AutoInfo(value = OperationType.INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id") // 主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "    VALUES (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    // 根据员工id删除员工基本信息
    void deleteById(List<Integer> ids);

    // 根据员工id查询员工所有信息
    Emp getInfo(Integer id);

    // 根据员工id更新员工基本信息
    @AutoInfo(value = OperationType.UPDATE)
    void updateById(Emp emp);

    /**
     * 查询每个职位对应的人数
      * @return map集合的key代表position和num
     */
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别信息
     * @return
     */
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 根据用户名和密码查询员工信息
     */
    @Select("select emp.id,emp.name,emp.username from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
}
