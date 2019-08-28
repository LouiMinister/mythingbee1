package com.antybeety.map.controller;

import com.antybeety.map.factory.FacilityDetailUpdater;
import com.antybeety.map.factory.FacilityMarkUpdater;
import com.antybeety.map.model.service.FacilityDetailService;
import com.antybeety.map.model.service.FacilityDisplayService;
import com.antybeety.map.model.vo.FacilityDetailVO;
import com.antybeety.map.model.vo.FacilityMarkVO;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FacilityController  implements InitializingBean, DisposableBean {

    @Autowired
    private FacilityDisplayService fDisplay;

    @Autowired
    private FacilityDetailService fDetail;

    @Autowired
    private FacilityMarkUpdater markUpdater;

    @Autowired
    private FacilityDetailUpdater detailUpdater;

//    public List<Map<String,Object>> searchFacility(Map<String, Object> bounds, List<Integer> facilFlag, List<String> facilName) throws SQLException {
//
//        List<FacilityMarkVO> temp ;
//        List<Map<String,Object>> result = new ArrayList<>();
//        Map<String, Object> facility ;
//        for(int i=0; i<facilFlag.size(); i++){
//            if(facilFlag.get(i) != 0){
//                temp = new ArrayList<>();
//                temp = fDisplay.searchFacilities(facilName.get(i),bounds);
//                facility =  new HashMap<>();
//                facility.put("name",facilName.get(i));
//                 facility.put("data",temp);
//                 result.add(facility);
//            }
//        }
//        return result;
//    }

    public List<Map<String,Object>> searchFacility(Map<String, Object> bounds, List<Integer> facilFlag, List<String> facilName) throws SQLException {

        List<FacilityMarkVO> temp ;
        List<Map<String,Object>> result = new ArrayList<>();
        Map<String, Object> facility ;
        for(int i=0; i<facilFlag.size(); i++){
            if(facilFlag.get(i) != 0){
                temp = markUpdater.update(bounds, facilName.get(i));
//                temp = fDisplay.searchFacilities(bounds);
                facility =  new HashMap<>();
                facility.put("name",facilName.get(i));
                facility.put("data",temp);
                result.add(facility);
            }
        }
        return result;
    }

    public FacilityDetailVO searchDetail(String type, String code) throws SQLException {
        FacilityDetailVO result = fDetail.searchDetail(type,code);

        return result;
    }

    public List<FacilityMarkVO> searchAroundFacility(Map<String,Object> bounds) {
        return fDisplay.searchAroundFacilities(bounds);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("FacilityController의 destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("FacilityController의 afterPropertiesSet");
    }
}
