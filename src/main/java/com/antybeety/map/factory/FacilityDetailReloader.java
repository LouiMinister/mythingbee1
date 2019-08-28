package com.antybeety.map.factory;

import com.antybeety.map.model.dao.FacilityDetailDAO;

public interface FacilityDetailReloader {
    void changeFacilityDetailDAO(FacilityDetailDAO type);
}
