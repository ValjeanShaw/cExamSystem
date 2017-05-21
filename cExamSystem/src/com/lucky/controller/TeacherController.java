package com.lucky.controller;

import com.lucky.dao.EUDateGridResult;
import com.lucky.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 风萧萧兮 on 2017/4/14.
 */
@Controller
@RequestMapping(value = "/Teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    /**
     * 查询出所有的教师   admin使用
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/AdminAllTeachers")
    @ResponseBody
    public EUDateGridResult selectAllTeacher(Integer page, Integer rows){
        EUDateGridResult result = teacherService.getTeachersList(page,rows);
        return result;
    }

    /**
     * 后台添加一个教师信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/AdminAddTeacher")
    @ResponseBody
    public String addNewTeacher(HttpServletRequest request){
        boolean flag = false;
        String teaName = (String) request.getParameter("teaName");
        String sex = (String) request.getParameter("sex");
        String profesRanks = (String) request.getParameter("profesRanks");
        String telphone = (String) request.getParameter("telphone");
        String email = (String) request.getParameter("email");
        try{
            flag = teacherService.addTeacher(teaName,sex,profesRanks,telphone,email);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(flag){
            return "ok";
        }else{
            return "error";
        }
    }

    /**
     * 后台修改一个教师信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/AdminEditTeacher")
    @ResponseBody
    public String EditTeacher(HttpServletRequest request){
        boolean flag = false;
        String id = (String) request.getParameter("id");
        String teaName = (String) request.getParameter("teaName");
        String sex = (String) request.getParameter("sex");
        String profesRanks = (String) request.getParameter("profesRanks");
        String telphone = (String) request.getParameter("telphone");
        String email = (String) request.getParameter("email");
        try{
            flag = teacherService.editAdminTeacher(id,teaName,sex,profesRanks,telphone,email);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(flag){
            return "ok";
        }else{
            return "error";
        }
    }

    /**
     * 后台删除一个教师信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/AdminDeleteTeacher")
    @ResponseBody
    public String DeleteTeacher(HttpServletRequest request){
        boolean flag = false;
        String id = (String) request.getParameter("id");
        try{
            flag = teacherService.deleteTeacher(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(flag){
            return "ok";
        }else{
            return "error";
        }
    }
}
