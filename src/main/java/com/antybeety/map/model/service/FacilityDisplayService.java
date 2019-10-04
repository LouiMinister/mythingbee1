package com.antybeety.map.model.service;
import com.antybeety.map.factory.FacilityMarkReloader;
import com.antybeety.map.model.dao.*;
import com.antybeety.map.model.vo.FacilityMarkVO;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.crypto.HmacSha1Aes128CksumType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacilityDisplayService implements FacilityMarkReloader, InitializingBean, DisposableBean {

    @Autowired
    private List<FacilityMarkDAOImpl> fmList;

//    public List<FacilityMarkVO> searchFacilities(String type, Map<String,Object> bounds) throws SQLException {
////        fm = createFacilDAO(type);
//
////        return fm.searchFacilities(bounds);
//
//        for(FacilityMarkDAO f : fmList){
//            if(f.getFacilName().equals(type)){
//                return f.searchFacilities(bounds);
//            }
//        }
//        return null;
//    }

    public List<FacilityMarkVO> searchFacilities(Map<String,Object> bounds, FacilityMarkDAO dao) throws SQLException {
        return dao.searchFacilities(bounds);
    }
    public List<FacilityMarkVO> searchAllFacilities() {
        List<FacilityMarkVO> temp = new ArrayList<>();
        for(FacilityMarkDAOImpl facil : fmList){
            temp.addAll(facil.getLatLng());
        }
        return temp;
    }
    public List<FacilityMarkVO> searchAroundFacilities(Map<String,Object> bounds) {
        List<FacilityMarkVO> temp = new ArrayList<>();
        for(FacilityMarkDAOImpl facil : fmList){
            temp.addAll(facil.searchFacilities(bounds));
        }
        return temp;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("FacilityDisplay의 destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("FacilityDisplay의 afterPropertiesSet");
    }

//    private FacilityMarkDAOImpl createFacilDAO(String type){
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        switch (type){
////            case "cctv": return (CCTVMarkDAO).getBean("cctvMark");
//            case "bell":  return (BellMarkDAO) context.getBean("bellMark");
////            case "light": return (LightMarkDAO) BeanUtils.getBean(com.thinkbeeway.map.dao.LightMarkDAO.class);
////            case"police": return (PoliceMarkDAO) BeanUtils.getBean(com.thinkbeeway.map.dao.PoliceMarkDAO.class);
////            case "convenience": return (ConvenienceMarkDAO) BeanUtils.getBean(com.thinkbeeway.map.dao.ConvenienceMarkDAO.class);
//            default: return null;
//        }
//    }
}
