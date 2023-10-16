package com.jxd.orderingsys.dao;


import com.jxd.orderingsys.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMenuMapper {
    /**
     * 根据角色查询菜单信息
     * @return
     * @param role
     */
    List<Menu> getMenus(@Param("role") int role);
}
