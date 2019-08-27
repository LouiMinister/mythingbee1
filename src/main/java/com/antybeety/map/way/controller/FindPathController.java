package com.antybeety.map.way.controller;

import com.antybeety.map.way.model.service.DistanceCalcService;
import com.antybeety.map.way.model.service.MapSettingService;
import com.antybeety.map.way.model.service.SafetyPathService;
import com.antybeety.map.way.model.vo.NodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.soap.Node;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FindPathController {


    @Autowired
    private DistanceCalcService distanceCalc;

    @Autowired
    private MapSettingService safetyValue;

    @Autowired
    private SafetyPathService safetyPathService;

    public List<Integer> searchDistanceAll(List<Map<String,Object>> locationList) {
        return distanceCalc.calcDistanceAll(locationList);
    }

    public int searchDistance(double startLat, double startLon, double endLat, double endLon) {
        return distanceCalc.calcDistance(startLat, startLon, endLat, endLon);
    }

//    public double calcSafetyValue(double startLat, double startLon, double endLat, double endLon){
//
//        double ea;  // left
//        double ja;  // right
//        double ka;  // top
//        double la;  // bottom
//
//        double area;    // 영역의 넓이
//
//        Map<String,Object> bounds = new HashMap<>();
//
//        // top, bottom 찾기
//        if(startLat > endLat){
//            la = endLat;
//            ka = startLat;
//        } else {
//            la = startLat;
//            ka = endLat;
//        }
//
//        // left, right 찾기
//        if(startLon > endLon){
//            ea = endLon;
//            ja = startLon;
//        }else {
//            ea = startLon;
//            ja = endLon;
//        }
//
//        // 0.00005를 더해주는 이유 : 정확히 그 내부가 아닌 도로의 폭 정도를 여유를 주기 위함
//        bounds.put("la",la-0.00005);
//        bounds.put("ka",ka+0.00005);
//        bounds.put("ja",ja+0.00005);
//        bounds.put("ea",ea-0.00005);
//
//
//        return safetyValue.calcSafetyValue(bounds);
//    }

    public List<NodeVO> searchSafePath(double startLat, double startLon, double endLat, double endLon) {

        try{
            NodeVO startNode = safetyPathService.matchNode(startLat,startLon);
            NodeVO endNode = safetyPathService.matchNode(endLat,endLon);
            return  safetyPathService.astar(startNode.getId(),endNode.getId());
        }
        catch(Exception e){
            return null;
        }
    }
}
