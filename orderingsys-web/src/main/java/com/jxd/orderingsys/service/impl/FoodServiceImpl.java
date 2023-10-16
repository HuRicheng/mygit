package com.jxd.orderingsys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.orderingsys.dao.IFoodMapper;
import com.jxd.orderingsys.model.Food;
import com.jxd.orderingsys.model.Ordered;
import com.jxd.orderingsys.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FoodServiceImpl
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/9 18:07
 * @Version 1.0
 */
@Service
public class FoodServiceImpl extends ServiceImpl<IFoodMapper, Food> implements IFoodService {
    @Resource
    private IFoodMapper foodMapper;
    @Override
    public Map<String, Object> getAllFoodList(Map<String, String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");

        Map<String, Object> map = new HashMap(4);
        if(page != null && limit != null){
            IPage<Food> pages = new Page(Integer.parseInt(page),Integer.parseInt(limit));
            IPage<Food> pageResult = foodMapper.getAllFoodList(pages,queryMap);
            map.put("count",pageResult.getTotal());
            map.put("data",pageResult.getRecords());
            map.put("code",0);
            return map;
        }else{
            map.put("data", (Object)null);
            map.put("code", "500");
            map.put("msg", "参数不符");
            map.put("count", (Object)null);
            return map;
        }
    }
}
