package com.antybeety.news.controller;

import com.antybeety.news.model.service.NewsAdService;
import com.antybeety.news.model.service.NewsService;
import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsAdControllerImpl implements NewsAdController{
    @Autowired
    private NewsAdService newsAdService;
    @Autowired
    private NewsService newsService;

    @Override
    public int addArticle(ArticleInfoKVO article) {
        return newsAdService.addArticle(article);
    }

    @Override
    public ArticleInfoVO searchArticle(String artiCode) {
        return newsAdService.searchArticle(artiCode);
    }

    @Override
    public List<String> searchAllPresses() {
        return newsAdService.searchAllPresses();
    }

    @Override
    public List<String> searchAllDistricts() {
        return newsAdService.searchAllDistricts();
    }

    @Override
    public int updateArticle(ArticleInfoKVO article) {  //0814 0949 미구현
        return newsAdService.updateArticle(article);
    }

    @Override
    public List<ArticleInfoVO> searchAllArticles() {
        return newsAdService.searchAllArticles();
    }
}
