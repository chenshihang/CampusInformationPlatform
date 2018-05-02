package org.wing.dao;

import org.wing.entity.Article;

import java.util.List;

public interface ArticleDao {
    int deleteByPrimaryKey(Integer articleid);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleid);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> getRecentArticles();

    List<Article> getArticleList(int categoryId);
}