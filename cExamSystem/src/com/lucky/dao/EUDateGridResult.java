package com.lucky.dao;

import java.util.List;

/**
 * Created by 风萧萧兮 on 2016/12/5.
 * easyui所需的类型的封装
 */
public class EUDateGridResult {

    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
