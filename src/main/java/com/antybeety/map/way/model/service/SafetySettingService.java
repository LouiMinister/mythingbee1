package com.antybeety.map.way.model.service;

import com.antybeety.map.model.service.FacilityDisplayService;
import com.antybeety.map.model.vo.*;
import com.antybeety.map.way.model.dao.AiDataDAO;
import com.antybeety.map.way.model.dao.EdgeDAO;
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
    private AiDataDAO aiDataDAO;
    @Autowired
    private FacilityDisplayService fDisplay;

    private static float distBetweenPointAndLine(float x, float y, float x1, float y1, float x2, float y2) {
        // A - the standalone point (x, y)
        // B - start point of the line segment (x1, y1)
        // C - end point of the line segment (x2, y2)
        // D - the crossing point between line from A to BC

        float AB = distBetween(x, y, x1, y1);
        float BC = distBetween(x1, y1, x2, y2);
        float AC = distBetween(x, y, x2, y2);

        // Heron's formula
        float s = (AB + BC + AC) / 2;
        float area = (float) Math.sqrt(s * (s - AB) * (s - BC) * (s - AC));

        // but also area == (BC * AD) / 2
        // BC * AD == 2 * area
        // AD == (2 * area) / BC
        float AD = (2 * area) / BC;
        return AD;
    }

    private static float distBetween(float x, float y, float x1, float y1) {
        float xx = x1 - x;
        float yy = y1 - y;

        return (float) Math.sqrt(xx * xx + yy * yy);
    }

    public void settingSafetyData(){
        List<EdgeNodeVO> edgeNodeList = getAllEdgeNode();
        List<FacilityMarkVO> cctvMarkList = fDisplay.searchCCTV();
        List<LightMarkVO> lightMarkList = fDisplay.searchLight();
        List<PoliceMarkVO> policeMarkList = fDisplay.searchPolice();
        List<ConvenienceMarkVO> convenienceMarkList = fDisplay.searchConvenience();

        settingRoadFacility(edgeNodeList,cctvMarkList);



    }
    public List<FacilityMarkVO> settingRoadFacility(List<EdgeNodeVO> edgeNodeList, List<FacilityMarkVO> allFacility){
        List<FacilityMarkVO> result = null;
        for(EdgeNodeVO v : edgeNodeList){
            for(FacilityMarkVO e : allFacility){
                if(distBetweenPointAndLine((float)e.getLat(),(float)e.getLng(),v.getLatStart(),v.getLonStart(),v.getLatEnd(),v.getLonEnd())<5){
                    aiDataDAO.addFacility(v.getId(),e.getCode());
                }
            }
        }
    }
}
