package com.lucky.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/3/23.
 */
public class ActiveUser implements Serializable {
    private String sysuserId; //系统用户id
    private String username;  //系统用户名

    private List<Permission> menus;  //菜单
    private List<Permission> permissions;   //请求路径

    private String MenuJson;    //计算出的菜单json

    public String getSysuserId() {
        return sysuserId;
    }

    public void setSysuserId(String sysuserId) {
        this.sysuserId = sysuserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Permission> getMenus() {
        return menus;
    }

    public void setMenus(List<Permission> menus) {
        this.menus = menus;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getMenuJson() {
        return MenuJson;
    }

    public void setMenuJson(String menuJson) {
        MenuJson = menuJson;
    }
}
