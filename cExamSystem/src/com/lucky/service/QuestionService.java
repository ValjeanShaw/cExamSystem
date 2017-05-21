package com.lucky.service;

import com.lucky.dao.ChooseQuestion;
import com.lucky.dao.EUDateGridResult;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/4/29.
 */
public interface QuestionService {
    //获得选择题列表
    EUDateGridResult getChooseQuestionList(int page, int rows);

    //添加一个选择题
    boolean addNewChoose(String questionText,String questionPic,String chooseA,String chooseB,
                         String chooseC,String chooseD,String answer,String explain,String chapter);

    //修改一个选择题
    boolean editChoose(String id,String questionText,String questionPic,String chooseA,String chooseB,
                       String chooseC,String chooseD,String answer,String explain,String chapter);

    //删除一个选择题
    boolean deleteChoose(String id);

    ChooseQuestion selRandOneChapService(String chapter);

    //随机查询多个题 和一个题  章节
    List<ChooseQuestion> selRandNChap_1_Service(int value);
    ChooseQuestion selRandOneChap_1_Service();
    //随机查询多个题 和一个题 章节
    List<ChooseQuestion> selRandNChap_3_Service(int value);
    ChooseQuestion selRandOneChap_3_Service();
    //随机查询多个题 和一个题  章节
    List<ChooseQuestion> selRandNChap_6_Service(int value);
    ChooseQuestion selRandOneChap_6_Service();
    //随机查询多个题 和一个题  章节
    List<ChooseQuestion> selRandNChap_7_Service(int value);
    ChooseQuestion selRandOneChap_7_Service();
    //随机查询多个题 和一个题  章节
    List<ChooseQuestion> selRandNChap_8_Service(int value);
    ChooseQuestion selRandOneChap_8_Service();
    //随机查询多个题 和一个题  章节
    List<ChooseQuestion> selRandNChap_9_Service(int value);
    ChooseQuestion selRandOneChap_9_Service();
    //随机查询多个题 和一个题  章节
    List<ChooseQuestion> selRandNChap_10_Service(int value);
    ChooseQuestion selRandOneChap_10_Service();

    //根据id查询题目
    List<ChooseQuestion> selQuesById(String id);
}
