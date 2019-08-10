package com.antybeety.news.model.service;

import com.antybeety.news.model.vo.ArticleInfoVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface NewsService {


    /*마지막 기사 이후의 기사를 반환*/
    public List<ArticleInfoVO> getMoreArticles(String lastArticleCode, int limit);

    /*최초에 기사를 반환*/
    public List<ArticleInfoVO> initializeArticle(int limit);

    /*필터 기사 검색*/
    public List<ArticleInfoVO> searchArticles(String searchWord, String lastArtiCode, String lastDate, int cnt);

    /*필터 기사 검색 + 구역*/
    public List<ArticleInfoVO> searchArticles(String searchWord, String lastArtiCode, String lastDate, String district, int cnt);





}
