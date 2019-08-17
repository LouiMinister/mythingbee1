package com.antybeety.news.model.controller;

import com.antybeety.news.controller.NewsAdController;
import com.antybeety.news.model.service.NewsCrawlingService;
import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;
import com.antybeety.press.model.vo.PressVO;
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
public class NewsAdControllerTest{
    @Autowired
    private NewsAdController controller;

    /*모든 기사찾기*/
    @Test
    public void test_모든기사찾기(){
        List<ArticleInfoVO>res = controller.searchAllArticles();

        for(ArticleInfoVO v : res){
            System.out.println(v);
        }
    }
    @Test
    public void test_모든신문사찾기(){
        List<String> res = controller.searchAllPresses();
        System.out.println(res);
    }


    @Test
    public void test_언론사추가하기(){
        PressVO vo = new PressVO("TT","티티뉴스");
        controller.insertPress(vo);
    }

    @Test
    public void test_언론사삭제하기(){
        String pressName= "티티뉴스";
        controller.deletePress(pressName);
    }

    @Test
    public void test_언론사수정하기(){
        String pressCode="TT";
        String pressName="티티수정뉴스";
        controller.updatePress(pressCode, pressName);
    }
    @Test
    public void test_크롤링(){
        String s = "https://news.v.daum.net/v/20190814154259704";
        ArticleInfoKVO article;
        article=controller.crawlingArticle(NewsCrawlingService.ENGINE_DAUM, s);
        System.out.println(article);
    }
}
