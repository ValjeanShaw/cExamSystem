package com.lucky.dao;

/**
 * Created by 风萧萧兮 on 2017/3/21.
 */
public class SysUser {
    private String id;
    private String sysUserName;
    private String sysUserPass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getSysUserPass() {
        return sysUserPass;
    }

    public void setSysUserPass(String sysUserPass) {
        this.sysUserPass = sysUserPass;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id='" + id + '\'' +
                ", sysUserName='" + sysUserName + '\'' +
                ", sysUserPass='" + sysUserPass + '\'' +
                '}';
    }
}
