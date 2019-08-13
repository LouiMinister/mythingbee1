package com.antybeety.news.model.controller;


import com.antybeety.news.controller.NewsController;
import com.antybeety.news.model.vo.ArticleInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
public class NewsControllerTest {

    @Autowired
    private NewsController newsController;

    @Test
    public void test_뉴스피드기사불러오기(){
        String lastArticleCode = "1907230001";
        int limit=5;


        List<ArticleInfoVO> articles = newsController.getArticles(lastArticleCode, limit);
    }

    @Test
    public void test_검색한기사불러오기(){
        String lastArticleCode= "1907230001";
        String lastDate ="";
        String searchWord= "여";
        int cnt=5;
        String district="마포구";
        List<ArticleInfoVO> articles = newsController.searchArticle(searchWord, lastArticleCode, lastDate, district, cnt);
        System.out.println(articles);
    }

    @Test
    public void test_검색한기사불러오기2(){
        String lastArticleCode= "1907230001";
        String lastDate ="";
        String searchWord= "여";
        int cnt=5;
        String district="";
        List<ArticleInfoVO> articles = newsController.searchArticle(searchWord, lastArticleCode, lastDate, district, cnt);
        System.out.println(articles);
    }
}
