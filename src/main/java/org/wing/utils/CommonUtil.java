package org.wing.utils;

import org.wing.entity.Article;
import org.wing.viewobject.Articlevo;

/**
 * 通用工具类，封装一些常用方法
 */
public class CommonUtil {

    /**
     * 将article类转化为ArticleVo类，主要功能，将Article的Date属性转为String返回给前台。
     * @param article
     * @return
     */
    public static Articlevo articleToVo(Article article){
        Articlevo articlevo = new Articlevo();
        articlevo.setArticleid(article.getArticleid());
        articlevo.setCategory(article.getCategory());
        articlevo.setCollege(article.getCollege());
        articlevo.setContents(article.getContents());
        articlevo.setPublishdate(DateTimeUtil.DateToStr(article.getPublishdate()));
        articlevo.setSchoolyear(article.getSchoolyear());
        articlevo.setTitle(article.getTitle());
        articlevo.setUser(article.getUser());
        return articlevo;
    }

    /**
     * 从身份证号中获取默认密码
     * @param identityId
     * @return
     */
    public static String identityIdToPassword (String identityId){

        //取身份证号后八位的前6位
        String password = identityId.substring(10,16);
        return password;
    }

}
