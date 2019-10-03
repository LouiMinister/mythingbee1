package com.antybeety.map.mybatis;

import com.antybeety.map.model.vo.FacilityDetailVO;
import com.antybeety.map.model.vo.FacilityMarkVO;
import com.antybeety.map.report.model.vo.ReportVO;

import java.util.List;
import java.util.Map;

public interface ReportMapper {
    List<ReportVO> searchReport(Map<String,Object> bounds);
    int addReport(ReportVO report);
}
