package com.antybeety.news.controller;

import com.antybeety.news.model.vo.ArticleInfoVO;

public interface NewsAdController {

    /*ArticleVO를 통해 기사를 추가
     * int로 결과 반환. 결과는 성공했을 시 1*/
    int addArticle(ArticleInfoVO article);

}
