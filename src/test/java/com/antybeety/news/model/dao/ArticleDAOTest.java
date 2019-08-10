package com.antybeety.news.model.dao;


import com.antybeety.news.model.vo.ArticleInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
public class ArticleDAOTest {

   @Autowired
    private ArticleInfoDAO dao;

    @Test
    public void text_기사추가하기(){
        ArticleInfoVO article = new ArticleInfoVO();
        String code="1908090010";
        String title="기사추가테스트1";
        String pressName="ED";
        String districtName="NW";
        String summary="요약";
        String url ="www.sampleurl.com";
        String imgUrl ="www.sampleimgurl.com";
        article.setCode(code);
        article.setTitle(title);
        article.setPressName(pressName);
        article.setDistrictName(districtName);
        article.setSummary(summary);
        article.setUrl(url);
        article.setImgURL(imgUrl);
        dao.addArticle(article);
        List<ArticleInfoVO> insertRes=dao.searchArticleInfo(code);
        assertNotNull("추가된 기사는",insertRes);
    }

    @Test
    public void test_기사코드로찾기(){
        String code="1907190001";
        List<ArticleInfoVO> article = dao.searchArticleInfo(code);
        System.out.println(article);
        assertNotNull("기사코드로 찾은 기사 결과는 존재한다.",article);
    }

    @Test
    public void test_모든기사찾기(){
        List<ArticleInfoVO> articles = dao.searchAllArticles();
        System.out.println(articles);
        assertNotNull("기사는 하나 이상 존재한다",articles);
    }

    @Test
    public void test_기사코드로날짜찾기(){
        String code="1907190001";
        String time =dao.searchArticleTimeByCode(code);
        System.out.println(time);
        assertNotNull("기사코드로 찾은 해당 기사의 날짜는 하나 이상 존재한다.",time);
    }

    @Test
    public void test_날짜로시간이전기사찾기(){
        String time="2019-08-01 10:42:50";
        int limit = 5;
      /*  HashMap<String, Object> param= new HashMap<String, Object>();
        param.put("time",time);
        param.put("limit",limit);*/
        List<ArticleInfoVO> articles= dao.searchBeforeArticlesByTime(time,limit);
        System.out.println(articles);
    }
    @Test
    public void test_해당기사이전기사들찾기(){
        String code="1907230001";
        int limit=5;
        String time = dao.searchArticleTimeByCode(code);
        List<ArticleInfoVO> articles = dao.searchBeforeArticlesByTime(time,limit);
        System.out.println(articles);
    }

    @Test
    public void test_검색어_시작시간_끝시간으로기사찾기(){
        String startTime="2019-08-01 10:42:50";
        String lastTime="1900-01-01 01:01:01";
        String searchWord = "여";
        int limit=5;
        List<ArticleInfoVO> articles= dao.searchArticleByFilter(searchWord, startTime, lastTime, limit);
        System.out.println(articles);
    }

    @Test
    public void test_검색어_시작시간_끝시간_지역구로기사찾기(){
        String startTime="2019-08-01 10:42:50";
        String lastTime="1900-01-01 01:01:01";
        String district ="마포구";
        String searchWord = "여";
        int limit=5;
        List<ArticleInfoVO> articles= dao.searchArticleByFilter(searchWord, startTime, lastTime,district, limit);
        System.out.println(articles);
    }

    @Test
    public void test_마지막기사코드(){
        String res=dao.searchLastDate();
        System.out.println(res);
        assertNotNull("마지막기사코드찾기",res);
    }
    @Test
    public void test_동재Spring시험성적(){
        String startTime="2019-08-01 10:42:50";
        String lastTime="1900-01-01 01:01:01";
        String district ="마포구";
        String searchWord = "여";
        int limit=5;
        List<ArticleInfoVO> articles= dao.searchArticleByFilter(searchWord, startTime, lastTime,district, limit);
        boolean ar = false;
        assertTrue(ar);
    }
  /*  @Test
    public void test_*/

 /*   @Test
    public void test_기사날짜로부터_과거의기사찾기 (){
        String code="1907240001";
        List<ArticleInfoVO> articles = dao.searchArticlesByBefore
    }*/

/*    public void testModifyMember() throws Exception {
        Map<String, Object> paramMap =new HashMap<>();
        paramMap.pu("id","user1");
        paramMpa.put("pwd", "new_0000");
        paramMap.put("name","new name");

        assertTrue(dao.modifyMember)
    }*/

/*    @Test
    public void testMultiDelete(){
        String[] ids ={"user1", "user2"};
        Member member= new Member();
        member.setIds(ids);
        assertTrue(dao.removeMultiMember(member) > 0);
    }*/

}
