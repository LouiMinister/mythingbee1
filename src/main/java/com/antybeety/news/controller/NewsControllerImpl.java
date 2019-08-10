package com.antybeety.news.controller;

import com.antybeety.news.model.service.NewsService;
import com.antybeety.news.model.vo.ArticleInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsControllerImpl implements NewsController {
    @Autowired
    private NewsService newsService;

    public List<ArticleInfoVO> getArticles(String lastArticleCode, int limit) {

        if(lastArticleCode.equals("") || lastArticleCode == null) {
            return null;
        }

        if(limit <= 0) {
            limit = 5;
        }

        List<ArticleInfoVO> list = null;
        if(lastArticleCode.equals("first")) {
            list = newsService.initializeArticle(limit);
        }
        else {
            list =newsService.getMoreArticles(lastArticleCode,limit);
        }
        return list;
    }

    public List<ArticleInfoVO> searchArticle(String searchWord, String lastArticleCode, String lastDate, String district, int cnt) {

        List<ArticleInfoVO> list;

        if(district.equals("")) {
            list = newsService.searchArticles(searchWord, lastArticleCode, lastDate, cnt);
        }
        else {
            list =newsService.searchArticles(searchWord, lastArticleCode, lastDate, district, cnt);
        }
        return list;
    }

}
