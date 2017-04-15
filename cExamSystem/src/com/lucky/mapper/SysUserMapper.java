package com.lucky.mapper;

import com.lucky.dao.SysUser;
import org.springframework.stereotype.Component;

/**
 * Created by 风萧萧兮 on 2017/3/21.
 */

public interface SysUserMapper {
    //注册系统用户
    void RegisterSysUser(SysUser sysUser);
    //注册系统用户前查询是否已经注册
    int SelectSysUser(String sysUserName);
    //登录系统用户验证
    SysUser LoginVali(String sysUserName);
    //修改系统管理员的密码
    int EditSysPass(SysUser sysUser);
}
