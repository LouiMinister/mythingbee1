package com.antybeety;


import com.antybeety.map.controller.FacilityController;
import com.antybeety.map.model.dao.ConvenienceMarkDAO;
import com.antybeety.stats.model.dao.CrimeStatsDAO;
import com.antybeety.stats.model.vo.CrimeRankedVO;
import com.antybeety.stats.model.vo.CrimeStatsVO;
import com.antybeety.stats.service.CrimeStatsService;
import com.antybeety.stats.service.CrimeStatsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
@WebAppConfiguration
public class MyBatisTest {
    /*@Autowired
    private CrimeStatsDAO dao;*/
    @Autowired
    private CrimeStatsService service;
/*    @Test
    public void testing(){
        List<CrimeStatsVO> list=dao.searchStatsListByYear(2017);
        assertNotNull("list는 null이면 안됩니다.",list);
        System.out.println(list);
        assertFalse("검색 결과 없음",list.isEmpty());
    }
    @Test
    public void testing2(){
        List<CrimeStatsVO> list=dao.searchStatsListByYearCategory(2017,"폭력");
        assertNotNull("list는 null이면 안됩니다.",list);
        System.out.println(list);
        assertFalse("검색 결과 없음",list.isEmpty());
    }*/
    @Test
    public void 컨트롤러테스트(){
        List<CrimeRankedVO> list = service.calcRank(2017,"ALL");
        assertNotNull(list);
    }
    @Test
    public void 날짜테스트(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(year);
    }

    @Test
    public void 시설물테스트(){
        ConvenienceMarkDAO dao = new ConvenienceMarkDAO();
        Map<String, Object> map = new HashMap<>();
        map.put("la",37.0);
        map.put("ka",37.5);
        map.put("ea",127.0);
        map.put("ja",127.5);
        System.out.println(dao.searchFacilities(map));
    }
}
