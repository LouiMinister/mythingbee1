package com.antybeety.news.model.service;

import com.antybeety.news.model.vo.ArticleInfoVO;

public interface NewsAdService {

    /*Vo로 기사 추가*/
    public int addArticle(ArticleInfoVO article);

    /*기사코드 만들기*/
    public String makeArticleCode();
}
