package com.lucky.service.impl;

import com.lucky.dao.TopChapterNum;
import com.lucky.dao.TopChapterNumRight;
import com.lucky.dao.TopStudentNum;
import com.lucky.dao.TopStudentNumRight;
import com.lucky.mapper.ChooseMapper;
import com.lucky.mapper.StuMessCMapper;
import com.lucky.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/5/21.
 */
@Service
public class TopServiceImpl implements TopService {
    @Autowired
    ChooseMapper chooseMapper;
    @Autowired
    StuMessCMapper stuMessCMapper;

    @Override
    public List<TopChapterNum> topAllChapterNumService() {
        return chooseMapper.topAllChapterNum();
    }

    @Override
    public List<TopChapterNumRight> topRightChapterNumService() {
        return chooseMapper.topRightChapterNum();
    }

    @Override
    public List<TopStudentNum> topAllStudentNumService() {
        return stuMessCMapper.topAllStudentNum();
    }

    @Override
    public List<TopStudentNumRight> topRightStudentNumService() {
        return stuMessCMapper.topRightStudentNum();
    }
}
