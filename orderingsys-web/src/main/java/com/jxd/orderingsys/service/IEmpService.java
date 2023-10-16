package com.jxd.orderingsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.orderingsys.model.Emp;

import java.util.List;

public interface IEmpService extends IService<Emp> {
    List<Emp> getEmpListWithDept(int deptId);
}
