package com.jxd.orderingsys.model;

import java.util.List;

/**
 * @ClassName OrderedWithOdfd
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/9 10:35
 * @Version 1.0
 */
public class OrderedWithOdfd {
    private Integer id;     //订单申请编号
    private String deptname;    //费用部门名称
    private String time;        //申请时间
    private int applicantId;    //申请人编号
    private String orderType;   //订餐类型
    private int orderCount;     //订餐数量
    private double totalPrice;  //订单总金额
    private String state;       //订单状态
    private String comments;    //订单备注
    private String uuid;        //uuid
    private List<Odfd> odfdList;  //订单餐品
    private List<Integer> eids;  //订餐人员编号

    public OrderedWithOdfd() {
    }


    public OrderedWithOdfd(Integer id, String deptname, String time, int applicantId, String orderType, int orderCount, double totalPrice, String state, String comments, String uuid, List<Odfd> odfdList, List<Integer> eids) {
        this.id = id;
        this.deptname = deptname;
        this.time = time;
        this.applicantId = applicantId;
        this.orderType = orderType;
        this.orderCount = orderCount;
        this.totalPrice = totalPrice;
        this.state = state;
        this.comments = comments;
        this.uuid = uuid;
        this.odfdList = odfdList;
        this.eids = eids;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Odfd> getOdfdList() {
        return odfdList;
    }

    public void setOdfdList(List<Odfd> odfdList) {
        this.odfdList = odfdList;
    }

    public List<Integer> getEids() {
        return eids;
    }

    public void setEids(List<Integer> eids) {
        this.eids = eids;
    }

    public Ordered getOrdered (){
        return new Ordered(
                this.id,
                this.time,
                this.applicantId,
                this.orderType,
                this.orderCount,
                this.totalPrice,
                this.state,
                this.comments,
                this.uuid);
    }
}
