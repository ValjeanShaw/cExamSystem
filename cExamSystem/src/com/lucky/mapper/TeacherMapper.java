package com.lucky.mapper;

import com.lucky.dao.Teacher;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/4/14.
 */
public interface TeacherMapper {

    List<Teacher> selectAllTeacher();
    //添加教师
    int addTeacher(Teacher teacher);
    //后台修改教师信息
    int editAdminTeacher(Teacher teacher);
    //后台删除教师信息
    int deleteTeacher(String id);
    //前台验证教师登录
    Teacher fontValiTea(String id);

}
