package org.wing.entity;

public class ArticleCategory {
    private Integer category;

    private String string;

    public ArticleCategory(Integer category, String string) {
        this.category = category;
        this.string = string;
    }

    public ArticleCategory() {
        super();
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string == null ? null : string.trim();
    }
}