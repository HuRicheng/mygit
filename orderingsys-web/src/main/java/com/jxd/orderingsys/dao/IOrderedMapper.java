package com.jxd.orderingsys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxd.orderingsys.model.Ordered;
import com.jxd.orderingsys.vo.OrderWithAll;
import com.jxd.orderingsys.vo.OrderWithEmp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IOrderedMapper extends BaseMapper<Ordered> {

    int selectIdByUUid(@Param("uuid") String uuid);


    boolean managerConfirm(@Param("orderids") List<Integer> orderids);

    boolean systemConfirm(@Param("orderids") List<Integer> orderids);


    IPage<OrderWithAll> getAllOrderWithAllList(IPage<OrderWithAll> page, Map<String,String> querymap);

    boolean delectBatch(@Param("orderids") List<Integer> orderids);

    OrderWithAll getOrderWithAllById(@Param("oid") Integer oid);
}
