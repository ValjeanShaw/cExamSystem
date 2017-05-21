package com.lucky.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 潇潇 on 2016/12/24.
 */
public class LoginJudge implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        HttpSession session = httpServletRequest.getSession();

        //登录和注册不拦截
        if(uri.contains("Login")||uri.contains("Register")){
            return true;
        }

//        return true;

        // 第一层拦截  只允许通过带font 和 admin的请求，admin登入必须登录
        if(uri.contains("font")||uri.contains("Font")){
            //前台应用判断登录
            if((session.getAttribute("fontUserIdStu")!=null)||(session.getAttribute("fontUserIdTea")!=null)){
                return true;
            }else{
                //用户没有登录跳转到登录页面
                httpServletRequest.getRequestDispatcher("/FontLogin/LoginPage.port").forward(httpServletRequest, httpServletResponse);
                return false;
            }
        }else{
            if(uri.contains("admin")||uri.contains("Admin")){
                if(session.getAttribute("AdminLoginUsername")!=null){
                    return true;
                }else{
                    //用户没有登录挑战到登录页面
                    httpServletRequest.getRequestDispatcher("/AdminManage/AdminLogin.port").forward(httpServletRequest, httpServletResponse);
                    return false;
                }
            }else{
                return false;
            }
        }
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
