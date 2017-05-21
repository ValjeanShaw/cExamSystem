package com.lucky.mapper;

import com.lucky.dao.Exampaper;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/5/14.
 */
public interface ExampaperMapper {

    //创建试卷
    int createNewPaper(String id,String title,String createMan);
    //插入试题与试卷关系
    int inQuesToPaper(String exampaperId,String questionId);
    //加载部分试卷
    List<Exampaper> fontLoadpapers(int start,int num);
    //根据id删除试卷
    int delPaperById(String id);
    //根据id查询出试卷名字
    String selExamTitleById(String paperId);
}
