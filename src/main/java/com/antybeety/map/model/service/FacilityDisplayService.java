package com.antybeety.map.model.service;
import com.antybeety.map.model.dao.*;
import com.antybeety.map.model.vo.FacilityMarkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.crypto.HmacSha1Aes128CksumType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacilityDisplayService {

    private FacilityMarkDAOImpl fm;



    @Autowired
    private List<FacilityMarkDAOImpl> fmList;

    public List<FacilityMarkVO> searchFacilities(String type, Map<String,Object> bounds) throws SQLException {
//        fm = createFacilDAO(type);

//        return fm.searchFacilities(bounds);

        for(FacilityMarkDAO f : fmList){
            if(f.getFacilName().equals(type)){
                return f.searchFacilities(bounds);
            }
        }
        return null;
    }

    public List<FacilityMarkVO> searchAroundFacilities(Map<String,Object> bounds) {
        List<FacilityMarkVO> temp = new ArrayList<>();
        for(FacilityMarkDAOImpl facil : fmList){
            temp.addAll(facil.searchFacilities(bounds));
        }
        return temp;
    }

    public int searchSafetyValue(double lat, double lng, double lat1, double lng1) {

        double temp;
        // lat, lng 가 작은거
        if(lat > lat1){
            temp = lat;
            lat = lat1;
            lat1 = temp;
        }
        if( lng > lng1){
            temp = lng;
            lng = lng1;
            lng1 = temp;
        }

        Map<String, Object> bounds = new HashMap<>();
        bounds.put("la",lat);
        bounds.put("ka",lat1);
        bounds.put("ea",lng);
        bounds.put("ja",lng1);

        List<FacilityMarkVO> list = this.searchAroundFacilities(bounds);

        int safetyValue = 0;

        for( FacilityMarkVO f : list){
            switch(f.getCode().substring(0,2)){
                case "CC":
                    safetyValue += 3;
                    break;
                case "BE":
                    break;
                case "LI":
                    break;
                case "CS":
                    break;
                case "PD":
                    break;
                default:
                    break;
            }
        }

        return 0;
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
