package com.lucky.service;

import com.lucky.dao.ActiveUser;
import com.lucky.dao.CommonDao;
import com.lucky.dao.Permission;
import com.lucky.dao.SysUser;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/3/21.
 */
public interface AdminUserService {
    //注册系统用户
    int RegisterUser(SysUser sysUser);
    //注册系统用户前先检查是否有此用户名
    boolean PreRegisterAdminUser(String username);
    //系统登录验证并添加权限
    ActiveUser LoginValiService(String username, String password) throws SQLException;

    //认证登录查询用户拥有的菜单
    List<Permission> findMenuByUserId(String sysuserId) throws Exception;
    //认证登录查询用户拥有的权限
    List<Permission> findPermissionByUser(CommonDao commonDao) throws Exception;

    //修改系统管理员的密码
    boolean EditSysUserPass(SysUser sysUser) throws Exception;

}
