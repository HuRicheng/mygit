package com.jxd.orderingsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.orderingsys.dao.IOdpsMapper;
import com.jxd.orderingsys.model.Odps;
import com.jxd.orderingsys.service.IOdpsService;
import org.springframework.stereotype.Service;

/**
 * @ClassName OdpsServiceImpl
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/10 11:46
 * @Version 1.0
 */
@Service
public class OdpsServiceImpl extends ServiceImpl<IOdpsMapper, Odps> implements IOdpsService {
}
