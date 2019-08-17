package com.antybeety.news.controller;

import com.antybeety.news.model.service.NewsService;
import com.antybeety.news.model.vo.ArticleInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


public interface NewsController {
    /*마지막 기사코드와 가져올 기사 수를 통해 기사들을 리턴.
    * 기사코드가 없을경우는 파라미터로 null String을 받는다.*/
    List<ArticleInfoVO> getArticles(String lastArticleCode, int limit);
    List<ArticleInfoVO> searchArticle(String searchWord, String lastArticleCode, String lastDate, String district, int cnt);

    /*가장 조회수 높은 키워드 n개를 리턴*/
    List<String> searchTopKeywords(int limit);
    /*기사 조회수 증가하기*/
    int increaseViewCount(String code);
}
