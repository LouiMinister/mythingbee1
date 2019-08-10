package com.antybeety.district.model.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
public class DistrictDAOTest {

    @Autowired
    private DistrictDAO dao;

    @Test
    public void test_모든지역구이름찾기(){
        List<String> infos = dao.searchAllDistrictNames();
        System.out.println(infos);
        assertNotNull(infos);
    }

    @Test
    public void test_모든지역구코드찾기(){
        List<String> infos = dao.searchAllDistrictCodes();
        System.out.println(infos);
        assertNotNull(infos);
    }

    @Test
    public void test_지역구이름으로코드찾기(){
        String name = "도봉구";
        String info = dao.searchDistrictCodeByName(name);
        System.out.println(info);
        assertNotNull(info);
    }

    @Test
    public void test_지역구코드로이름찾기(){
        String code = "DB";
        String info = dao.searchDistrictNameByCode(code);
        System.out.println(info);
        assertNotNull(info);
    }
}