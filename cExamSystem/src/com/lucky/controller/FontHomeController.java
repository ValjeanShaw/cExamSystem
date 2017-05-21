package com.lucky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 风萧萧兮 on 2017/5/7.
 */
@Controller
@RequestMapping("/FontHome")
public class FontHomeController {

    /**
     * 返回页面
     * @return
     */
    @RequestMapping("/TeacherHome")
    public String TeacherHome(){
        return "views/font/home";
    }

    /**
     * 返回页面
     * @return
     */
    @RequestMapping("/StudentHome")
    public String StudentHome(){
        return "views/font/stuHome";
    }

}
