package com.antybeety.news.model.dao;

import com.antybeety.news.model.vo.KeywordVO;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;

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
    public void test_키워드테이블_피벗테이블에_기사추가_중복없는경우(){

        String code = "1908060001";
        KeywordVO keyword = new KeywordVO();
        keyword.setCode("ZZZZ");
        keyword.setName("중복이름");
        dao.addKeyword(code, keyword);
    }

    @Test
    public void test_키워드테이블_피벗테이블에_기사추가_키워드이름중복(){
        String code = "1908090010";
        KeywordVO keyword = new KeywordVO();
        keyword.setCode("YYYY");
        keyword.setName("중복이름");
        dao.addKeyword(code, keyword);
    }

    @Test
    public void test_키워드테이블_피벗테이블에_기사추가_키워드코드중복(){
        String code = "1908060001";
        KeywordVO keyword = new KeywordVO();
        keyword.setCode("YYYY");
        keyword.setName("안중복이름");
        dao.addKeyword(code, keyword);
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
    @Test
    public void test_키워드이름으로_키워드제거_피벗(){
        String arCode="1907190002";
        String name="테스팅";
        String code="TEST01";
        KeywordVO kvo = new KeywordVO(name,code);
        dao.addKeyword(arCode,kvo);

      System.out.println(dao.deleteKeyword("테스팅"));
    }
}
