package com.jxd.orderingsys.model;

/**
 * @ClassName UserLogin
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/8 19:17
 * @Version 1.0
 */
public class UserLogin {
    private int userid;   //用户id
    private String username;  //用户名
    private String password;  //密码
    private int role;  //用户权限

    public static final int ADMIN_ROLE = 0;     //管理员
    public static final int MANAGER_ROLE = 1;   //经理
    public static final int STAFF_ROLE = 2;     //普通员工

    public UserLogin() {
    }

    public UserLogin(int userid, String username, String password, int role) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
