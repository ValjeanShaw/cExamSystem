package com.lucky.dao;

/**
 * Created by 风萧萧兮 on 2017/5/14.
 */
public class Exampaper {
    private String id;
    private String exampaperTitle;
    private String createDate;
    private String createMan;
    private String difficulty;

    public String getExampaperTitle() {
        return exampaperTitle;
    }

    public void setExampaperTitle(String exampaperTitle) {
        this.exampaperTitle = exampaperTitle;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
