package com.lucky.service;

import com.lucky.dao.EUDateGridResult;
import com.lucky.dao.FontResult;
import net.sf.json.JSONObject;

/**
 * Created by 风萧萧兮 on 2017/4/22.
 */
public interface NewsService {

    //查询所有的新闻
    EUDateGridResult getNewsList(int page, int rows);

    //添加一个新闻
    boolean addNews(String newsTitle,String newsText,String createMan);

    //更新一个新闻
    boolean editNews(String id,String newsTitle,String newsText,String createMan);

    //删除一条新闻
    boolean deleteNews(String id);

    //前端查询新闻列表
    FontResult fontLoadNews(int start);
}
