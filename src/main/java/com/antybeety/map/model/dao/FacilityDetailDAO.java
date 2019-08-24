package com.antybeety.map.model.dao;

import com.antybeety.map.model.vo.FacilityDetailVO;

import java.util.List;

public interface FacilityDetailDAO {
    FacilityDetailVO searchDetail(String code);
    String getFacilName();
    List<String> searchAllRoadAddr();
}
