package com.lucky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 风萧萧兮 on 2017/3/21.
 */
@Controller
@RequestMapping(value="/AdminManage")
public class AdminManage {

    /**
     * 后台登录界面
     * @return
     */
    @RequestMapping(value="/AdminLogin")
    public String AdminLogin(){
        return "views/admin/login";
    }

    @RequestMapping(value="/LoginIn")
    public String LoginIn(){
        return null;
    }

    @RequestMapping(value="/Register")
    public String AdminRegister(){
        return "/views/admin/register";
    }

    @RequestMapping(value="/RegisterIn")
    public String RegisterIn(){



        return "ok";
    }

}
