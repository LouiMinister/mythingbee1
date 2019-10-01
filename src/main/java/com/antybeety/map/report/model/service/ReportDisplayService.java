package com.antybeety.map.report.model.service;

import com.antybeety.map.report.model.dao.ReportDAO;
import com.antybeety.map.report.model.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportDisplayService {
    @Autowired
    private ReportDAO reportDao;

    public List<ReportVO> ReportSearch(Map bounds){
        return reportDao.searchReport(bounds);

    }

}
