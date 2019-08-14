package com.antybeety.press.model.dao;

import com.antybeety.news.model.vo.ArticleInfoVO;
import com.antybeety.press.model.vo.PressVO;
import com.antybeety.press.mybatis.PressMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
public class PressDAOTest {

    @Autowired
    private PressDAO dao;



    @Test
    public void test_언론사추가하기(){
        String name="테스트뉴스";
        String code="TS";
        PressVO press= new PressVO();
        press.setCode(code);
        press.setName(name);

        int result = dao.insertPressInfo(press);
        System.out.println(press);
        assertNotNull("언론사추가",result);
    }

    @Test
    public void test_이름으로코드찾기(){
        String name="이데일리";

        String res= dao.searchPressCode(name);
        System.out.println(res);
        assertNotNull("이름찾기",res);
    }

    @Test
    public void test_코드로이름찾기(){
        String code ="ED";

        String res= dao.searchPressName(code);
        System.out.println(res);
        assertNotNull("코드찾기",res);
    }

    @Test
    public void test_모든이름찾기(){
        List<String> names= dao.searchAllNames();
        System.out.println(names);
        String res="";
        for(String name: names){
            res+=name+", ";
        }
        assertNotNull("모든이름찾기", res);
    }
    @Test
    public void test_이름으로언론사제거(){
        String name = "테스트";
        PressVO press = new PressVO("TE","테스트");
        assertNotNull("언론사추가",dao.insertPressInfo(press));
        System.out.println(dao.searchAllNames());
        assertNotNull("언론사 제거",dao.deleteByName(name));
        System.out.println(dao.searchAllNames());
    }
    @Test
    public void test_코드로언론사제거(){
        String code = "TE";
        PressVO press = new PressVO(code,"테스트");
        assertNotNull("언론사추가",dao.insertPressInfo(press));
        System.out.println(dao.searchAllNames());
        assertNotNull("언론사 제거",dao.deleteByCode(code));
        System.out.println(dao.searchAllNames());
    }
    @Test
    public void test_코드로언론사이름변경(){
        String code="TS";
       int result= dao.updatePressName(code,"토토뉴스");
        assertNotNull("언론사명변경",result);
        System.out.println(result);
        System.out.println(dao.searchAllNames());
      //  System.out.println("중복이면 -1이 출력됩니다 : " + dao.updatePressName(code,"테스팅"));
       // assertNotNull("안론사명재변경",dao.updatePressName(code,"테스트 뉴스"));
       // System.out.println(dao.searchAllNames());
    }
}
