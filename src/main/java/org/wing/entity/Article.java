package org.wing.entity;

import java.util.Date;

public class Article {
    private Integer articleid;

    private String user;

    private String title;

    private Date publishdate;

    private String schoolyear;

    private String college;

    private String category;

    private String contents;


    public Article(){

    }

    public Article(Integer articleid, String user, String title, Date publishdate, String schoolyear, String college, String category) {
        this.articleid = articleid;
        this.user = user;
        this.title = title;
        this.publishdate = publishdate;
        this.schoolyear = schoolyear;
        this.college = college;
        this.category = category;
    }

    public Article(Integer articleid, String user, String title, Date publishdate, String schoolyear, String college, String category, String contents) {
        this.articleid = articleid;
        this.user = user;
        this.title = title;
        this.publishdate = publishdate;
        this.schoolyear = schoolyear;
        this.college = college;
        this.category = category;
        this.contents = contents;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public String getSchoolyear() {
        return schoolyear;
    }

    public void setSchoolyear(String schoolyear) {
        this.schoolyear = schoolyear;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}