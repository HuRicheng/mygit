package com.jxd.orderingsys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.jxd.orderingsys.model.Emp;
import com.jxd.orderingsys.model.UserLogin;

public interface IUserLoginMapper extends BaseMapper<UserLogin> {
    Emp selectByUserNameAndPassword(UserLogin userLogin);
}
