package com.lucky.mapper;

import com.lucky.dao.Student;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/4/15.
 */
public interface StudentMapper {
    //查找所有的
    List<Student> selectAllStudent();

    //学生登录验证
    String LoginVali(String stuNum);

    //根据id查询学生
    Student selStuByNum(String stuNum);

    //查询是否已经注册
    int judgeStuNumUnique(String stuNum);

    int RegisterStu(Student student);

}
