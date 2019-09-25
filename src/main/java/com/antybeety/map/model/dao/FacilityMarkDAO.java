package com.antybeety.map.model.dao;

import com.antybeety.map.model.vo.FacilityMarkVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FacilityMarkDAO {
    public List<FacilityMarkVO> searchFacilities(Map<String, Object> bounds);
    public List<FacilityMarkVO> searchAll();
    List<FacilityMarkVO> getLatLng();
}
