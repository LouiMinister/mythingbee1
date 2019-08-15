package com.antybeety.news.controller;

import com.antybeety.news.model.service.NewsAdService;
import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;
import com.antybeety.press.model.service.PressService;
import com.antybeety.press.model.vo.PressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsAdControllerImpl implements NewsAdController{
    @Autowired
    private NewsAdService newsAdService;
    @Autowired
    private PressService pressService;

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
        return pressService.searchAllNames();
    }

    @Override
    public List<String> searchAllDistricts() {
        return newsAdService.searchAllDistricts();
    }

    @Override
    public String login(String id, String password) { return newsAdService.login(id,password); }

    @Override
    public int insertPress(PressVO vo) {
        return pressService.insertPress(vo);
    }

    @Override
    public int updateArticle(ArticleInfoKVO article) {  //0814 0949 미구현
        return newsAdService.updateArticle(article);
    }

    @Override
    public List<ArticleInfoVO> searchAllArticles() {
        return newsAdService.searchAllArticles();
    }

    @Override
    public int deleteArticle(List<String> articles) {
        return newsAdService.deleteArticles(articles);
    }

    @Override
    public int updatePress(String code, String name) {
        return pressService.updatePressName(code,name);
    }

    @Override
    public int deletePress(String name) {
        return pressService.deleteByName(name);
    }
}
