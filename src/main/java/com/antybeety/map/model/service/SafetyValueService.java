package com.antybeety.map.model.service;

import com.antybeety.map.model.vo.FacilityMarkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
// 길찾기를 위한 정보들 ( 안전 가중치 등 ) 세팅하기 위한 서비스
public class SafetyValueService {

    @Autowired
    private FacilityDisplayService fDisplay;

    @Autowired
    private DistanceCalcService distanceCalc;

    // 두 노드 사이에 안전 가중치를 구하는 메서드
    public double setSafetyValue (Map<String,Object> bounds){
        // 영역 내의 모든 시설물 가져오기
        List<FacilityMarkVO> allFacility = fDisplay.searchAroundFacilities(bounds);

        int safetyValueSum = 0;

        // 영역 내의 총 안전수치 점수 . 지금은 개당 1점이지만 안전 시설물마다 점수를 다르게 매겨야 함
        for(FacilityMarkVO v : allFacility){
            safetyValueSum ++;
        }

        return  safetyValueSum / distanceCalc.calcArea(bounds);
    }
}
