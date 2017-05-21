package com.lucky.service;

import com.lucky.dao.EUDateGridResult;
import com.lucky.dao.StuMess;
import com.lucky.dao.Student;

/**
 * Created by 风萧萧兮 on 2017/4/15.
 */
public interface StudentService {
    //后台查询列表的方式
    EUDateGridResult getStudentsList(int page, int rows);
    //学生前台登录验证
    Student fontLoginVali(String stuNum, String pass) throws Exception;
    //判断是否已经注册
    boolean judgeStuNumUnique(String stuNum);
    //注册用户
    boolean RegisterStuService(Student student);
    //查询每章节学生做题数量
    StuMess selChapterNumByNum(String stuNum);
    //查询全部数据
    StuMess selAllNumByNum(String stuNum);

}
