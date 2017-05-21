package com.lucky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lucky.dao.ChooseQuestion;
import com.lucky.dao.EUDateGridResult;
import com.lucky.dao.FontResult;
import com.lucky.dao.News;
import com.lucky.mapper.NewsMapper;
import com.lucky.service.NewsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/4/22.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    /**
     * //查询所有的新闻列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDateGridResult getNewsList(int page,int rows) {
        //分页处理
//        PageHelper.startPage(page,rows);
//        List<News> list = newsMapper.selectAllNewsAdmin(value);
//
//        //创建一个返回值对象
//        EUDateGridResult result = new EUDateGridResult();
//        result.setRows(list);
//        //取记录总条数
//        PageInfo<News> pageInfo = new PageInfo<News>(list);
//        result.setTotal(pageInfo.getTotal());
//        return result;

        //新的处理方法
        int start = rows*(page-1);
        int num = rows;

        EUDateGridResult result = new EUDateGridResult();
        int total = newsMapper.seltotalNum();
        List<News> list = newsMapper.selectAllNewsAdmin(start,num);
        result.setTotal(total);
        result.setRows(list);

        return result;

    }

    /**
     * 增加一条新闻
     * @param newsTitle
     * @param newsText
     * @param createMan
     * @return
     */
    @Override
    public boolean addNews(String newsTitle, String newsText, String createMan) {
        if(newsMapper.addNews(newsTitle,newsText,createMan) == 1){
            return true;
        }
        return false;
    }

    /**
     * 修改一条新闻
     * @param id
     * @param newsTitle
     * @param newsText
     * @param createMan
     * @return
     */
    @Override
    public boolean editNews(String id, String newsTitle, String newsText, String createMan) {
        if(newsMapper.editNews(id,newsTitle,newsText,createMan) == 1){
            return true;
        }
        return false;
    }

    /**
     * 删除一条新闻
     * @param id
     * @return
     */
    @Override
    public boolean deleteNews(String id) {
        if(newsMapper.deleteNews(id) == 1){
            return true;
        }
        return false;
    }

    /**
     * 前台查询新闻返回
     * @param start
     * @return
     */
    @Override
    public FontResult fontLoadNews(int start) {
        int num = 5;   //一次多加载五条
        List list = newsMapper.fontLoadNews(start,num);
        FontResult result = new FontResult();
        result.setObjects(list);
        return result;
    }



}
