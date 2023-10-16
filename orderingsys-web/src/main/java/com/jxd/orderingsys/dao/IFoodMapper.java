package com.jxd.orderingsys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxd.orderingsys.model.Food;
import com.jxd.orderingsys.vo.OrderWithAll;

import java.util.Map;

public interface IFoodMapper extends BaseMapper<Food> {
    IPage<Food> getAllFoodList(IPage<Food> page, Map<String,String> querymap);
}
