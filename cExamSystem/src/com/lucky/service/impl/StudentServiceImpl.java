package com.lucky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lucky.dao.EUDateGridResult;
import com.lucky.dao.StuMess;
import com.lucky.dao.Student;
import com.lucky.dao.Teacher;
import com.lucky.mapper.StuMessCMapper;
import com.lucky.mapper.StudentMapper;
import com.lucky.service.StudentService;
import com.lucky.utils.DESUtil;
import com.lucky.utils.PwdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Socket;
import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/4/15.
 */
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StuMessCMapper stuMessCMapper;



    /**
     * 查找所有的学生信息
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDateGridResult getStudentsList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page,rows);
        List<Student> list = studentMapper.selectAllStudent();

        //创建一个返回值对象
        EUDateGridResult result = new EUDateGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Student> pageInfo = new PageInfo<Student>(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    /**
     * 判断是否已经注册
     * @param stuNum
     * @return
     */
    @Override
    public boolean judgeStuNumUnique(String stuNum) {
        if(studentMapper.judgeStuNumUnique(stuNum) == 1){
            return false;
        }
        return true;
    }

    /**
     * 学生登录验证
     * @param stuNum
     * @return
     */
    @Override
    public Student fontLoginVali(String stuNum,String pass) throws Exception{

        String dbpass = studentMapper.LoginVali(stuNum);
        String key = "A1B2C3D4E5F60708";         //解密的密钥，要和加密的一样
        //两次加密   des加密和md5加密
        pass = PwdUtil.getPassMD5(pass);
        pass = DESUtil.encrypt(pass,key);
        //密码匹配成功
        if(pass.equals(dbpass)){
            Student student = studentMapper.selStuByNum(stuNum);
            return student;
        }

        return null;
    }

    /**
     * 注册用户
     * @param student
     * @return
     */
    @Override
    public boolean RegisterStuService(Student student) {
        String stuNum = student.getStuNum();
        if(stuMessCMapper.addNewMessC(stuNum) == 1){
            if(studentMapper.RegisterStu(student) ==1){
                return true;
            }
        }
        return false;
    }


    /**
     *
     * @param stuNum
     * @return
     */
    @Override
    public StuMess selChapterNumByNum(String stuNum) {
        StuMess stuMess = stuMessCMapper.selChapterNumByNum(stuNum);
        return stuMess;
    }

    /**
     *
     * @param stuNum
     * @return
     */
    @Override
    public StuMess selAllNumByNum(String stuNum) {
        StuMess stuMess = stuMessCMapper.selAllNumByNum(stuNum);
        return stuMess;
    }
}
