package com.antybeety.map.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FacilityDetailUpdater {

    @Autowired
    private Map<String, FacilityDetailReloader> reloaderMap;

    @Autowired
    private FacilityDetailHolder holder;

    public void update(String beanName) {
        this.reloaderMap.forEach((k,v) ->
                v.changeFacilityDetailDAO((this.holder.get(beanName))));
    }
}
