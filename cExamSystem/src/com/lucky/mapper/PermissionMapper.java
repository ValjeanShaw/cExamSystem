package com.lucky.mapper;

import com.lucky.dao.CommonDao;
import com.lucky.dao.Permission;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/3/23.
 */
public interface PermissionMapper {
    //获取系统用户所拥有的菜单
    List<Permission> selectUserMenu(String sysUserId);
    //获取系统用户所拥有的资源
    List<Permission> selectUserPermission(CommonDao commonDao);

}
