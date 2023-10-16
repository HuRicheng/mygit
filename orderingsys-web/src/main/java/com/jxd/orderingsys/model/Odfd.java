package com.jxd.orderingsys.model;


/**
 * @ClassName Odfd
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/8 19:41
 * @Version 1.0
 */
public class Odfd {
    private int orderId;        //订单编号
    private Long foodId;         //餐品编号
    private int foodCount;      //餐品数量
    private double foodPrice;   //餐品价格
    private String foodComments;    //餐品备注

    public Odfd() {
    }

    public Odfd(int orderId, Long foodId, int foodCount, double foodPrice, String foodComments) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.foodCount = foodCount;
        this.foodPrice = foodPrice;
        this.foodComments = foodComments;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodComments() {
        return foodComments;
    }

    public void setFoodComments(String foodComments) {
        this.foodComments = foodComments;
    }
}
