package com.jxd.orderingsys.vo;

/**
 * @ClassName EmpVO
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/11 9:31
 * @Version 1.0
 */
public class EmpVO {
    private int id;
    private String name;

    public EmpVO() {
    }


    public EmpVO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
