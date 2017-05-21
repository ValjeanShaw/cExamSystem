package com.lucky.mapper;

import com.lucky.dao.ChooseQuestion;
import com.lucky.dao.TopChapterNum;
import com.lucky.dao.TopChapterNumRight;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/4/29.
 */
public interface ChooseMapper {
    //查询所有的选择题
    List<ChooseQuestion> selectAllChoose();
    //添加一条信息
    int addNewChoose(String questionText,String questionPic,String chooseA,String chooseB,
                     String chooseC,String chooseD,String answer,String explain,String chapter);
    //修改一条信息
    int editNewChoose(String id,String questionText,String questionPic,String chooseA,String chooseB,
                      String chooseC,String chooseD,String answer,String explain,String chapter);
    //删除一条信息
    int deleteChooseById(String id);

    //随机查询一道题
    ChooseQuestion selRandOneChap(String value);

    //随机查询多个题 和一个题  章节
    List<ChooseQuestion> selRandNChap_1(Integer value);
    ChooseQuestion selRandOneChap_1();
    //随机查询多个题 和一个题 章节
    List<ChooseQuestion> selRandNChap_3(int value);
    ChooseQuestion selRandOneChap_3();
    //随机查询多个题 和一个题  章节
    List<ChooseQuestion> selRandNChap_6(int value);
    ChooseQuestion selRandOneChap_6();
    //随机查询多个题 和一个题  章节
    List<ChooseQuestion> selRandNChap_7(int value);
    ChooseQuestion selRandOneChap_7();
    //随机查询多个题 和一个题  章节
    List<ChooseQuestion> selRandNChap_8(int value);
    ChooseQuestion selRandOneChap_8();
    //随机查询多个题 和一个题  章节
    List<ChooseQuestion> selRandNChap_9(int value);
    ChooseQuestion selRandOneChap_9();
    //随机查询多个题 和一个题  章节
    List<ChooseQuestion> selRandNChap_10(int value);
    ChooseQuestion selRandOneChap_10();

    //根据id查询出试卷
    List<ChooseQuestion> selQuesById(String exampaperId);

    //根据id将完成数+1
    int plusFinishNumById(String id);
    //根据id将做对数量
    int plusRightNumById(String id);
    //根据id将点赞次数+1
    int plusZanNumById(String id);

    List<TopChapterNum> topAllChapterNum();

    List<TopChapterNumRight> topRightChapterNum();

}
