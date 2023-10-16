package com.jxd.orderingsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.orderingsys.dao.IOrderedWithOdfdMapper;
import com.jxd.orderingsys.model.Odfd;
import com.jxd.orderingsys.model.Odps;
import com.jxd.orderingsys.model.Ordered;
import com.jxd.orderingsys.model.OrderedWithOdfd;
import com.jxd.orderingsys.service.IOdfdService;
import com.jxd.orderingsys.service.IOdpsService;
import com.jxd.orderingsys.service.IOrderedService;
import com.jxd.orderingsys.service.IOrderedWithOdfdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName OrderedWithOdfdServiceImpl
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/10 11:41
 * @Version 1.0
 */
@Service
public class OrderedWithOdfdServiceImpl extends ServiceImpl<IOrderedWithOdfdMapper, OrderedWithOdfd> implements IOrderedWithOdfdService {
    @Resource
    private IOrderedService orderedService;
    @Resource
    private IOdpsService odpsService;
    @Resource
    private IOdfdService odfdService;
    @Override
    public boolean saveOrder(OrderedWithOdfd orderedWithOdfd) {
        Ordered ordered = orderedWithOdfd.getOrdered();
        String uuid = "";
        if ("".equals(ordered.getUuid()) || ordered.getUuid() == null) {
            uuid = UUID.randomUUID().toString();
            ordered.setUuid(uuid);
        }
        ordered.setTime(new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()));
        boolean flag = orderedService.saveOrUpdate(ordered);
        if (orderedWithOdfd.getId() == 0){
            int orderid = orderedService.getIdByUUid(uuid);
            //保存订单餐品信息
            List<Odfd> odfds = orderedWithOdfd.getOdfdList();
            for (Odfd odfd:odfds) {
                odfd.setOrderId(orderid);
            }
            odfdService.saveBatch(odfds);

            //保存订餐人员信息
            List<Integer> eids = orderedWithOdfd.getEids();
            List<Odps> odpss = new ArrayList<>();
            for (int i = 0; i < eids.size(); i++) {
                Odps odps = new Odps();
                odps.setEid(eids.get(i));
                odps.setOrderId(orderid);
                odpss.add(odps);
            }
            odpsService.saveBatch(odpss);
        }
        return flag;
    }
}
