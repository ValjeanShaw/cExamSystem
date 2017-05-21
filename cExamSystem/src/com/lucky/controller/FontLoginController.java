package com.lucky.controller;

import com.lucky.dao.Student;
import com.lucky.service.StudentService;
import com.lucky.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by 风萧萧兮 on 2017/5/13.
 */
@RequestMapping("/FontLogin")
@Controller
public class FontLoginController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;

    public void StudentLogin(){

    }

    /**
     * 教师登录
     * @param username
     * @param userpass
     * @return
     */
    @RequestMapping("/TeacherIn")
    @ResponseBody
    public String TeacherLogin(String username,String userpass,HttpSession session){
        String teaName = teacherService.teaFontLoginVali(username,userpass);
        if(teaName == null){
            return "error";    //密码匹配出错
        }else{
            //id和姓名存入session
            session.setAttribute("fontUserIdTea",username);
            session.setAttribute("fontUserName",teaName);
            return "teacherok";
        }
    }

    /**
     * 学生登录
     * @param username
     * @param userpass
     * @param session
     * @return
     */
    @RequestMapping("/StudentIn")
    @ResponseBody
    public String fontLoginVali(String username,String userpass,HttpSession session){
        try{
            Student student = studentService.fontLoginVali(username,userpass);
            if(student != null){
                session.setAttribute("fontUserIdStu",student.getStuNum());
                session.setAttribute("fontUserName",student.getStuName());

                return "studentok";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/LoginPage")
    public String LoginPage(){
        return "views/font/fontLogin";
    }

    /**
     *前台退出登录
     */
    @RequestMapping("/fontLoginOut")
    public String fontLoginOut(HttpSession session){
        session.removeAttribute("fontUserIdTea");
        session.removeAttribute("fontUserIdStu");
        session.removeAttribute("fontUserName");
        session.removeAttribute("TheQuestionOfTestPaper");
        session.removeAttribute("TheQuestionOfTestPaper");
        session.removeAttribute("JdugeResult");

        return "views/font/fontLogin";
    }

}
