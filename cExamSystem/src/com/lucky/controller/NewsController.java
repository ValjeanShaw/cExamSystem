package com.lucky.controller;

import com.lucky.dao.EUDateGridResult;
import com.lucky.dao.FontResult;
import com.lucky.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 风萧萧兮 on 2017/4/22.
 */
@Controller
@RequestMapping("/News")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 查询所有的新闻列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/AdminAllNews")
    @ResponseBody
    public EUDateGridResult selectAllNewsList(Integer page, Integer rows,HttpSession session){
        return newsService.getNewsList(page,rows);
    }

    /**
     * 添加一条记录
     * @param request
     * @return
     */
    @RequestMapping("/AdminAddNews")
    @ResponseBody
    public String addNewsAdmin(HttpServletRequest request){

        String newsTitle = request.getParameter("newsTitle");
        String newsText = request.getParameter("newsText");
        String createMan = request.getParameter("createMan");

        if(newsService.addNews(newsTitle,newsText,createMan)){
            return "ok";
        }else{
            return "error";
        }
    }

    /**
     * 修改消息
     * @param request
     * @return
     */
    @RequestMapping("/AdminEditNews")
    @ResponseBody
    public String editNewsAdmin(HttpServletRequest request){
        String id = request.getParameter("id");
        String newsTitle = request.getParameter("newsTitle");
        String newsText = request.getParameter("newsText");
        String createMan = request.getParameter("createMan");

        if(newsService.editNews(id,newsTitle,newsText,createMan)){
            return "ok";
        }else{
            return "error";
        }
    }


    /**
     * 根据id删除
     * @param request
     * @return
     */
    @RequestMapping("/AdminDeleteNews")
    @ResponseBody
    public String deleteNewsAdmin(HttpServletRequest request){
        if(newsService.deleteNews(request.getParameter("id"))){
            return "ok";
        }else{
            return "error";
        }
    }

    /**
     *
     * @return
     */
    @RequestMapping("/FontLoadNews")
    @ResponseBody
    public FontResult fontLoadNews(String num){
        return newsService.fontLoadNews(Integer.parseInt(num));
    }
}
