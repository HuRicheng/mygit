package com.jxd.orderingsys.vo;

/**
 * @ClassName OdfdVO
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/11 11:47
 * @Version 1.0
 */
public class OdfdVO {
    private Long foodId;
    private Integer foodCount;
    private Integer foodPrice;
    private String foodComments;
    private String foodName;

    public OdfdVO() {
    }

    public OdfdVO(Long foodId, Integer foodCount, Integer foodPrice, String foodComments, String foodName) {
        this.foodId = foodId;
        this.foodCount = foodCount;
        this.foodPrice = foodPrice;
        this.foodComments = foodComments;
        this.foodName = foodName;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Integer getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(Integer foodCount) {
        this.foodCount = foodCount;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodComments() {
        return foodComments;
    }

    public void setFoodComments(String foodComments) {
        this.foodComments = foodComments;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
