package com.lucky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lucky.dao.EUDateGridResult;
import com.lucky.dao.Teacher;
import com.lucky.mapper.TeacherMapper;
import com.lucky.service.TeacherService;
import com.lucky.utils.DESUtil;
import com.lucky.utils.PwdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/4/14.
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * easyui的传参数分页查询item列表
     *
     * Easyui中datagrid控件要求的数据格式为：
     *{total:”2”,rows:[{“id”:”1”,”name”,”张三”},{“id”:”2”,”name”,”李四”}]}
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDateGridResult getTeachersList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page,rows);
        List<Teacher> list = teacherMapper.selectAllTeacher();

        //创建一个返回值对象
        EUDateGridResult result = new EUDateGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    /**
     *添加一个教师
     * @param teaName
     * @param sex
     * @param profesRanks
     * @param telphone
     * @param email
     * @return
     * @throws Exception
     */
    @Override
    public boolean addTeacher(String teaName, String sex, String profesRanks, String telphone, String email) throws Exception {
        //密码初始化为123456 加密
        String key = "A1B2C3D4E5F60708";         //解密的密钥，要和加密的一样
        //两次加密   des加密和md5加密
        String teaPass = PwdUtil.getPassMD5("123456");
        teaPass = DESUtil.encrypt(teaPass,key);

        Teacher teacher = new Teacher();
        teacher.setTeaName(teaName);
        teacher.setTeaPass(teaPass);
        teacher.setSex(sex);
        teacher.setProfesRanks(profesRanks);
        teacher.setTelphone(telphone);
        teacher.setEmail(email);
        //执行mybatis的方法
        if(teacherMapper.addTeacher(teacher) == 1){
            return true;
        }
        return false;
    }

    /**
     * 修改指定老师的信息
     * @param id
     * @param teaName
     * @param sex
     * @param profesRanks
     * @param telphone
     * @param email
     * @return
     * @throws Exception
     */
    @Override
    public boolean editAdminTeacher(String id, String teaName, String sex, String profesRanks, String telphone, String email) throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setTeaName(teaName);
        teacher.setSex(sex);
        teacher.setProfesRanks(profesRanks);
        teacher.setTelphone(telphone);
        teacher.setEmail(email);
        //执行mybatis的方法
        if(teacherMapper.editAdminTeacher(teacher) == 1){
            return true;
        }
        return false;
    }

    /**
     * 删除一条教师信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteTeacher(String id) throws Exception {
        if(teacherMapper.deleteTeacher(id) == 1){
            return true;
        }
        return false;
    }
}
