package com.lucky.controller;

import com.lucky.dao.ChooseQuestion;
import com.lucky.dao.EUDateGridResult;
import com.lucky.service.ExampaperService;
import com.lucky.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/4/29.
 */
@RequestMapping(value="/Question")
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExampaperService exampaperService;

    /**
     * 查询所有的新闻列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/AdminAllChooseList")
    @ResponseBody
    public EUDateGridResult selectAllNewsList(Integer page, Integer rows){
        return questionService.getChooseQuestionList(page,rows);
    }

    /**
     * 添加一个新闻
     * @param request
     * @return
     */
    @RequestMapping("/AdminAddNewChoose")
    @ResponseBody
    public String addNewChoose(HttpServletRequest request){
        String questionText = request.getParameter("questionText");
        String questionPic = request.getParameter("questionPic");
        String chooseA = request.getParameter("chooseA");
        String chooseB = request.getParameter("chooseB");
        String chooseC = request.getParameter("chooseC");
        String chooseD = request.getParameter("chooseD");
        String answer = request.getParameter("answer");
        String qExplain = request.getParameter("qExplain");
        String chapter = request.getParameter("chapter");

        if(questionService.addNewChoose(questionText,questionPic,chooseA,chooseB,chooseC,chooseD,answer,qExplain,chapter)){
            return "ok";
        }else{
            return "error";
        }
    }

    /**
     * 修改一条信息
     * @param request
     * @return
     */
    @RequestMapping("/AdminEditChoose")
    @ResponseBody
    public String editChoose(HttpServletRequest request){
        String id = request.getParameter("id");
        String questionText = request.getParameter("questionText");
        String questionPic = request.getParameter("questionPic");
        String chooseA = request.getParameter("chooseA");
        String chooseB = request.getParameter("chooseB");
        String chooseC = request.getParameter("chooseC");
        String chooseD = request.getParameter("chooseD");
        String answer = request.getParameter("answer");
        String qExplain = request.getParameter("qExplain");
        String chapter = request.getParameter("chapter");

        if(questionService.editChoose(id,questionText,questionPic,chooseA,chooseB,chooseC,chooseD,answer,qExplain,chapter)){
            return "ok";
        }else{
            return "error";
        }
    }

    /**
     * 删除一条信息
     * @param id
     * @return
     */
    @RequestMapping("/AdminDeleteChoose")
    @ResponseBody
    public String deleteChoose(String id){
        if(questionService.deleteChoose(id)){
            return "ok";
        }else{
            return "error";
        }
    }

    /**
     * 前端展现题库页面
     * @return
     */
    @RequestMapping("/fontQuestionBase")
    public String fontQuestionBase(){
        return "views/font/questionBase";
    }

    /*
    查询所有的选择题
     */
    @RequestMapping("/fontCreateTestPaperChoose")
    @ResponseBody
    public String createTestPaperChoose(HttpServletRequest request,HttpSession session){
        Integer chapter_1_2 = Integer.parseInt(request.getParameter("chapter_1_2"));
        Integer chapter_3_4_5 = Integer.parseInt(request.getParameter("chapter_3_4_5"));
        Integer chapter_6 = Integer.parseInt(request.getParameter("chapter_6"));
        Integer chapter_7 = Integer.parseInt(request.getParameter("chapter_7"));
        Integer chapter_8 = Integer.parseInt(request.getParameter("chapter_8"));
        Integer chapter_9 = Integer.parseInt(request.getParameter("chapter_9"));
        Integer chapter_10 = Integer.parseInt(request.getParameter("chapter_10"));
        //定义总的集合
        List<ChooseQuestion> list = new ArrayList<ChooseQuestion>();
        //加入各类子集合
        List<ChooseQuestion> _list = questionService.selRandNChap_1_Service(chapter_1_2);
        if(_list != null&& _list.size()>0){
            list.addAll(_list);
        }
        _list = questionService.selRandNChap_3_Service(chapter_3_4_5);
        if(_list != null&& _list.size()>0){
            list.addAll(_list);
        }
        _list = questionService.selRandNChap_6_Service(chapter_6);
        if(_list != null&& _list.size()>0){
            list.addAll(_list);
        }
        _list = questionService.selRandNChap_7_Service(chapter_7);
        if(_list != null&& _list.size()>0){
            list.addAll(_list);
        }
        _list = questionService.selRandNChap_8_Service(chapter_8);
        if(_list != null&& _list.size()>0){
            list.addAll(_list);
        }
        _list = questionService.selRandNChap_9_Service(chapter_9);
        if(_list != null&& _list.size()>0){
            list.addAll(_list);
        }
        _list = questionService.selRandNChap_10_Service(chapter_10);
        if(_list != null&& _list.size()>0){
            list.addAll(_list);
        }

        session.setAttribute("TheQuestionOfTestPaper",list);

        return "ok";
    }

    /**
     * 更换题目
     * @param chapter
     * @return
     */
    @RequestMapping("/fontChangePaperChoose")
    @ResponseBody
    public ChooseQuestion selRandOneChapService(String chapter){
        return questionService.selRandOneChapService(chapter);
    }

    /**
     * 试卷细节区域页面ById
     * @return
     */
    @RequestMapping("/fontTestpaperDetailById")
    public String fontTestpaperDetailById(String paperId,HttpSession session){
        //获取题目存入session
        List<ChooseQuestion> list = questionService.selQuesById(paperId);
        session.removeAttribute("TheQuestionOfTestPaper");
        session.setAttribute("TheQuestionOfTestPaper",list);
        String paperTitle = exampaperService.selExamTitleById(paperId);
        session.setAttribute("ExampaperTitle",paperTitle);
        return "views/font/testpaperDetailById";
    }


}
