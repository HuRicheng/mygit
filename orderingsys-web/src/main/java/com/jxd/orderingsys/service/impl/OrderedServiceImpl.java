package com.jxd.orderingsys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.orderingsys.dao.IOdfdMapper;
import com.jxd.orderingsys.dao.IOdpsMapper;
import com.jxd.orderingsys.dao.IOrderedMapper;
import com.jxd.orderingsys.model.Ordered;
import com.jxd.orderingsys.model.OrderedWithOdfd;
import com.jxd.orderingsys.service.IOrderedService;
import com.jxd.orderingsys.vo.OrderWithAll;
import com.jxd.orderingsys.vo.OrderWithEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderedServiceImpl
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/9 11:32
 * @Version 1.0
 */
@Service
public class OrderedServiceImpl extends ServiceImpl<IOrderedMapper, Ordered> implements IOrderedService {
    @Resource
    private IOrderedMapper orderedMapper;
    @Resource
    private IOdfdMapper odfdMapper;
    @Resource
    private IOdpsMapper odpsMapper;



    @Override
    public int getIdByUUid(String uuid) {
        return orderedMapper.selectIdByUUid(uuid);
    }



    @Override
    public boolean managerConfirm(List<Integer> orderids) {
        return orderedMapper.managerConfirm(orderids);
    }

    @Override
    public boolean systemConfirm(List<Integer> orderids) {
        return orderedMapper.systemConfirm(orderids);
    }



    @Override
    public Map<String, Object> getAllOrderWithAllList(Map<String, String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");
        Map<String, Object> map = new HashMap(4);
        if(page != null && limit != null){
            IPage<OrderWithAll> pages = new Page(1,1000000);
            IPage<OrderWithAll> pageResult = orderedMapper.getAllOrderWithAllList(pages,queryMap);
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

    @Override
    public boolean deleteBatch(List<Integer> orderids) {
        return orderedMapper.delectBatch(orderids);
    }

    @Override
    public OrderWithAll getOrderById(Integer oid) {
        return orderedMapper.getOrderWithAllById(oid);
    }

}
