package com.jxd.orderingsys.model;

/**
 * @ClassName Dept
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/8 19:25
 * @Version 1.0
 */
public class Dept {
    private Integer id;     //部门编号
    private String name;    //部门名称

    public Dept() {
    }

    public Dept(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
