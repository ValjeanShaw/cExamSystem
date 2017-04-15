package com.lucky.service;

import com.lucky.dao.EUDateGridResult;

/**
 * Created by 风萧萧兮 on 2016/12/4.
 */
public interface ItemsService {

    EUDateGridResult getItemsList(int page, int rows);
}
