package com.jxd.orderingsys.vo;

import com.jxd.orderingsys.model.Emp;

import java.util.List;

/**
 * @ClassName OrderWithEmp
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/10 15:54
 * @Version 1.0
 */
public class OrderWithEmp {
    private int id;     //订单申请编号
    private String time;        //申请时间
    private String dname;       //部门名称
    private int applicantId;    //申请人编号
    private String orderType;   //订餐类型
    private int orderCount;     //订餐数量
    private double totalPrice;  //订单总金额
    private String state;       //订单状态
    private String comments;    //订单备注
    private String uuid;        //uuid
    private List<Emp> empList;  //订餐人员

    public OrderWithEmp() {
    }

    public OrderWithEmp(int id, String time, String dname, int applicantId, String orderType, int orderCount, double totalPrice, String state, String comments, String uuid, List<Emp> empList) {
        this.id = id;
        this.time = time;
        this.dname = dname;
        this.applicantId = applicantId;
        this.orderType = orderType;
        this.orderCount = orderCount;
        this.totalPrice = totalPrice;
        this.state = state;
        this.comments = comments;
        this.uuid = uuid;
        this.empList = empList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
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

    public List<Emp> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Emp> empList) {
        this.empList = empList;
    }
}
