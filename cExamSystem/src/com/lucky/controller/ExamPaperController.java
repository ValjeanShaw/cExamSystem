package com.lucky.controller;

import com.lucky.dao.ChooseQuestion;
import com.lucky.dao.Exampaper;
import com.lucky.dao.JdugeResult;
import com.lucky.service.ExampaperService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/5/8.
 */

@Controller
@RequestMapping("/TestPaper")
public class ExamPaperController {

    @Autowired
    ExampaperService exampaperService;
    /**
     * 试卷区域页面
     * @return
     */
    @RequestMapping("/fontTestpaperSpace")
    public String fontTestPaper(){
        return "views/font/testpaperSpace";
    }


    /**
     * 试卷细节区域页面
     * @return
     */
    @RequestMapping("/fontTestpaperDetail")
    public String fontTestpaperDetail(){
        return "views/font/testpaperDetail";
    }

    /**
     * 获得session中的题目
     * @param session
     * @return
     */
    @RequestMapping("/fontGetPaperQuestion")
    @ResponseBody
    public List<ChooseQuestion> getPaperQuestion(HttpSession session){
        List<ChooseQuestion> list = (List<ChooseQuestion>) session.getAttribute("TheQuestionOfTestPaper");
        return list;
    }

    /**
     * 创建试卷
     * @param jsonStr
     * @param title
     * @param session
     * @return
     */
    @RequestMapping("/fontCreateQuesIntoPaper")
    @ResponseBody
    public String CreateQuesIntoPaper(String jsonStr,String title,HttpSession session){
        String createMan =(String) session.getAttribute("fontUserName");
        JSONObject object = new JSONObject(jsonStr);
        JSONArray array = object.getJSONArray("data");
        List<String> list = new ArrayList<String>();
        for(int i=0;i<array.length();i++){
            list.add((String) array.get(i));
        }
        if(exampaperService.CreatePaperService(list,title,createMan)){
            return "ok";
        }
        return "error";
    }

    /**
     * 返回试卷列表
     * @param index
     * @return
     */
    @RequestMapping("/fontGetExamPaper")
    @ResponseBody
    public List<Exampaper> getExamPaper(String index){
        int _index = Integer.parseInt(index);
        return exampaperService.fontLoadpapers(_index);
    }

    /**
     * 根据id删除试卷
     *
     */
    @RequestMapping("/fontDelExamPaperById")
    @ResponseBody
    public String fontDelExamPaperById(String paperId){
        if(exampaperService.fontDelPaperById(paperId)){
            return "ok";
        }
        return "error";
    }

    /**
     * 根据id查询试卷名字
     * @param paperId
     * @return
     */
    @RequestMapping("/fontSelExamTitleById")
    @ResponseBody
    public String fontSelExamTitleById(String paperId,HttpSession session){
        String paperTitle = exampaperService.selExamTitleById(paperId);
        session.setAttribute("Exampaper",paperTitle);
        return "ok";
    }
    /**
     * 查询试卷名字
     *
     * @return
     */
    @RequestMapping("/fontGetExamTitle")
    @ResponseBody
    public String fontSelExamTitleById(HttpSession session){
        String title = (String) session.getAttribute("ExampaperTitle");
        return title;
    }

    /**
     * 接收答案判卷
     */
    @RequestMapping("/fontJudgePaper")
    @ResponseBody
    public String fontJudgePaper(String arr,HttpSession session){
        //解析json字符串d
        String createMan =(String) session.getAttribute("fontUserName");
        JSONObject object = new JSONObject(arr);
        JSONArray array = object.getJSONArray("data");
        List<String> userlist = new ArrayList<String>();
        for(int i=0;i<array.length();i++){
            userlist.add((String) array.get(i));
        }
        //取出session中存的原题目
        List<ChooseQuestion> resourseList =(List<ChooseQuestion>) session.getAttribute("TheQuestionOfTestPaper");
        //取出用户stunum
        String stuNum = (String) session.getAttribute("fontUserIdStu");
        //拿到service进行判断
        JdugeResult result = exampaperService.fontJudgePaperService(userlist,resourseList,stuNum);
        if(result == null){
            return "error";
        }
        session.setAttribute("JdugeResult",result);
        return "ok";
    }

    /**
     * 返回判卷结果页面
     * @return
     */
    @RequestMapping("/fontExamResultPage")
    public String fontExamResultPage(){
        return "views/font/stuExamResult";
    }
    /**
     * 返回做题结果
     * @param session
     * @return
     */
    @RequestMapping("/fontGetJdugeResult")
    @ResponseBody
    public JdugeResult fontGetJdugeResult(HttpSession session){
        return (JdugeResult) session.getAttribute("JdugeResult");
    }

}
