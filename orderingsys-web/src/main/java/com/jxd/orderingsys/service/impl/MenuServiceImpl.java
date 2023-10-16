package com.jxd.orderingsys.service.impl;


import com.jxd.orderingsys.dao.IMenuMapper;
import com.jxd.orderingsys.model.Menu;
import com.jxd.orderingsys.service.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MenuServiceImpl
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/7 15:14
 * @Version 1.0
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Resource
    private IMenuMapper menuMapper;

    @Override
    public List<Menu> getMenu(int role) {
        return menuMapper.getMenus(role);
    }
}
