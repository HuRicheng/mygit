package com.jxd.orderingsys.service;

import com.jxd.orderingsys.model.Emp;
import com.jxd.orderingsys.model.UserLogin;

public interface IUserLoginService {
    Emp login(UserLogin userLogin);
}
