package com.lucky.service;

import com.lucky.dao.ChooseQuestion;
import com.lucky.dao.Exampaper;
import com.lucky.dao.JdugeResult;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/5/14.
 */
public interface ExampaperService {
    //创建试卷
    boolean CreatePaperService(List<String> list,String title,String createMan);
    //查询试卷
    List<Exampaper> fontLoadpapers(int index);
    //根据id删除试卷
    boolean fontDelPaperById(String id);
    //根据id查询试卷 名称
    String selExamTitleById(String paperId);
    //判题
    JdugeResult fontJudgePaperService(List<String> userlist, List<ChooseQuestion> resourseList, String stuNum);

}
