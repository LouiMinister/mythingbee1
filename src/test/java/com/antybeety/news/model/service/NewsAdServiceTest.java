package com.antybeety.news.model.service;

import com.antybeety.news.model.dao.ArticleInfoDAO;
import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.KeywordVO;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
public class NewsAdServiceTest {

    @Autowired
    NewsAdService service;

    @Test
    public void test_기사추가하기1(){
        ArticleInfoKVO kvo = new ArticleInfoKVO();

        kvo.setTitle("NewsAdServiceTest기사1");
        kvo.setSummary("테스트용1");
        kvo.setUrl("www.testurl.com");
        kvo.setImgURL("imageimageimage");
        kvo.setPressName("이데일리");
        kvo.setDistrictName("동작구");

        List<KeywordVO> keywords = new ArrayList<KeywordVO>();
        keywords.add(new KeywordVO("테스트키워드1","AAAA"));
        keywords.add(new KeywordVO("테스트키워드2","BBBB"));
        keywords.add(new KeywordVO("테스트키워드3","CCCC"));

        kvo.setKeywords(keywords);
        service.addArticle(kvo);
    }


    @Test
    public void test_기사추가하기2_중복코드키워드(){
        ArticleInfoKVO kvo = new ArticleInfoKVO();

        kvo.setTitle("NewsAdServiceTest기사2");
        kvo.setSummary("테스트용1");
        kvo.setUrl("www.testurl.com");
        kvo.setImgURL("imageimageimage");
        kvo.setPressName("이데일리");
        kvo.setDistrictName("동작구");

        List<KeywordVO> keywords = new ArrayList<KeywordVO>();
        // 키워드가 중복이 되면 기사PK, 키워드PK 중복되서 에러 발생
        keywords.add(new KeywordVO("테스트키워드1","EEEE"));
        keywords.add(new KeywordVO("테스트키워드2","FFFF"));
        keywords.add(new KeywordVO("테스트키워드3","FFFF"));
        keywords.add(new KeywordVO("테스트키워드4","CCCC"));

        kvo.setKeywords(keywords);
        service.addArticle(kvo);
    }


    @Test
    public void test_기사코드만들기(){
        String result=service.makeArticleCode();

        assertNotNull("기사코드 만들기",result);
    }

    @Test
    public void test_코드로기사하나찾기(){
        String code ="1907190001";
        ArticleInfoKVO articleKvo = service.searchArticle(code);
        System.out.println(articleKvo);

    }

    @Test
    public void test_기사수정테스트(){
        List<KeywordVO> list=new ArrayList<KeywordVO>();
        list.add(new KeywordVO("간첩","SPYY01"));
        list.add(new KeywordVO("테스트키워드1","AAAA0001"));
        ArticleInfoKVO akvo = new ArticleInfoKVO("1908140001","기사테스트중","이데일리","써머리",null,0,"urll.url","imgul.imgul",
                "노원",list);
        System.out.println(service.updateArticle(akvo));
    }

    @Test
    public void test_기사삭제테스트(){
        List<String> codeList = new ArrayList<String>();
        codeList.add("1908140001");
        service.deleteArticles(codeList);
    }
    
}
