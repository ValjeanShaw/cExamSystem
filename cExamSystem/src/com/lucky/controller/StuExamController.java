package com.lucky.controller;

import com.lucky.dao.ChooseQuestion;
import com.lucky.service.ExampaperService;
import com.lucky.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/5/15.
 */
@RequestMapping("/StuExam")
@Controller
public class StuExamController {

    @Autowired
    QuestionService questionService;
    @Autowired
    ExampaperService exampaperService;
    /**
     * 试卷列表页面
     * @return
     */
    @RequestMapping("/fontStuPaperPage")
    public String fontStuPaperPage(){
        return "views/font/stuExampaperSpace";
    }

    /**
     * 具体做题页面
     * @return
     */
    @RequestMapping("/fontStuExamPage")
    public String fontStuExamPage(String paperId,HttpSession session){
        //获取题目存入session
        List<ChooseQuestion> list = questionService.selQuesById(paperId);
        session.removeAttribute("TheQuestionOfTestPaper");
        session.setAttribute("TheQuestionOfTestPaper",list);
        String paperTitle = exampaperService.selExamTitleById(paperId);
        session.setAttribute("ExampaperTitle",paperTitle);
        return "/views/font/stuExam";
    }
}
