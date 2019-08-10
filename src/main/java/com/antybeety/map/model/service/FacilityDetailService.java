package com.antybeety.map.model.service;

import com.antybeety.map.model.dao.FacilityDetailDAO;
import com.antybeety.map.model.dao.FacilityDetailDAOImpl;
import com.antybeety.map.model.vo.FacilityDetailVO;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class FacilityDetailService {

    @Autowired
    private List<FacilityDetailDAOImpl> fd;

    public FacilityDetailVO searchDetail(String type, String code) throws SQLException {

        for(FacilityDetailDAO f : fd){
            if(f.getFacilName().equals(type)){
                System.out.println(f.getFacilName());
                return f.searchDetail(code);
            }
        }
        return null;
    }

//    private FacilityDetailDAOImpl createFaciliDAO(String type) {
//        switch(type) {
//            case "CC": return (FacilityDetailDAOImpl)context.getBean("CCTVDetailDAO");
//            case "BE": return (FacilityDetailDAOImpl)context.getBean("BellDetailDAO");
//            case "PD": return (FacilityDetailDAOImpl)context.getBean("PoliceDetailDAO");
//            case "LI": return (FacilityDetailDAOImpl)context.getBean("LightDetailDAO");
//            case "CS": return (FacilityDetailDAOImpl)context.getBean("ConvenienceDetailDAO");
//            default :  return null;
//        }
//    }
}
