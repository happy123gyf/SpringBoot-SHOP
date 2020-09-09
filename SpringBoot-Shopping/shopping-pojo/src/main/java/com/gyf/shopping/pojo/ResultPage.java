package com.gyf.shopping.pojo;

import java.util.List;

/**
 *  返回到Angular前端的封装类
 */
public class ResultPage {
    private List rows;   //分页的区间数
    private Long total;  //分页的总记录数


    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
