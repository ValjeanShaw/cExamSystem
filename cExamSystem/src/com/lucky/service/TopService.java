package com.lucky.service;

import com.lucky.dao.TopChapterNum;
import com.lucky.dao.TopChapterNumRight;
import com.lucky.dao.TopStudentNum;
import com.lucky.dao.TopStudentNumRight;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/5/21.
 */
public interface TopService {

    //每章节做题总数
    List<TopChapterNum> topAllChapterNumService();

    //每章节正确率
    List<TopChapterNumRight> topRightChapterNumService();

    //学生榜
    List<TopStudentNum> topAllStudentNumService();

    List<TopStudentNumRight> topRightStudentNumService();

}
