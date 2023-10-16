package com.jxd.orderingsys.service;


import com.jxd.orderingsys.model.Menu;

import java.util.List;

public interface IMenuService {
    List<Menu> getMenu(int role);
}
