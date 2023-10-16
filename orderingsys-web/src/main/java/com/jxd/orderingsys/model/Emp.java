package com.jxd.orderingsys.model;

/**
 * @ClassName Emp
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/8 19:22
 * @Version 1.0
 */
public class Emp {
    private int id;     //员工编号
    private String name;    //员工姓名
    private String sex;     //员工性别
    private String phone;   //员工电话
    private Integer deptId;     //员工所属部门编号
    private int role;       //员工职位

    public Emp() {
    }

    public Emp(int id, String name, String sex, String phone, Integer deptId, int role) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.deptId = deptId;
        this.role = role;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", deptId=" + deptId +
                ", role=" + role +
                '}';
    }
}
