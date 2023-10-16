package com.jxd.orderingsys.model;

/**
 * @ClassName Menu
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/9 11:45
 * @Version 1.0
 */
public class Menu {
    private String id;
    private String title;
    private String path;
    private String role;

    public Menu() {
    }

    public Menu(String id, String title, String path, String role) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
