package com.itfd.mapper;

import com.itfd.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作员工工作经历表
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存员工工作经历数据，要提前绑定这段工作经历对应的员工id
     * @param exprList
     */
    void insertBatch(List<EmpExpr> exprList);

    // 根据员工id删除员工工作经历
    void deleteByEmpId(List<Integer> empids);
}
