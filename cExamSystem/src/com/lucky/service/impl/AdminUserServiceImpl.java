package com.lucky.service.impl;

import com.lucky.dao.ActiveUser;
import com.lucky.dao.CommonDao;
import com.lucky.dao.Permission;
import com.lucky.dao.SysUser;
import com.lucky.mapper.PermissionMapper;
import com.lucky.mapper.SysUserMapper;
import com.lucky.service.AdminUserService;
import com.lucky.utils.DESUtil;
import com.lucky.utils.PwdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/3/21.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    //自动注入mapper进行操作
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 注册系统用户
     * @param sysUser
     * @return
     */
    @Override
    public int RegisterUser(SysUser sysUser) {
        sysUserMapper.RegisterSysUser(sysUser);
        return 0;
    }

    /**
     * 注册系统用户之前判断系统用户是否有这个用户名
     * @param username
     * @return
     */
    public boolean PreRegisterAdminUser(String username){
        int num = sysUserMapper.SelectSysUser(username);
        if(num>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 登录验证
     * @param username
     * @return
     */
    @Override
    public ActiveUser LoginValiService(String username, String password)  throws SQLException {
        ActiveUser activeUser = null;   //返回的sysuser,在controller中放入session中
        SysUser sysUser = sysUserMapper.LoginVali(username);
        try{
            String key = "A1B2C3D4E5F60708";         //解密的密钥，要和加密的一样
            //两次加密   des加密和md5加密
            password = PwdUtil.getPassMD5(password);
            password = DESUtil.encrypt(password,key);
            String dbpassword = sysUser.getSysUserPass();
            if(dbpassword.equals(password)){
                //密码匹配
                activeUser = new ActiveUser();       //确定用到的时候再定义，提高性能，防止内存浪费
                activeUser.setSysuserId(sysUser.getId());
                activeUser.setUsername(username);
                List<Permission> listMenu = findMenuByUserId(sysUser.getId());
                activeUser.setMenus(listMenu);
                //构造json字符串   两次循环
                Iterator<Permission> iteratorMenu = listMenu.iterator();
                //创建树形菜单
                //使用StringBuffer比较高效
                StringBuffer string = new StringBuffer("");
                while(iteratorMenu.hasNext()){
                    Permission permission = iteratorMenu.next();
                    string.append("<li><span>"+permission.getPermissionName()+"</span><ul>");     //拼串
                    //创建模型查询其子类权限
                    CommonDao commonDao = new CommonDao();
                    commonDao.setId(sysUser.getId());
                    commonDao.setParentId(permission.getId());
                    //查找子类
                    List<Permission> listPermission = findPermissionByUser(commonDao);
                    activeUser.setPermissions(listPermission);
                    Iterator<Permission> iteratorPermission = listPermission.iterator();
                    while(iteratorPermission.hasNext()){
                        Permission childrenPermission = iteratorPermission.next();
                        string.append("<li data-options=\"attributes:{'url':'"+childrenPermission.getUrl()+"'}\">"+childrenPermission.getPermissionName()+"</li>");
                    }
                    string.append("</ul></li>");
                }
                activeUser.setMenuJson(string.toString());
                return activeUser;
            }else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 系统用户登录之后查询拥有的菜单
     * @param sysuserId
     * @return
     * @throws Exception
     */
    @Override
    public List<Permission> findMenuByUserId(String sysuserId) throws Exception {
        return permissionMapper.selectUserMenu(sysuserId);
    }

    /**
     * 系统用户登录之后查询他拥有的权限
     * @param commonDao
     * @return
     * @throws Exception
     */
    @Override
    public List<Permission> findPermissionByUser(CommonDao commonDao) throws Exception {
        return permissionMapper.selectUserPermission(commonDao);
    }

    /**
     * 修改系统用户的密码
     * @param sysUser
     * @return
     * @throws Exception
     */
    @Override
    public boolean EditSysUserPass(SysUser sysUser) throws Exception {
        if(sysUserMapper.EditSysPass(sysUser) == 1){
            return true;
        }
        return false;
    }
}
