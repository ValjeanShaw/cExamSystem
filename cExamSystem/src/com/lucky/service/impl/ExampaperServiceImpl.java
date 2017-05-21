package com.lucky.service.impl;

import com.lucky.dao.ChooseQuestion;
import com.lucky.dao.Exampaper;
import com.lucky.dao.JdugeResult;
import com.lucky.mapper.ChooseMapper;
import com.lucky.mapper.ExampaperMapper;
import com.lucky.mapper.StuMessCMapper;
import com.lucky.service.ExampaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by 风萧萧兮 on 2017/5/14.
 */
@Service
public class ExampaperServiceImpl implements ExampaperService {


    @Autowired
    ExampaperMapper exampaperMapper;
    @Autowired
    ChooseMapper chooseMapper;
    @Autowired
    StuMessCMapper stuMessCMapper;


    @Override
    public boolean CreatePaperService(List<String> list, String title,String createMan) {
        String paperId = UUID.randomUUID().toString();  //生成随机的uuid
        //插入多个
        for(String quesId:list){
            if(exampaperMapper.inQuesToPaper(paperId,quesId) != 1){
                return false;
            }
        }
        //插入试题
        if(exampaperMapper.createNewPaper(paperId,title,createMan) != 1){
            return false;
        }
        return true;
    }

    /**
     * 查询部分试卷
     * @param index
     * @return
     */
    @Override
    public List<Exampaper> fontLoadpapers(int index) {
        return exampaperMapper.fontLoadpapers(index,6);
    }

    /**
     * 根据id删除试卷
     * @param id
     * @return
     */
    @Override
    public boolean fontDelPaperById(String id) {
        if(exampaperMapper.delPaperById(id) == 1){
            return true;
        }
        return false;
    }

    /**
     * 判卷
     * @param userlist
     * @param resourseList
     * @param stuNum
     * @return
     */
    @Override
    public JdugeResult fontJudgePaperService(List<String> userlist, List<ChooseQuestion> resourseList, String stuNum) {
        //
        JdugeResult result = new JdugeResult();
        int num = 0;
        for(int i=0;i<userlist.size();i++){
            String userAnswer = userlist.get(i);
            ChooseQuestion resChoose = resourseList.get(i);
            //比较
            boolean flag = resChoose.getAnswer().equals(userAnswer);
            if(flag){
                num++;
            }
            String chapter = resChoose.getChapter();
            //两大操作
            if(!QuestionJudgeOperate(resChoose,flag)){
                return null;
            }
            if(!StudentJdugeOperate(stuNum,flag,chapter)){
                return null;
            }

        }
        result.setRightNum(num);
        result.setList(userlist);
        return result;
    }

    /**
     * 试卷判断后操作
     * @return
     */
    private boolean QuestionJudgeOperate(ChooseQuestion chooseQuestion,boolean flag){
        String id = chooseQuestion.getId();
        //做题总数+1
        if(chooseMapper.plusFinishNumById(id) == 1){
            //是否做对，做对+1
            if(flag){
                chooseMapper.plusRightNumById(id);
            }
            return true;
        }
        return false;
    }

    /**
     *学生信息的判断操作，将具体做题情况加入
     * @return
     */
    private boolean StudentJdugeOperate(String stuNum,boolean flag,String chapter){
        if(stuMessCMapper.plusFinishNumByNum(stuNum) == 1){
            if(flag){
                stuMessCMapper.plusRightNumByNum(stuNum);
            }
            //分条件判断
            switch(chapter){
                case "编程基础":{
                    stuMessCMapper.plusChapter_1_2ByNum(stuNum);
                    if(flag){
                        stuMessCMapper.plusChapter_1_2_RByNum(stuNum);
                    }
                    break;
                }
                case "逻辑编程":{
                    stuMessCMapper.plusChapter_3_4_5ByNum(stuNum);
                    if(flag){
                        stuMessCMapper.plusChapter_3_4_5_RByNum(stuNum);
                    }
                    break;
                }
                case "数组":{
                    stuMessCMapper.plusChapter_6ByNum(stuNum);
                    if(flag){
                        stuMessCMapper.plusChapter_6_RByNum(stuNum);
                    }
                    break;
                }
                case "函数":{
                    stuMessCMapper.plusChapter_7ByNum(stuNum);
                    if(flag){
                        stuMessCMapper.plusChapter_7_RByNum(stuNum);
                    }
                    break;
                }
                case "指针":{
                    stuMessCMapper.plusChapter_8ByNum(stuNum);
                    if(flag){
                        stuMessCMapper.plusChapter_8_RByNum(stuNum);
                    }
                    break;
                }
                case "结构体":{
                    stuMessCMapper.plusChapter_9ByNum(stuNum);
                    if(flag){
                        stuMessCMapper.plusChapter_9_RByNum(stuNum);
                    }
                    break;
                }
                case "文件":{
                    stuMessCMapper.plusChapter_10ByNum(stuNum);
                    if(flag){
                        stuMessCMapper.plusChapter_10_RByNum(stuNum);
                    }
                    break;
                }
            }
            return true;
        }
        return false;
    }


    /**
     * 返回试卷名称
     * @param paperId
     * @return
     */
    @Override
    public String selExamTitleById(String paperId) {
        return exampaperMapper.selExamTitleById(paperId);
    }


}
