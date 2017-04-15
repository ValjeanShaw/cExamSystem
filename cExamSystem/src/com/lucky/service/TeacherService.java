package com.lucky.service;

import com.lucky.dao.EUDateGridResult;

/**
 * Created by 风萧萧兮 on 2017/4/14.
 * 教师类的service功能
 *
 */
public interface TeacherService {

    //后台查询教师列表的方式
    EUDateGridResult getTeachersList(int page, int rows);

    //添加教师
    boolean addTeacher(String teaName, String sex, String profesRanks, String telphone, String email) throws Exception;

    //后台修改教师信息
    boolean editAdminTeacher(String id, String teaName, String sex, String profesRanks, String telphone, String email) throws Exception;

    //后台修改教师信息
    boolean deleteTeacher(String id) throws Exception;
}