package com.antybeety.map.model.dao;

import com.antybeety.map.model.vo.FacilityDetailVO;

public interface FacilityDetailDAO {
    FacilityDetailVO searchDetail(String code);
    String getFacilName();
}
