package com.antybeety.map.factory;

import com.antybeety.map.model.dao.BellMarkDAO;
import com.antybeety.map.model.dao.CCTVMarkDAO;
import com.antybeety.map.model.dao.FacilityMarkDAO;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FacilityMarkHolder  implements InitializingBean, DisposableBean {

    @Autowired
    private Map<String, FacilityMarkDAO> fmMap;

    public FacilityMarkDAO get(String type){
        return this.fmMap.get(type);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("FacilityMarkHolder의 destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("FacilitymarkHolder의 afterPropertiesSet");
    }
}
