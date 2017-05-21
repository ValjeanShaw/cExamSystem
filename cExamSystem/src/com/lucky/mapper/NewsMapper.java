package com.lucky.mapper;

import com.lucky.dao.News;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2017/4/22.
 */
public interface NewsMapper {
    //查询分页下总数
    int seltotalNum();
    //查询所有的新闻
    List<News> selectAllNewsAdmin(int start,int end);

    //添加新闻
    int addNews(String newsTitle,String newsText,String createMan);

    //修改一个新闻
    int editNews(String id,String newsTitle,String newsText,String createMan);

    //删除一条新闻
    int deleteNews(String id);

    //前端加载新闻列表
    List<News> fontLoadNews(int start,int end);


}
