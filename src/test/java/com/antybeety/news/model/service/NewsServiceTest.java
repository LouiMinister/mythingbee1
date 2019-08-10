package com.antybeety.news.model.service;

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
public class NewsServiceTest {

    @Autowired
    private NewsService newsService;

/*    @Test
    public void test_기사코드만들기(){
        String res="";

        res= newsService.makeArticleCode();
        System.out.println(res);
    }*/

    @Test
    public void test_마지막기사_이후기사불러오기(){
        String articleCode= "1907230001";
        int limit=5;
       List<ArticleInfoVO> articles =newsService.getMoreArticles(articleCode, limit);
        System.out.println(articles);
}

    @Test
    public void test_최초기사불러오기(){

        int limit=5;
        List<ArticleInfoVO> articles = newsService.initializeArticle(limit);
        System.out.println(articles);

    }

    @Test
    public void test_필터기사불러오기(){
        String articleCode= "1907230001";
        String lastDate ="";
        String searchWord= "여";
        int limit=5;
        List<ArticleInfoVO> articles = newsService.searchArticles(searchWord,articleCode,lastDate,limit);
        System.out.println(articles);
    }

    @Test
    public void test_필터지역기사불러오기(){
        String articleCode= "1907230001";
        String lastDate ="";
        String searchWord= "여";
        int limit=5;
        String district="마포구";
        List<ArticleInfoVO> articles = newsService.searchArticles(searchWord,articleCode,lastDate,district,limit);
        System.out.println(articles);
    }
}
