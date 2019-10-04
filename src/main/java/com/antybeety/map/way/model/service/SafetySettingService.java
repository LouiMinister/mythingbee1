package com.antybeety.map.way.model.service;

import com.antybeety.map.model.service.FacilityDisplayService;
import com.antybeety.map.model.vo.*;
import com.antybeety.map.way.model.dao.AiDataDAO;
import com.antybeety.map.way.model.dao.EdgeDAO;
import com.antybeety.map.way.model.dao.EdgeInfoDAO;
import com.antybeety.map.way.model.vo.EdgeInfoVO;
import com.antybeety.map.way.model.vo.EdgeNodeVO;
import com.antybeety.map.way.model.vo.EdgeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SafetySettingService {
    @Autowired
    private EdgeDAO edgeDAO;
    @Autowired
    private EdgeInfoDAO edgeInfoDAO;
    @Autowired
    private AiDataDAO aiDataDAO;

    @Autowired
    private FacilityDisplayService fDisplay;

    private static double distBetweenPointAndLine(double x, double y, double x1, double y1, double x2, double y2) {
        // A - the standalone point (x, y)
        // B - start point of the line segment (x1, y1)
        // C - end point of the line segment (x2, y2)
        // D - the crossing point between line from A to BC

        double AB = distBetween(x, y, x1, y1);
        double BC = distBetween(x1, y1, x2, y2);
        double AC = distBetween(x, y, x2, y2);

        // Heron's formula
        double s = (AB + BC + AC) / 2;
        double area = (double) Math.sqrt(s * (s - AB) * (s - BC) * (s - AC));

        // but also area == (BC * AD) / 2
        // BC * AD == 2 * area
        // AD == (2 * area) / BC
        double AD = (2 * area) / BC;
        System.out.println(1000*AD);
        return 1000*AD;
    }

    private static double distBetween(double x, double y, double x1, double y1) {
        double xx = x1 - x;
        double yy = y1 - y;

        return (double) Math.sqrt(xx * xx + yy * yy);
    }

    public void settingSafetyData(){
        List<EdgeInfoVO> edgeInfoList = edgeInfoDAO.getEdgeInfo();
        for(EdgeInfoVO v : edgeInfoList){
            Map<String, Object> bounds = new HashMap<>();
            double left;
            double right;
            double bottom;
            double top;
            double margin = 0.00015;
            if (v.getLat_s() > v.getLat_e()) {
                bottom = v.getLat_e();
                top = v.getLat_s();
            }
            else {
                bottom = v.getLat_s();
                top = v.getLat_e();
            }

            // 좌우 구하기
            if(v.getLng_s() > v.getLng_e()){
                right = v.getLng_s();
                left = v.getLng_e();
            }else {
                right = v.getLng_e();
                left =  v.getLng_s();
            }
            bounds.put("ea",left-margin);
            bounds.put("ja",right+margin);
            bounds.put("la",bottom-margin);
            bounds.put("ka",top+margin);
            System.out.println(left +"왼"+right+"오"+ bottom +"밑"+top);
            List<FacilityMarkVO> markInfoList = fDisplay.searchAroundFacilities(bounds);
            System.out.println(markInfoList);
            settingRoadFacility(v, markInfoList);
        }
        /*
        List<FacilityMarkVO> markInfoList = fDisplay.searchAllFacilities();
        settingRoadFacility(edgeInfoList,markInfoList);*/
    }
    /*public void settingRoadFacility(List<EdgeInfoVO> edgeInfoList, List<FacilityMarkVO> markInfoList){
        List<FacilityMarkVO> result = null;
        for(EdgeInfoVO v : edgeInfoList){
            for(FacilityMarkVO e : markInfoList){
                if(distBetweenPointAndLine(e.getLat(),e.getLng(),v.getLat_s(),v.getLng_s(),v.getLat_e(),v.getLng_e())<0.05){
                    aiDataDAO.addFacility(v.getEdgeId(),e.getCode());
                }
            }
        }
    }*/
    public void settingRoadFacility(EdgeInfoVO v, List<FacilityMarkVO> markInfoList){
        for(FacilityMarkVO e : markInfoList){
            if(distBetweenPointAndLine(e.getLat(),e.getLng(),v.getLat_s(),v.getLng_s(),v.getLat_e(),v.getLng_e())<0.14){
                aiDataDAO.addFacility(v.getEdgeId(),e.getCode());
            }
        }
    }
}
