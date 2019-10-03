package com.antybeety.map.report.controller;

import com.antybeety.map.report.model.service.ReportService;
import com.antybeety.map.report.model.vo.ReportVO;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
@Controller
public class ReportController implements InitializingBean, DisposableBean {

    @Autowired
    private ReportService rs;

   public int addReport(ReportVO report){
        return rs.addReport(report);
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public List<ReportVO> searchReport(Map<String, Object> bounds) {
       return rs.searchReport(bounds);
    }
}
