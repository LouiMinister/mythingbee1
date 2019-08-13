package com.antybeety.news.model.dao;

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
public class KeywordDAOTest {
    @Autowired
    KeywordDAO dao;

    @Test
    public void test_키워드의_코드와이름으로_기사추가하기(){

    }

    @Test
    public void test_약자코드4자리로_마지막기사의코드찾기(){
        String code="MURD";
        String res=dao.searchCode(code);
        System.out.println(res);
    }

    @Test
    public void test_키워드이름을통해_기사의코드찾기_키워드존재할경우(){
        String name="워마드";
        String res= dao.searchCodeByName(name);
        System.out.println(res);
    }

    @Test
    public void test_키워드이름을통해_기사의코드찾기_키워드존재안할경우(){
        String name="뛣쀓쒫";
        String res= dao.searchCodeByName(name);
        System.out.println("존재안할경우 null리턴  "+res);
    }

}
