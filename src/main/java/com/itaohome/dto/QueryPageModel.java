package com.itaohome.dto;

import java.util.List;

/**
 * 分页model
 * Created by Administrator on 2015/1/6.
 */
public class QueryPageModel {

    private List list;//当前分页

    private Integer currPageNum;//当前页码

    private Integer totalCount;//总条数

    private Integer pageSize;//每页大小

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getCurrPageNum() {
        return currPageNum;
    }

    public void setCurrPageNum(Integer currPageNum) {
        this.currPageNum = currPageNum;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
