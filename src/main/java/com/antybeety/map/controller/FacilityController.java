package com.antybeety.map.controller;

import com.antybeety.map.model.service.DistanceCalcService;
import com.antybeety.map.model.service.FacilityDetailService;
import com.antybeety.map.model.service.FacilityDisplayService;
import com.antybeety.map.model.service.SafetyValueService;
import com.antybeety.map.model.vo.FacilityDetailVO;
import com.antybeety.map.model.vo.FacilityMarkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FacilityController {

    @Autowired
    private FacilityDisplayService fDisplay;

    @Autowired
    private FacilityDetailService fDetail;

    @Autowired
    private DistanceCalcService distanceCalc;

    @Autowired
    private SafetyValueService safetyValue;

    public List<Map<String,Object>> searchFacility(Map<String, Object> bounds, List<Integer> facilFlag, List<String> facilName) throws SQLException {

        List<FacilityMarkVO> temp ;
        List<Map<String,Object>> result = new ArrayList<>();
        Map<String, Object> facility ;
        for(int i=0; i<facilFlag.size(); i++){
            if(facilFlag.get(i) != 0){
                temp = new ArrayList<>();
                temp = fDisplay.searchFacilities(facilName.get(i),bounds);
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

    public List<Integer> searchDistanceAll(List<Map<String,Object>> locationList) {
        return distanceCalc.calcDistanceAll(locationList);
    }

    public int searchDistance(double startLat, double startLon, double endLat, double endLon) {
        return distanceCalc.calcDistance(startLat, startLon, endLat, endLon);
    }

    public double calcSafetyValue(double startLat, double startLon, double endLat, double endLon){

        double ea;  // left
        double ja;  // right
        double ka;  // top
        double la;  // bottom

        double area;    // 영역의 넓이

        Map<String,Object> bounds = new HashMap<>();

        // top, bottom 찾기
        if(startLat > endLat){
            la = endLat;
            ka = startLat;
        } else {
            la = startLat;
            ka = endLat;
        }

        // left, right 찾기
        if(startLon > endLon){
            ea = endLon;
            ja = startLon;
        }else {
            ea = startLon;
            ja = endLon;
        }

        // 0.00005를 더해주는 이유 : 정확히 그 내부가 아닌 도로의 폭 정도를 여유를 주기 위함
        bounds.put("la",la-0.00005);
        bounds.put("ka",ka+0.00005);
        bounds.put("ja",ja+0.00005);
        bounds.put("ea",ea-0.00005);


        return safetyValue.setSafetyValue(bounds);
    }
}
