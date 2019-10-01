package com.antybeety.map.report.model.dao;


import com.antybeety.map.model.vo.FacilityMarkVO;
import com.antybeety.map.report.model.vo.ReportVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ReportDAO {
     int addReport(ReportVO report);
    List<ReportVO> searchReport(Map<String, Object> bounds);

}
