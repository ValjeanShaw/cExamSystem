package com.lucky.controller;

import com.lucky.dao.ActiveUser;
import com.lucky.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by 风萧萧兮 on 2017/3/21.
 */
@Controller
@RequestMapping(value="/AdminManage")
public class AdminManage {

    @Autowired
    private AdminUserService adminUserService;
    /**
     * 跳转到后台登录界面
     * @return
     */
    @RequestMapping(value="/AdminLogin")
    public String AdminLogin(){
        return "views/admin/login";
    }

    /**
     * 用户名和密码必须使用post方式传输
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/LoginIn",method= RequestMethod.POST)
    @ResponseBody
    public String LoginIn(String username,String password,HttpSession session) throws SQLException {
        //获得用户名和密码登录
        ActiveUser activeUser = adminUserService.LoginValiService(username,password);
        if(activeUser != null){
            //登录成功将id存入session
            session.setAttribute("AdminLoginId",activeUser.getSysuserId());
            session.setAttribute("AdminLoginUsername",activeUser.getUsername());
            session.setAttribute("AdminMenu",activeUser.getMenus());
            session.setAttribute("AdminPermission",activeUser.getPermissions());
            session.setAttribute("AdminJsonMenu",activeUser.getMenuJson());
            return "ok";
        }else{
            return "用户名或密码不正确";
        }
    }


}
