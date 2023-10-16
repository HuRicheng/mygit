package com.jxd.orderingsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.orderingsys.model.Food;

import java.util.Map;

public interface IFoodService extends IService<Food> {
    Map<String, Object> getAllFoodList(Map<String,String> map);
}
