package com.lucky.dao;

/**
 * Created by 风萧萧兮 on 2017/3/21.
 */
public class SysRole {
    private String id;
    private String sysRoleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysRoleName() {
        return sysRoleName;
    }

    public void setSysRoleName(String sysRoleName) {
        this.sysRoleName = sysRoleName;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id='" + id + '\'' +
                ", sysRoleName='" + sysRoleName + '\'' +
                '}';
    }
}
