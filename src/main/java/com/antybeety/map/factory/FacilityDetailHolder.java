package com.antybeety.map.factory;

import com.antybeety.map.model.dao.FacilityDetailDAO;
import com.antybeety.map.model.dao.FacilityMarkDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FacilityDetailHolder {

    @Autowired
    private Map<String, FacilityDetailDAO> fmMap;

    public FacilityDetailDAO get(String type){
        return this.fmMap.get(type);
    }
}
