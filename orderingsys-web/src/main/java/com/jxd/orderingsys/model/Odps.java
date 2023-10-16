package com.jxd.orderingsys.model;


/**
 * @ClassName Odps
 * @Description TODO
 * @Author 胡日成 你好
 * @Date 2023/10/8 19:33
 * @Version 1.0
 */
public class Odps {
    private int orderId;     //订单编号
    private Integer eid;    //订餐人员编号

    public Odps() {
    }

    public Odps(int orderId, int eid) {
        this.orderId = orderId;
        this.eid = eid;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
}
