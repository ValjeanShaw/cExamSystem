package com.lucky.controller;

import com.lucky.dao.Student;
import com.lucky.dao.SysUser;
import com.lucky.service.AdminUserService;
import com.lucky.service.StudentService;
import com.lucky.utils.DESUtil;
import com.lucky.utils.PwdUtil;
import org.apache.commons.io.IOExceptionWithCause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by 潇潇 on 2016/12/22.
 * 前台注册和后台注册
 */
@Controller
@RequestMapping("/Register")
public class RegisterController {
    //自动注入系统用户mapper
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private StudentService studentService;

    /**
     * 跳转到后台注册页面
     * @return
     */
    @RequestMapping("/adminRegister")
    public String Register(){
        return "views/admin/register";
    }

    /**
     *
     * @param username
     * @param userpass
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/adminRegisterIn",method = RequestMethod.POST)
    @ResponseBody
    public String AdminRegister(String username,String userpass) throws Exception{
        if(adminUserService.PreRegisterAdminUser(username)){
            return "此用户名已注册！";
        }else{
            SysUser sysUser = new SysUser();
            sysUser.setSysUserName(username);
            String key = "A1B2C3D4E5F60708";         //加密秘钥
            //两次加密   des加密和md5加密
            userpass = PwdUtil.getPassMD5(userpass);
            userpass = DESUtil.encrypt(userpass,key);
            sysUser.setSysUserPass(userpass);
            if(adminUserService.RegisterUser(sysUser) == 1){
                return "ok";
            }else{
                return "注册失败,请重试";
            }
        }
    }

    /**
     * 注册学生
     * @param request
     * @return
     */
    @RequestMapping("/fontRegistStudent")
    @ResponseBody
    public String fontRegistStudent(HttpServletRequest request) throws Exception{
        Student student = new Student();
        String stuNum = request.getParameter("stuNum");
        String userpass = request.getParameter("userpass");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String college = request.getParameter("college");
        String grade = request.getParameter("grade");
        String classNum = request.getParameter("classNum");

        String key = "A1B2C3D4E5F60708";         //加密秘钥
        //两次加密   des加密和md5加密
        userpass = PwdUtil.getPassMD5(userpass);
        userpass = DESUtil.encrypt(userpass,key);

        student.setStuNum(stuNum);
        student.setStuName(stuName);
        student.setStuPass(userpass);
        student.setSex(sex);
        student.setCollege(college);
        student.setGrade(grade);
        student.setClassNum(classNum);

        if(studentService.RegisterStuService(student)){
            return "ok";
        }
        return "error";
    }

    /**
     * 返回注册界面
     * @return
     */
    @RequestMapping("/fontRegisterPage")
    public String fontRegisterPage(){
        return "views/font/fontRegister";
    }

}
