package com.antybeety.map.model.service;
import com.antybeety.map.model.dao.*;
import com.antybeety.map.model.vo.FacilityMarkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
