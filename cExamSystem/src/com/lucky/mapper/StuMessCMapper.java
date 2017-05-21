package com.lucky.mapper;

import com.lucky.dao.StuMess;
import com.lucky.dao.TopStudentNum;
import com.lucky.dao.TopStudentNumRight;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/5/16.
 */
public interface StuMessCMapper {
    int plusFinishNumByNum(String stuNum);
    int plusRightNumByNum(String stuNum);
    int plusChapter_1_2ByNum(String stuNum);
    int plusChapter_1_2_RByNum(String stuNum);
    int plusChapter_3_4_5ByNum(String stuNum);
    int plusChapter_3_4_5_RByNum(String stuNum);
    int plusChapter_6ByNum(String stuNum);
    int plusChapter_6_RByNum(String stuNum);
    int plusChapter_7ByNum(String stuNum);
    int plusChapter_7_RByNum(String stuNum);
    int plusChapter_8ByNum(String stuNum);
    int plusChapter_8_RByNum(String stuNum);
    int plusChapter_9ByNum(String stuNum);
    int plusChapter_9_RByNum(String stuNum);
    int plusChapter_10ByNum(String stuNum);
    int plusChapter_10_RByNum(String stuNum);

    int addNewMessC(String stuNum);

    StuMess selChapterNumByNum(String stuNum);

    StuMess selAllNumByNum(String stuNum);

    List<TopStudentNum> topAllStudentNum();

    List<TopStudentNumRight> topRightStudentNum();

}
