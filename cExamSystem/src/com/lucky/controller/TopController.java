package com.lucky.controller;

import com.lucky.dao.TopChapterNum;
import com.lucky.dao.TopChapterNumRight;
import com.lucky.dao.TopStudentNum;
import com.lucky.dao.TopStudentNumRight;
import com.lucky.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/5/21.
 */
@RequestMapping("/Top")
@Controller
public class TopController {
    @Autowired
    TopService topService;

    @RequestMapping("/fontTopPage")
    public String fontTopPage(){
        return "views/font/topList";
    }

    @RequestMapping("/fontStuTopPage")
    public String fontStuTopPage(){
        return "views/font/stuTopList";
    }
    /**
     * 题目总榜
     * @return
     */
    @RequestMapping("/fontTopAllChapterNum")
    @ResponseBody
    public List<TopChapterNum> fontTopAllChapterNum(){
        return topService.topAllChapterNumService();
    }

    /**
     * 题目正确率榜
     * @return
     */
    @RequestMapping("/fontTopRightChapterNum")
    @ResponseBody
    public List<TopChapterNumRight> fontTopRightChapterNum(){
        return topService.topRightChapterNumService();
    }
    /**
     * 学生做题总榜
     * @return
     */
    @RequestMapping("/fontTopAllStudentNum")
    @ResponseBody
    public List<TopStudentNum> fontTopAllStudentNum(){
        return topService.topAllStudentNumService();
    }
    /**
     * 题目总榜
     * @return
     */
    @RequestMapping("/fontTopRightStudentNum")
    @ResponseBody
    public List<TopStudentNumRight> fontTopRightStudentNum(){
        return topService.topRightStudentNumService();
    }


}
