package com.antybeety.news.model.service;

import com.antybeety.news.model.vo.ArticleInfoKVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
public class NewsCrawlingServiceImplTest {

    @Autowired
    NewsCrawlingService service;

    @Test
    public void test_다음뉴스기사_불러오기(){
        String []articleURL= {"https://news.v.daum.net/v/20190815090039789",
                "https://news.v.daum.net/v/20190814212855807",
                "https://news.v.daum.net/v/20190815090643916",
                "https://news.v.daum.net/v/20190812200137883",
                "https://news.v.daum.net/v/20190815133627394"
        };
        ArticleInfoKVO article;
        for(String s: articleURL){
            article=service.searchArticle(NewsCrawlingService.ENGINE_DAUM, s );
            //System.out.println(article);
            //System.out.println("-------------------------------------------------------------");
        }
    }
}
