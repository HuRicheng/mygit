package com.jxd.orderingsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.orderingsys.dao.IEmpMapper;
import com.jxd.orderingsys.model.Emp;
import com.jxd.orderingsys.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName EmpServiceImpl
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/10 9:59
 * @Version 1.0
 */
@Service
public class EmpServiceImpl extends ServiceImpl<IEmpMapper, Emp> implements IEmpService {
    @Resource
    private IEmpMapper empMapper;
    @Override
    public List<Emp> getEmpListWithDept(int deptId) {
        return empMapper.selectByDeptId(deptId);
    }
}
