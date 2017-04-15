package com.lucky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.lucky.dao.EUDateGridResult;
import com.lucky.dao.TbItemExp;
import com.lucky.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2016/12/4.
 */
@Service
public class ItemsServiceImpl implements ItemsService {
//    @Autowired
//    private TbItemMapper tbItemMapper;

    /**
     * easyui的传参数分页查询item列表
     *
     * Easyui中datagrid控件要求的数据格式为：
     *{total:”2”,rows:[{“id”:”1”,”name”,”张三”},{“id”:”2”,”name”,”李四”}]}
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDateGridResult getItemsList(int page, int rows) {
//        //TbItemExample example = new TbItemExample();
//        PageHelper.startPage(page,rows);
//        List<TbItemExp> list = tbItemMapper.selectAllItem();
//        //分页处理
//
//        //创建一个返回值对象
//        EUDateGridResult result = new EUDateGridResult();
//        result.setRows(list);
//        //取记录总条数
//        PageInfo<TbItemExp> pageInfo = new PageInfo<TbItemExp>(list);
//        result.setTotal(pageInfo.getTotal());
//
//        return result;

        return null;
    }

}
