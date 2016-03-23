package com.bob.bilibilibb.bean;

import java.util.Map;

//查询排行信息的结果实体类
public class VideoListResult {
    private int code; //响应码
    private int pages; //总页数
    private String name; //查询的类型
    private int num; //记录总数目
    private int results; //同上
    private Map<String,VideoDetail> list;

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Map<String, VideoDetail> getList() {
        return list;
    }

    public void setList(Map<String, VideoDetail> list) {
        this.list = list;
    }
}
