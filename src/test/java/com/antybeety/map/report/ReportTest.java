package com.antybeety.map.report;

import com.antybeety.map.report.controller.ApiReportController;
import com.antybeety.map.report.model.dao.ReportDAO;
import com.antybeety.map.report.model.vo.ReportVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
        System.out.println(arc.addRequest(1.1,1.2,"addr","cat","2019-10-02","commentss"));

    }
}