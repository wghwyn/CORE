package com.wgh.core.Global;

import java.io.Serializable;

/**
 * 分页查询公共类
 */
public class PageUtil implements Serializable{
    private int start = 0;          //起始记录
    private int end = 10;           //结束记录
    private int pageSize = 10;     //每页记录数
    private int pageNo = 1;        //当前页数

    public PageUtil(){

    }
    public PageUtil(int pageSize, int pageNo){
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.calStartEnd();
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 计算当前页起始和结束记录数
     * @return
     */
    public void calStartEnd(){
        this.start = this.pageSize*(this.pageNo-1);
        this.end = this.pageSize*this.pageNo-1;
    }

    @Override
    public String toString(){
        return "{start:" + this.start + ",end:" + this.end
                + ",pageSize:" + this.pageSize + ",pageNo:" + this.pageNo + "}";
    }
}
