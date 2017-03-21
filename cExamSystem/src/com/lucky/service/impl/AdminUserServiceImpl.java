package com.lucky.service.impl;

import com.lucky.dao.SysUser;
import com.lucky.mapper.SysUserMapper;
import com.lucky.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 风萧萧兮 on 2017/3/21.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    //自动注入mapper进行操作

    private SysUserMapper sysUserMapper;

    /**
     * 注册用户
     * @param sysUser
     * @return
     */
    @Override
    public int RegisterUser(SysUser sysUser) {

        sysUserMapper.RegisterSysUser(sysUser);
        return 0;
    }
}
