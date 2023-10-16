package com.jxd.orderingsys.service.impl;

import com.jxd.orderingsys.dao.IUserLoginMapper;
import com.jxd.orderingsys.model.Emp;
import com.jxd.orderingsys.model.UserLogin;
import com.jxd.orderingsys.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName UserLoginServiceImpl
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/9 8:41
 * @Version 1.0
 */
@Service
public class UserLoginServiceImpl implements IUserLoginService {
    @Resource
    private IUserLoginMapper userLoginMapper;
    @Override
    public Emp login(UserLogin userLogin) {
        return userLoginMapper.selectByUserNameAndPassword(userLogin);
    }
}
