package com.lucky.controller;

import com.lucky.dao.Permission;
import com.lucky.dao.SysUser;
import com.lucky.service.AdminUserService;
import com.lucky.utils.DESUtil;
import com.lucky.utils.PwdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 风萧萧兮 on 2016/12/4.
 */

@Controller
@RequestMapping(value="/AdminMenu")
public class AdminMenuController {
    @Autowired
    AdminUserService adminUserService;

    /**
     * 后台登录首页
     * @return
     */
    @RequestMapping(value="/index")
    public String index(){
        return "views/admin/index";
    }

    /**
     * tab的跳转页
     *
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String TabPage(@PathVariable String page){
        return "views/admin/"+page;
    }


    /**
     * 获取权限
     * @param session
     * @return
     */
    @RequestMapping(value="/GetPermission")
    @ResponseBody
    public List<Permission> getPermission(HttpSession session){
        List<Permission> permissions = (List<Permission>) session.getAttribute("AdminPermission");
        return permissions;
    }

    /**
     * 在后台打包成树形菜单，传递给前台创建
     * @return
     */
    @RequestMapping("/GetMenuJson")
    @ResponseBody
    public String MenuJson(HttpSession session){
        return (String) session.getAttribute("AdminJsonMenu");
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/ExitLogin")
    @ResponseBody
    public String ExitLogin(HttpSession session){
        //销毁session中的记录
        session.removeAttribute("AdminLoginId");
        session.removeAttribute("AdminLoginUsername");
        session.removeAttribute("AdminMenu");
        session.removeAttribute("AdminPermission");
        session.removeAttribute("AdminJsonMenu");

        return "ok";
    }

    /**
     * 修改系统密码
     * @param pass
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/EditSysPass")
    @ResponseBody
    public String EditSysUserPass(String pass,HttpSession session) throws Exception{
        //拿出session中的user
        String user =(String) session.getAttribute("AdminLoginUsername");
        SysUser sysUser = new SysUser();
        sysUser.setSysUserName(user);
        //密码加密
        String key = "A1B2C3D4E5F60708";         //解密的密钥，要和加密的一样
        //两次加密   des加密和md5加密
        pass = PwdUtil.getPassMD5(pass);
        pass = DESUtil.encrypt(pass,key);
        sysUser.setSysUserPass(pass);

        //调用service算法
        if(adminUserService.EditSysUserPass(sysUser)){
            return "ok";
        }
        return "error";
    }

}
