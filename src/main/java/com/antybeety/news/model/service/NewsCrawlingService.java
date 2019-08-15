package com.antybeety.news.model.service;


import com.antybeety.news.model.vo.ArticleInfoKVO;

public interface NewsCrawlingService {
    public static final int ENGINE_DAUM=1;
    public static final int ENGINE_NAVER=2;

    public ArticleInfoKVO searchArticle(int engine, String url);
}
