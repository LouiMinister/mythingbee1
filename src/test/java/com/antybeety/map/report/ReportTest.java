package com.antybeety.map.report;

import com.antybeety.map.report.controller.ApiReportController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
@WebAppConfiguration
public class ReportTest {
    @Autowired
    private ApiReportController arc;

    @Test
    public void addTest() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("lat",1.2);
        map.put("lon",3.3);
        map.put("address","testing");
        map.put("category","theCategory");
        map.put("date","2019-10-03");
        map.put("comments","etc");
        System.out.println(arc.addReport(map));
        //System.out.println(arc.addReport(1.1,1.2,"addr","cat","2019-10-02","commentss"));

    }

    @Test
    public void searchTest() {
        Map<String,Object> bounds = new HashMap<String,Object>();
        bounds.put("la",0.1);
        bounds.put("ka",2.1);
        bounds.put("ea",0.1);
        bounds.put("ja",2.1);
       System.out.println(arc.searchReport(bounds));
    }
}