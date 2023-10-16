package com.jxd.orderingsys.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @ClassName Food
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/8 19:26
 * @Version 1.0
 */
public class Food {
    @TableId(type = IdType.AUTO)
    private int id;  //餐品编号
    private String name;    //餐品名称
    private double price;   //餐品单价
    private Integer visibility; //可见性

    public Food() {
    }

    public Food(int id, String name, double price, Integer visibility) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.visibility = visibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", visibility=" + visibility +
                '}';
    }
}
