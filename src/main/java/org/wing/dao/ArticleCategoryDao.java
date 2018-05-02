package org.wing.dao;

import org.wing.entity.ArticleCategory;

public interface ArticleCategoryDao {
    int deleteByPrimaryKey(Integer category);

    int insert(ArticleCategory record);

    int insertSelective(ArticleCategory record);

    ArticleCategory selectByPrimaryKey(Integer category);

    int updateByPrimaryKeySelective(ArticleCategory record);

    int updateByPrimaryKey(ArticleCategory record);
}