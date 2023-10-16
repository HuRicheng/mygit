package com.jxd.orderingsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.orderingsys.model.OrderedWithOdfd;

public interface IOrderedWithOdfdService extends IService<OrderedWithOdfd> {
    boolean saveOrder(OrderedWithOdfd orderedWithOdfd);
}
