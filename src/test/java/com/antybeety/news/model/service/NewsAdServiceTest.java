package com.antybeety.news.model.service;

import com.antybeety.news.model.dao.ArticleInfoDAO;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
    public void test_기사추가하기(){

    }

    @Test
    public void test_기사코드만들기(){
        String result=service.makeArticleCode();

        assertNotNull("기사코드 만들기",result);
    }

    @Test
    public void test_코드로_기사찾기(){

    }

    @Test
    public void test_모든신문사이름찾기(){

    }

    @Test
    public void test_모든지역구이름찾기(){

    }

    @Test
    public void test_ArticleInfoKVo로_기사_수정하기(){

    }



}
