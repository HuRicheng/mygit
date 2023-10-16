package com.jxd.orderingsys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxd.orderingsys.model.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IEmpMapper extends BaseMapper<Emp> {
    List<Emp> selectByDeptId(@Param("deptId") int deptId);
}
