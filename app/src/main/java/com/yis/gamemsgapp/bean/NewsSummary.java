package com.yis.gamemsgapp.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by yinjianhua on 16/3/16.
 */
public class NewsSummary extends BmobObject {
    private String title;
    private String summary;
    private String publishTime;
    private String author;

    public NewsSummary(String title, String summary, String publishTime, String author) {
        this.title = title;
        this.summary = summary;
        this.publishTime = publishTime;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
