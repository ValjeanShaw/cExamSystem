package com.lucky.controller;

import com.lucky.dao.EUDateGridResult;
import com.lucky.dao.StuMess;
import com.lucky.dao.Student;
import com.lucky.service.StudentService;
import com.lucky.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by 风萧萧兮 on 2017/4/15.
 */

@RequestMapping(value="/Student")
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 查询出所有的学生   admin使用
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/AdminAllStudents")
    @ResponseBody
    public EUDateGridResult selectAllTeacher(Integer page, Integer rows){
        EUDateGridResult result = studentService.getStudentsList(page,rows);
        return result;
    }

    /**
     * 判断是否已经注册
     * @param stuNum
     * @return
     */
    @RequestMapping("/fontJudgeRegisterUnique")
    @ResponseBody
    public String fontJudgeRegisterUnique(String stuNum){
        if(studentService.judgeStuNumUnique(stuNum)){
            return "ok";
        }
        return "error";
    }

    /**
     * 返回个人数据中心界面
     * @return
     */
    @RequestMapping("/fontPersonalData")
    public String fontPersonalData(){
        return "views/font/personalData";
    }

    @RequestMapping("/fontSelChapterNumByNum")
    @ResponseBody
    public StuMess fontSelChapterNumByNum(HttpSession session){

        String stuNum = (String) session.getAttribute("fontUserIdStu");
        StuMess stuMess = studentService.selChapterNumByNum(stuNum);

        return stuMess;
    }

    @RequestMapping("/fontSelAllNumByNum")
    @ResponseBody
    public StuMess fontSelAllNumByNum(HttpSession session){

        String stuNum = (String) session.getAttribute("fontUserIdStu");
        StuMess stuMess = studentService.selAllNumByNum(stuNum);

        return stuMess;
    }
}
