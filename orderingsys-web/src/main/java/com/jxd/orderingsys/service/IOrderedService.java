package com.jxd.orderingsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.orderingsys.model.Ordered;
import com.jxd.orderingsys.model.OrderedWithOdfd;
import com.jxd.orderingsys.vo.OrderWithAll;

import java.util.List;
import java.util.Map;

public interface IOrderedService extends IService<Ordered> {


    int getIdByUUid(String uuid);

    boolean managerConfirm(List<Integer> orderids);

    boolean systemConfirm(List<Integer> orderids);

    Map<String,Object> getAllOrderWithAllList(Map<String,String> map);

    boolean deleteBatch(List<Integer> orderids);

    OrderWithAll getOrderById(Integer oid);
}
