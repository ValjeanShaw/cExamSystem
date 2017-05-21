package com.lucky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lucky.dao.ChooseQuestion;
import com.lucky.dao.EUDateGridResult;
import com.lucky.dao.News;
import com.lucky.mapper.ChooseMapper;
import com.lucky.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/4/29.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
     @Autowired
     ChooseMapper chooseMapper;

    /**
     * 后台分页加载信息
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDateGridResult getChooseQuestionList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page,rows);
        List<ChooseQuestion> list = chooseMapper.selectAllChoose();

        //创建一个返回值对象
        EUDateGridResult result = new EUDateGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<ChooseQuestion> pageInfo = new PageInfo<ChooseQuestion>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }


    /**
     * 后台添加一条信息
     * @param questionText
     * @param questionPic
     * @param chooseA
     * @param chooseB
     * @param chooseC
     * @param chooseD
     * @param answer
     * @param explain
     * @param chapter
     * @return
     */
    @Override
    public boolean addNewChoose(String questionText, String questionPic, String chooseA, String chooseB, String chooseC, String chooseD, String answer, String explain, String chapter) {
        if(chooseMapper.addNewChoose(questionText,questionPic,chooseA,chooseB,chooseC,chooseD,answer,explain,chapter) == 1){
            return true;
        }
        return false;
    }

    /**
     * 修改一条信息
     * @param id
     * @param questionText
     * @param questionPic
     * @param chooseA
     * @param chooseB
     * @param chooseC
     * @param chooseD
     * @param answer
     * @param explain
     * @param chapter
     * @return
     */
    @Override
    public boolean editChoose(String id, String questionText, String questionPic, String chooseA, String chooseB, String chooseC, String chooseD, String answer, String explain, String chapter) {
        if(chooseMapper.editNewChoose(id,questionText,questionPic,chooseA,chooseB,chooseC,chooseD,answer,explain,chapter) == 1){
            return true;
        }
        return false;
    }

    /**
     * 根据id删除一条信息
     * @param id
     * @return
     */
    @Override
    public boolean deleteChoose(String id) {
        if(chooseMapper.deleteChooseById(id) == 1){
            return true;
        }
        return false;
    }

    @Override
    public ChooseQuestion selRandOneChapService(String chapter) {
        return chooseMapper.selRandOneChap(chapter);
    }

    @Override
    public List<ChooseQuestion> selRandNChap_1_Service(int value) {
        return chooseMapper.selRandNChap_1(value);
    }

    @Override
    public ChooseQuestion selRandOneChap_1_Service() {
        return chooseMapper.selRandOneChap_1();
    }

    @Override
    public List<ChooseQuestion> selRandNChap_3_Service(int value) {
        return chooseMapper.selRandNChap_3(value);
    }

    @Override
    public ChooseQuestion selRandOneChap_3_Service() {
        return chooseMapper.selRandOneChap_3();
    }

    @Override
    public List<ChooseQuestion> selRandNChap_6_Service(int value) {
        return chooseMapper.selRandNChap_6(value);
    }

    @Override
    public ChooseQuestion selRandOneChap_6_Service() {
        return chooseMapper.selRandOneChap_6();
    }

    @Override
    public List<ChooseQuestion> selRandNChap_7_Service(int value) {
        return chooseMapper.selRandNChap_7(value);
    }

    @Override
    public ChooseQuestion selRandOneChap_7_Service() {
        return chooseMapper.selRandOneChap_7();
    }

    @Override
    public List<ChooseQuestion> selRandNChap_8_Service(int value) {
        return chooseMapper.selRandNChap_8(value);
    }

    @Override
    public ChooseQuestion selRandOneChap_8_Service() {
        return chooseMapper.selRandOneChap_8();
    }

    @Override
    public List<ChooseQuestion> selRandNChap_9_Service(int value) {
        return chooseMapper.selRandNChap_9(value);
    }

    @Override
    public ChooseQuestion selRandOneChap_9_Service() {
        return chooseMapper.selRandOneChap_9();
    }

    @Override
    public List<ChooseQuestion> selRandNChap_10_Service(int value) {
        return chooseMapper.selRandNChap_10(value);
    }

    @Override
    public ChooseQuestion selRandOneChap_10_Service() {
        return chooseMapper.selRandOneChap_10();
    }

    /**
     * 根据id查询所有试题
     * @param id
     * @return
     */
    @Override
    public List<ChooseQuestion> selQuesById(String id) {
        return chooseMapper.selQuesById(id);
    }
}
