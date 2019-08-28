package com.antybeety.map.factory;

import com.antybeety.map.model.dao.FacilityMarkDAO;
import com.antybeety.map.model.vo.FacilityMarkVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FacilityMarkReloader {
    List<FacilityMarkVO> searchFacilities(Map<String,Object> bounds, FacilityMarkDAO type) throws SQLException;
}
